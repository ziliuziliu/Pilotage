package com.eis.common.util;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class MsgUtil {

    public static final int SUCCESS = 200;
    public static final int NULL_ARGUMENT=400;
    public static final int PARAM_DEFICIT=401;
    public static final int AUTHENTICATION_FAILURE=402;
    public static final int FEIGN_FAILURE=403;

    public static final String SUCCESS_MSG = "Success";
    public static final String NULL_ARGUMENT_MSG="null argument";
    public static final String PARAM_DEFICIT_MSG="request param deficit";
    public static final String AUTHENTICATION_FAILURE_MSG="authentication failure";
    public static final String FEIGN_FAILURE_MSG="feign failure";
}
