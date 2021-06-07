package com.eis.traderUI.util;

import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;


public class MyWebSocketClient extends WebSocketClient {
    public static final String TAG="MyWebSocketClient";

    public MyWebSocketClient(String url) throws URISyntaxException {
        super(new URI(url));
    }

    @Override
    public void onOpen(ServerHandshake handShakeData) {
        Log.i(TAG,"ws open");
    }

    @Override
    public void onMessage(String message) {
        Log.i(TAG,"ws receive: "+message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        Log.i(TAG,"ws close");
    }

    @Override
    public void onError(Exception ex) {
        Log.i(TAG,"ws error");
    }
}
