package com.eis.common.util;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class MsgUtil {

    public static final int SUCCESS = 0;
    public static final int NULL_ARGUMENT=400;
    public static final int PARAM_DEFICIT=401;
    public static final int AUTHENTICATION_FAILURE=402;

    public static final String SUCCESS_MSG = "Success";
    public static final String NULL_ARGUMENT_MSG="null argument";
    public static final String PARAM_DEFICIT_MSG="request param deficit";
    public static final String AUTHENTICATION_FAILURE_MSG="authentication failure";

    public static Msg<JsonObject> makeMsg(MsgCode code, JsonObject data) {
        return new Msg<JsonObject>(code, data);
    }

    public static Msg<JsonObject> makeMsg(MsgCode code, String msg, JsonObject data) {
        return new Msg<JsonObject>(code, msg, data);
    }

    public static Msg<JsonObject> makeMsg(MsgCode code) {
        return new Msg<JsonObject>(code);
    }

    public static Msg<JsonObject> makeMsg(MsgCode code, String msg) {
        return new Msg<JsonObject>(code, msg);
    }

    public static Msg<JsonObject> makeMsg(int status, String msg, JsonObject data) {
        return new Msg<JsonObject>(status, msg, data);
    }

    public static Msg<JsonObject> makeMsg(int status, String msg) {
        return new Msg<JsonObject>(status, msg);
    }

    public static Msg<JsonArray> makeMsg(MsgCode code, String msg, JsonArray data) {
        return new Msg<JsonArray>(code, msg, data);
    }
}
