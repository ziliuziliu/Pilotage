package com.eis.user.dao;

import com.eis.user.entity.User;

public interface UserDao {
    User findAllByCompanyAndUsername(String company, String username);

    User findByUserId(Integer userId);
}
