package com.eis.user.daoImpl;

import com.eis.user.dao.UserDao;
import com.eis.user.entity.User;
import com.eis.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findAllByCompanyAndUsername(String company, String username) {
        return userRepository.findAllByCompanyAndUsername(company,username);
    }

    @Override
    public User findByUserId(Integer userId) {
        return userRepository.findByUserId(userId);
    }
}
