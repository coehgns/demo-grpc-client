package com.example.demogrpcclient.service;

import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.chb.examples.lib.HelloReply;
import org.chb.examples.lib.HelloRequest;
import org.chb.examples.lib.SimpleGrpc;
import org.springframework.stereotype.Service;

@Service
public class GrpcClientService {

    @GrpcClient("test")
    private SimpleGrpc.SimpleBlockingStub stub;

    public String sendMessage(String name) {
        try {
            HelloReply response = this.stub
                    .sayHello(
                            HelloRequest.newBuilder()
                            .setName(name)
                            .build()
                    );
            return response.getMessage();
        } catch (StatusRuntimeException e) {
            return "FAILED with " + e.getStatus().getCode().name();
        }
    }
}
