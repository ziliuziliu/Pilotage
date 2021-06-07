package com.eis.traderUI.dto;

import lombok.Data;

@Data
public class Msg<T> {
    private int status;
    private String msg;
    private T data;
}
