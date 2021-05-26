package org.eis.market.endPoint;

import org.eis.market.MarketApplication;
import org.springframework.beans.factory.annotation.Autowired;

import javax.websocket.*;
import java.net.URI;

@ClientEndpoint
public class MarketClientEndPoint {
    private final String TAG="MarketClientEndPoint ";

    public MarketClientEndPoint(URI endpointURI) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, endpointURI);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @OnOpen
    public void open(Session session){
        MarketApplication.logger.info(TAG+"Client WebSocket is opening...");
    }

    @OnMessage
    public void onMessage(String message){
        MarketApplication.logger.info(TAG+"Server send message: " + message);
    }

    @OnClose
    public void onClose(){
        MarketApplication.logger.info(TAG+"Websocket closed");
    }
}
