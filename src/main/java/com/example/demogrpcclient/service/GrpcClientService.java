package com.example.demogrpcclient.service;

import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.chb.examples.lib.HelloWorldProto;
import org.chb.examples.lib.SimpleGrpc;
import org.springframework.stereotype.Service;

@Service
public class GrpcClientService {

    @GrpcClient("test")
    private SimpleGrpc.SimpleBlockingStub stub;

    public String sendMessage(String name) {
        try {
            HelloWorldProto.HelloReply response = this.stub
                    .sayHello(
                            HelloWorldProto.HelloRequest.newBuilder()
                            .setName(name)
                            .build()
                    );
            return response.getMessage();
        } catch (StatusRuntimeException e) {
            return "FAILED with " + e.getStatus().getCode().name();
        }
    }
}
