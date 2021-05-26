package com.eis.broker.service;

import com.eis.broker.websocket.MarketDepthEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BroadcastService {

    private final MarketDepthEndpoint marketDepthEndpoint;

    @Autowired
    public BroadcastService(MarketDepthEndpoint marketDepthEndpoint) {
        this.marketDepthEndpoint = marketDepthEndpoint;
    }

    class BroadcastThread extends Thread {
        @Override
        @SuppressWarnings("InfiniteLoopStatement")
        public void run() {
            while (true) {
                try {
                    marketDepthEndpoint.sendAll();
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @PostConstruct
    public void broadCast() {
        BroadcastThread thread = new BroadcastThread();
        thread.start();
    }
}
