package org.eis.market.endPoint;

import org.eis.market.MarketApplication;
import org.eis.market.decoder.MessageDecoder;
import org.eis.market.message.JoinMessage;
import org.eis.market.message.Message;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value="/market",
                decoders = {MessageDecoder.class})
@Component
public class MarketDepthEndPoint {
    private final String TAG="MarketDepthEndPoint ";

    @OnOpen
    public void onOpen(Session session){
        MarketApplication.logger.info(TAG+"Connection opened: "+session.toString());
    }

    @OnMessage
    public void onMessage(final Session session, Message message) {
        MarketApplication.logger.info(TAG + "Received: " + message.toString());
        JoinMessage joinMessage = (JoinMessage) message;
        session.getUserProperties().put("user", joinMessage.getCompany()+joinMessage.getTrader());
        session.getUserProperties().put("active", true);
    }

    @OnClose
    public void onClose(Session session){
        MarketApplication.logger.info(TAG+"Connection closed: "+session.toString());
        session.getUserProperties().put("active",false);
    }

    @OnError
    public void onError(Session session,Throwable throwable){
        MarketApplication.logger.info(TAG+"Connection error: "+session.toString()+throwable.toString());
    }

    private synchronized void sendAll(Session session,Object msg){
        try{
            for (Session s:session.getOpenSessions()){
                if(s.isOpen()&& (boolean) s.getUserProperties().get("active")){
                    s.getBasicRemote().sendObject(msg);
                }
            }
        } catch (EncodeException | IOException e) {
            e.printStackTrace();
        }
    }
}
