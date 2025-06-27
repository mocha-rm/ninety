package com.jhlab.ninety.domain.auth.service;

import com.jhlab.ninety.domain.auth.dto.UserJwtResponseDto;
import com.jhlab.ninety.domain.auth.dto.UserResponseDto;
import com.jhlab.ninety.domain.auth.dto.auth.LoginRequestDto;
import com.jhlab.ninety.domain.auth.dto.auth.SignUpRequestDto;
import com.jhlab.ninety.domain.auth.entity.User;
import com.jhlab.ninety.domain.auth.repository.UserRepository;
import com.jhlab.ninety.domain.auth.type.UserRole;
import com.jhlab.ninety.global.common.exception.GlobalException;
import com.jhlab.ninety.global.common.exception.type.AuthErrorCode;
import com.jhlab.ninety.global.security.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public UserResponseDto signUp(SignUpRequestDto requestDto) {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new GlobalException(AuthErrorCode.EMAIL_DUPLICATED);
        }

        User user = new User(
                requestDto.getEmail(),
                passwordEncoder.encode(requestDto.getPassword()),
                requestDto.getName(),
                requestDto.getNickName(),
                requestDto.getPhoneNumber(),
                UserRole.NORMAL
        );

        userRepository.save(user);

        return UserResponseDto.toDto(user);
    }

    @Override
    public UserJwtResponseDto login(LoginRequestDto requestDto) {
        User user = userService.getUserFromDB(requestDto.getEmail());

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new GlobalException(AuthErrorCode.PASSWORD_MISMATCH);
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDto.getEmail(),
                        requestDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtil.generateAccessToken(user);
        String refreshToken = jwtUtil.generateRefreshToken(user);

        redisTemplate.opsForValue()
                .set("RT:" + user.getId(),
                        refreshToken,
                        jwtUtil.getRefreshTokenExpirationTime(),
                        TimeUnit.MILLISECONDS
                );

        Claims claims = jwtUtil.parseClaims(accessToken);

        return new UserJwtResponseDto(user.getId(), user.getEmail(), user.getName(), user.getRole(), accessToken, refreshToken);
    }

    @Override
    public void logout(String accessToken, Long userId) {
        if (accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }

        long expiration = jwtUtil.getTokenExpirationTime(accessToken);
        redisTemplate.opsForValue().set(accessToken, "logout", expiration, TimeUnit.MILLISECONDS);

        String refreshKey = "RT:" + userId;
        if (redisTemplate.hasKey(refreshKey)) {
            redisTemplate.delete(refreshKey);
        }

        log.info("유저 [{}] 로그아웃: accessToken 블랙리스트 등록 및 refresh 삭제", userId);
    }

    @Override
    public Map<String, Object> refreshAccessToken(String refreshToken) {
        Long userId = jwtUtil.getUserId(refreshToken);

        validateRefreshToken(refreshToken, userId);

        User user = userService.getUserFromDB(userId);

        String newAccessToken = jwtUtil.generateAccessToken(user);
        Claims newClaims = jwtUtil.parseClaims(newAccessToken);

        return Map.of(
                "accessToken", newAccessToken,
                "exp", newClaims.getExpiration()
        );
    }

    private void validateRefreshToken(String refreshToken, Long userId) {
        String redisKey = "RT:" + userId;
        String storedToken = (String) redisTemplate.opsForValue().get(redisKey);

        log.info("Stored Token: {}", storedToken);

        if (storedToken == null || !storedToken.equals(refreshToken)) {
            throw new GlobalException(AuthErrorCode.INVALID_REFRESH_TOKEN);
        }
    }
}
