package com.example.demogrpcclient.presentation;

import com.example.demogrpcclient.client.grpc.GrpcMemberClient;
import com.example.demogrpcclient.mapper.GrpcMemberMapper;
import com.example.demogrpcclient.presentation.dto.response.ResponseMemberDTO;
import com.test.member.grpc.MemberProto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final GrpcMemberClient grpcMemberClient;
    private final GrpcMemberMapper grpcMemberMapper;

    @GetMapping("/grpc/{memberId}")
    public ResponseEntity<ResponseMemberDTO> grpcTest(@PathVariable("memberId") Long memberId) {
        log.trace("[gRPC TEST] 들어온 HTTP 요청 - memberId={}", memberId);

        try {
            MemberProto.MemberResponse response = grpcMemberClient.getMemberById(memberId);
            ResponseMemberDTO responseMemberDTO = grpcMemberMapper.protoToDto(response);
            log.trace("[gRPC TEST] 응답 - ID={}, email={}", responseMemberDTO.getId(), responseMemberDTO.getEmail());

            return ResponseEntity.ok(responseMemberDTO);
        } catch (Exception e) {
            log.error("[gRPC TEST] 요청 중 예외 발생 - {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
