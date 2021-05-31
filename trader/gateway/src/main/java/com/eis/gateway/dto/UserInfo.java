package com.eis.gateway.dto;

import lombok.Data;

@Data
public class UserInfo {
    private Integer userId;
    private String company;
    private String username;
    private String password;
    private String token;
}
