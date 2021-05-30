package com.eis.gateway.controller;

import com.eis.common.util.Msg;
import com.eis.common.util.MsgCode;
import com.eis.common.util.MsgUtil;
import com.eis.gateway.GatewayApplication;
import com.eis.gateway.dto.UserInfo;
import com.eis.gateway.service.UserFeignService;
import com.eis.gateway.util.TokenUtil;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trader")
public class UserController {
    @Autowired
    private UserFeignService userFeignService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    Msg<UserInfo> login(@RequestBody JsonObject jsonObject){
        Msg<UserInfo> userInfoMsg=userFeignService.login(jsonObject);
        if(userInfoMsg.getStatus()!=200)return userInfoMsg;
        UserInfo userInfo=userInfoMsg.getData();
        String token=TokenUtil.sign(userInfo);
        userInfo.setToken(token);
        return new Msg<>(MsgCode.SUCCESS, userInfo);
    }
}
