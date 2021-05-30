package com.eis.user.repository;

import com.eis.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findAllByCompanyAndUsername(String company,String username);

    User findByUserId(Integer userId);
}
