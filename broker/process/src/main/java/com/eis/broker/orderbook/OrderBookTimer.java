package com.eis.broker.orderbook;

import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;

public class OrderBookTimer {
    public static ConcurrentHashMap<String, Instant> timer;
}
