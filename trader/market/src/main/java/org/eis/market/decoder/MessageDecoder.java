package org.eis.market.decoder;

import com.google.gson.reflect.TypeToken;
import org.eis.market.MarketApplication;
import org.eis.market.message.JoinMessage;
import org.eis.market.message.Message;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MessageDecoder implements Decoder.Text<Message>{
    private Map<String,String> messageMap;

    @Override
    public Message decode(String s) throws DecodeException {
        Message msg;
        if(willDecode(s)){
            msg=new JoinMessage(messageMap.get("company"),messageMap.get("trader"),messageMap.get("product"));
        }else{
            throw new DecodeException(s,"[Message] Can't decode .");
        }
        return msg;
    }

    @Override
    public boolean willDecode(String s) {
        boolean flag=false;
        messageMap=new HashMap<>();
        messageMap= MarketApplication.gson.fromJson(s,new TypeToken<Map<String,String>>(){}.getType());
        Set<String> keys=messageMap.keySet();
        if(keys.contains("company")&&keys.contains("trader")&&keys.contains("product"))
            flag=true;
        return flag;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
