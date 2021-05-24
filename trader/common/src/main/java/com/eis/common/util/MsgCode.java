package com.eis.common.util;

public enum MsgCode {
    SUCCESS(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG),
    NULL_ARGUMENT(MsgUtil.NULL_ARGUMENT,MsgUtil.NULL_ARGUMENT_MSG),
    PARAM_DEFICIT(MsgUtil.PARAM_DEFICIT,MsgUtil.PARAM_DEFICIT_MSG),
    AUTHENTICATION_FAILURE(MsgUtil.AUTHENTICATION_FAILURE,MsgUtil.AUTHENTICATION_FAILURE_MSG);

    private int status;
    private String msg;

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    private MsgCode(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
