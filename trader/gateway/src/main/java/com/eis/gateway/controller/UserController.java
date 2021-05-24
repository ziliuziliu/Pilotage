package com.eis.gateway.controller;

import com.eis.common.util.Msg;
import com.eis.common.util.MsgCode;
import com.eis.common.util.MsgUtil;
import com.eis.gateway.GatewayApplication;
import com.eis.gateway.dto.UserInfo;
import com.eis.gateway.util.TokenUtil;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    Msg<UserInfo> login(@RequestBody JsonObject jsonObject){
        if(!jsonObject.has("company")||!jsonObject.has("trader")){
            throw new RuntimeException(MsgUtil.PARAM_DEFICIT_MSG);
        }
        UserInfo userInfo = GatewayApplication.gson.fromJson(jsonObject.toString(), UserInfo.class);
        String token=TokenUtil.sign(userInfo);
        userInfo.setToken(token);
        return new Msg<>(MsgCode.SUCCESS, userInfo);
    }
}
