package com.jhlab.ninety.domain.auth.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhlab.ninety.domain.auth.dto.UserJwtResponseDto;
import com.jhlab.ninety.domain.auth.entity.User;
import com.jhlab.ninety.domain.auth.type.UserRole;
import com.jhlab.ninety.global.security.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2ServiceImpl implements OAuth2Service {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final RedisTemplate<String, Object> redisTemplate;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    @Override
    @Transactional
    public UserJwtResponseDto googleLogin(String idToken, String accessToken) {
        try {
            // 구글 토큰 검증 API 호출
            String url = "https://oauth2.googleapis.com/tokeninfo?id_token=" + idToken;
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<>(headers);

            String response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
            JsonNode jsonNode = objectMapper.readTree(response);

            // 디버깅을 위한 로그 추가
            log.info("Google token response: {}", response);
            log.info("Configured client ID: {}", googleClientId);
            log.info("Token audience: {}", jsonNode.get("aud").asText());

            // 클라이언트 ID 검증
            String tokenAudience = jsonNode.get("aud").asText();
            if (!googleClientId.equals(tokenAudience)) {
                log.error("Client ID mismatch. Expected: {}, Got: {}", googleClientId, tokenAudience);
                throw new RuntimeException("Invalid client ID");
            }

            String email = jsonNode.get("email").asText();
            
            // 사용자 정보 가져오기
            String userInfoUrl = "https://www.googleapis.com/oauth2/v3/userinfo";
            HttpHeaders userInfoHeaders = new HttpHeaders();
            userInfoHeaders.setBearerAuth(accessToken);
            HttpEntity<String> userInfoEntity = new HttpEntity<>(userInfoHeaders);
            
            String userInfoResponse = restTemplate.exchange(
                userInfoUrl, 
                HttpMethod.GET, 
                userInfoEntity, 
                String.class
            ).getBody();
            
            log.info("Google user info response: {}", userInfoResponse);
            
            JsonNode userInfoNode = objectMapper.readTree(userInfoResponse);
            
            // 사용자 정보가 없는 경우 기본값 설정
            String name = userInfoNode.has("name") ? userInfoNode.get("name").asText() : email.split("@")[0];
            String picture = userInfoNode.has("picture") ? userInfoNode.get("picture").asText() : null;

            User user = userService.getUserByEmail(email)
                    .orElseGet(() -> createGoogleUser(email, name, picture));

            String newAccessToken = jwtUtil.generateAccessToken(user);
            String refreshToken = jwtUtil.generateRefreshToken(user);

            // Refresh Token을 Redis에 저장
            redisTemplate.opsForValue()
                    .set("RT:" + user.getId(),
                            refreshToken,
                            jwtUtil.getRefreshTokenExpirationTime(),
                            TimeUnit.MILLISECONDS);

            return new UserJwtResponseDto(
                    user.getId(),
                    user.getEmail(),
                    user.getName(),
                    user.getRole(),
                    newAccessToken,
                    refreshToken
            );

        } catch (Exception e) {
            log.error("Google login failed", e);
            throw new RuntimeException("Google login failed: " + e.getMessage());
        }
    }

    private User createGoogleUser(String email, String name, String picture) {
        User user = new User(
                email,
                null, // 소셜 로그인은 비밀번호가 없음
                name,
                null, // 전화번호는 선택적으로 추가 가능
                UserRole.NORMAL
        );
        return userService.saveUser(user);
    }
} 