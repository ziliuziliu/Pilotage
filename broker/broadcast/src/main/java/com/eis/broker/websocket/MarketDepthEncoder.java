package com.eis.broker.websocket;

import com.eis.broker.message.MarketDepthMsg;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MarketDepthEncoder implements Encoder.Text<MarketDepthMsg> {

    private static Gson gson = new Gson();

    @Override
    public String encode(MarketDepthMsg marketDepthMsg){
        return gson.toJson(marketDepthMsg);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
    }

    @Override
    public void destroy() {
    }
}
