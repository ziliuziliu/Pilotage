package com.eis.broker.endpoint;

import com.eis.broker.config.GrpcClientConfiguration;
import com.eis.broker.entity.OrderLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DispatchGrpcClient {

    private final GrpcClientConfiguration configuration;

    @Autowired
    public DispatchGrpcClient(GrpcClientConfiguration grpcClientConfiguration) {
        this.configuration = grpcClientConfiguration;
    }

    public boolean dispatch(OrderLog orderLog) {
        DispatchRequest dispatchRequest = DispatchRequest.newBuilder()
                .setOrderId(orderLog.getOrderId())
                .setProduct(orderLog.getProduct())
                .setQuantity(orderLog.getQuantity())
                .setPrice(orderLog.getPrice())
                .setSide(orderLog.getSide())
                .setType(orderLog.getType())
                .setCompany(orderLog.getCompany())
                .setTrader(orderLog.getTrader())
                .build();
        DispatchResponse response = configuration.getStub().dispatch(dispatchRequest);
        return response.getStatus();
    }

}
