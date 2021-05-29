package com.eis.gateway.dto;

import lombok.Data;

@Data
public class UserInfo {
    String company;
    String username;
    String password;
    String token;
}
