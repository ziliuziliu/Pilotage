package com.eis.user.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name="trader_user")
public class User {
    @Id
    @Column(name = "userId")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer userId;
    private String company;
    private String username;
    private transient String password;

    public User(){}

    public User(String company, String username, String password) {
        this.company = company;
        this.username = username;
        this.password = password;
    }
}
