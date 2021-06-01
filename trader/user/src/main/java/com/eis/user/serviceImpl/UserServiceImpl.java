package com.eis.user.serviceImpl;

import com.eis.user.dao.UserDao;
import com.eis.user.entity.User;
import com.eis.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User login(String username, String password, String company) {
        User user=userDao.findAllByCompanyAndUsername(company,username);
        if(user!=null){
            if(user.getPassword().equals(password)){
                user.setPassword(null);
                return user;
            }

        }
        return null;
    }

    @Override
    public User findByUserId(Integer userId) {
        return userDao.findByUserId(userId);
    }
}
