package com.example.demogrpcclient.presentation;

import com.example.demogrpcclient.service.GrpcClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GrpcClientController {

    private final GrpcClientService grpcClientService;

    @GetMapping("/test")
    public String sendMessage() {
        return grpcClientService.sendMessage("test");
    }
}
