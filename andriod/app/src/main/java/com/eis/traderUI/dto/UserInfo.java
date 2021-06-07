package com.eis.traderUI.dto;

import lombok.Data;

@Data
public class UserInfo {
    private Integer userId;
    private String company;
    private String username;
    private String password;
    private String token;

    public UserInfo(String company, String username, String password) {
        this.company = company;
        this.username = username;
        this.password = password;
    }
}
