package com.eis.transmit.controller;

import com.eis.common.util.Msg;
import com.eis.common.util.MsgCode;
import com.eis.common.util.MsgUtil;
import com.eis.transmit.TransmitApplication;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public Msg<Boolean> handle(NullPointerException e) {
        TransmitApplication.logger.error("NullPointerException", e);
        return new Msg<>(MsgCode.NULL_ARGUMENT,false);
    }

    @ExceptionHandler(RuntimeException.class)
    public Msg<Boolean> handle(RuntimeException e){
        TransmitApplication.logger.error("RuntimeException", e);
        if(e.getMessage().equals(MsgUtil.PARAM_DEFICIT_MSG)){
            return new Msg<>(MsgCode.PARAM_DEFICIT,false);
        }
        return new Msg<>(MsgCode.NULL_ARGUMENT,false);
    }

    @ExceptionHandler(feign.RetryableException.class)
    public Msg<Boolean> handle(feign.RetryableException e){
        TransmitApplication.logger.error("feignException", e);
        return new Msg<>(MsgCode.FEIGN_FAILURE,false);
    }
}
