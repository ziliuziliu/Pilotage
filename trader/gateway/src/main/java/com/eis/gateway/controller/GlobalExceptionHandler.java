package com.eis.gateway.controller;

import com.eis.common.util.Msg;
import com.eis.common.util.MsgCode;
import com.eis.common.util.MsgUtil;
import com.eis.gateway.GatewayApplication;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public Msg<Boolean> handle(NullPointerException e) {
        GatewayApplication.logger.error("NullPointerException", e);
        return new Msg<>(MsgCode.NULL_ARGUMENT,false);
    }

    @ExceptionHandler(RuntimeException.class)
    public Msg<Boolean> handle(RuntimeException e){
        GatewayApplication.logger.error("RuntimeException", e);
        if(e.getMessage()== MsgUtil.PARAM_DEFICIT_MSG){
            return new Msg<>(MsgCode.PARAM_DEFICIT,false);
        }
        return new Msg<>(MsgCode.NULL_ARGUMENT,false);
    }
}
