package com.eis.user.service;

import com.eis.user.entity.User;

public interface UserService {
    User login(String username,String password,String company);

    User findByUserId(Integer userId);
}
