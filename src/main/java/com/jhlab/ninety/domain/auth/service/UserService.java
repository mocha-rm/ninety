package com.jhlab.ninety.domain.auth.service;

import com.jhlab.ninety.domain.auth.entity.User;

import java.util.Optional;

public interface UserService {
    User getUserFromDB(Long userId);

    User getUserFromDB(String email);

    Optional<User> getUserByEmail(String email);

    User saveUser(User user);

}
