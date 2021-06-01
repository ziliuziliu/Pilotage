package com.eis.user.controller;

import com.eis.common.util.Msg;
import com.eis.common.util.MsgCode;
import com.eis.common.util.MsgUtil;
import com.eis.user.UserApplication;
import com.eis.user.entity.User;
import com.eis.user.service.UserService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            UserApplication.logger.info("user not found");
            return new Msg<>(MsgCode.AUTHENTICATION_FAILURE,null);
        }
        System.out.println(user.toString());
        return new Msg<>(MsgCode.SUCCESS,user);
    }

    @RequestMapping(value="/user",method = RequestMethod.GET)
    public Msg<User> findByUserId(@RequestParam Integer userId){
        return new Msg<>(MsgCode.SUCCESS,userService.findByUserId(userId));
    }
}
