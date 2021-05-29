package com.eis.user.controller;

import com.eis.common.util.Msg;
import com.eis.common.util.MsgCode;
import com.eis.common.util.MsgUtil;
import com.eis.user.entity.User;
import com.eis.user.service.UserService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Msg<User> login(@RequestBody JsonObject jsonObject){
        if(!jsonObject.has("company")||!jsonObject.has("username")||!jsonObject.has("password")){
            throw new RuntimeException(MsgUtil.PARAM_DEFICIT_MSG);
        }
        String company=jsonObject.get("company").getAsString();
        String username=jsonObject.get("username").getAsString();
        String password=jsonObject.get("password").getAsString();
        User user=userService.login(username,password,company);
        if(user==null){
            return new Msg<>(MsgCode.AUTHENTICATION_FAILURE,null);
        }
        return new Msg<>(MsgCode.SUCCESS,user);
    }
}
