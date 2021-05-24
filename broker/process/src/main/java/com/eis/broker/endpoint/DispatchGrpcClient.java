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

    public boolean finished(String product) {
        FinishedRequest finishedRequest = FinishedRequest.newBuilder()
                .setProduct(product)
                .build();
        FinishedResponse response = configuration.getStub().finished(finishedRequest);
        return response.getStatus();
    }

}
