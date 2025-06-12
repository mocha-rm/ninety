package com.jhlab.ninety.domain.auth.service;

import com.jhlab.ninety.domain.auth.entity.User;

public interface UserService {
    User getUserFromDB(Long userId);

    User getUserFromDB(String email);

}
