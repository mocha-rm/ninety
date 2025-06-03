package com.jhlab.ninety.domain.auth.service;

import com.jhlab.ninety.domain.auth.dto.UserResponseDto;
import com.jhlab.ninety.domain.auth.dto.auth.LoginRequestDto;
import com.jhlab.ninety.domain.auth.dto.auth.SignUpRequestDto;
import com.jhlab.ninety.domain.auth.entity.User;
import com.jhlab.ninety.domain.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    @Override
    public UserResponseDto signUp(SignUpRequestDto requestDto) {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RuntimeException("이메일 중복");
        }

        User user = new User(
                requestDto.getEmail(),
                requestDto.getPassword(),
                requestDto.getName(),
                requestDto.getPhoneNumber()
        );

        userRepository.save(user);

        return UserResponseDto.toDto(user);
    }

    @Override
    public UserResponseDto login(LoginRequestDto requestDto) {
        return null;
    }

    @Override
    public void logout(String accessToken, Long userId) {

    }

    @Override
    public Map<String, Object> refreshAccessToken(String refreshToken) {
        return Map.of();
    }
}
