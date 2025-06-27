package com.jhlab.ninety.domain.auth.service;

import com.jhlab.ninety.domain.auth.dto.UserResponseDto;
import com.jhlab.ninety.domain.auth.dto.update.UserNicknameUpdateRequestDto;
import com.jhlab.ninety.domain.auth.dto.update.UserPasswordUpdateRequestDto;
import com.jhlab.ninety.domain.auth.entity.User;
import com.jhlab.ninety.domain.auth.repository.UserRepository;
import com.jhlab.ninety.global.common.exception.GlobalException;
import com.jhlab.ninety.global.common.exception.type.AuthErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Cacheable(value = "user", key = "#userId")
    public UserResponseDto findUser(Long userId) {
        User user = getUserFromDB(userId);
        return UserResponseDto.toDto(user);
    }

    @Override
    @Transactional
    @CacheEvict(value = "user", key = "#userId")
    public UserResponseDto updateNickname(Long userId, UserNicknameUpdateRequestDto requestDto) {
        User user = getUserFromDB(userId);
        user.updateNickname(requestDto.getNickName());
        userRepository.save(user);

        return UserResponseDto.toDto(user);
    }

    @Override
    @Transactional
    @CacheEvict(value = "user", key = "#userId")
    public void updatePassword(Long userId, UserPasswordUpdateRequestDto requestDto) {
        User user = getUserFromDB(userId);

        if (!passwordEncoder.matches(requestDto.getCurrentPassword(), user.getPassword())) {
            throw new GlobalException(AuthErrorCode.PASSWORD_MISMATCH);
        } else if (requestDto.getCurrentPassword().equals(requestDto.getNewPassword())) {
            throw new GlobalException(AuthErrorCode.PASSWORD_BAD_REQUEST_SAME_AS_BEFORE);
        } else if (!requestDto.getNewPassword().equals(requestDto.getConfirmPassword())) {
            throw new GlobalException(AuthErrorCode.PASSWORD_BAD_REQUEST_CONFIRM_AGAIN);
        }

        user.updatePassword(passwordEncoder.encode(requestDto.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public User getUserFromDB(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new GlobalException(AuthErrorCode.USER_NOT_FOUND));
    }

    @Override
    public User getUserFromDB(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new GlobalException(AuthErrorCode.USER_NOT_FOUND));
    }
}
