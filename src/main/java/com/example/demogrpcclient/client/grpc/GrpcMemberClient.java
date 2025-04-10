package com.example.demogrpcclient.client.grpc;

import com.test.member.grpc.MemberProto;
import com.test.member.grpc.MemberServiceGrpc;
import io.grpc.Channel;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GrpcMemberClient {
    /**
     * application.yml에서 설정한 `member-service` 채널을 이용합니다.
     * 위에서 등록된 빈을 주입 받기 위한 역할을 합니다.
     */
    @GrpcClient("member-service")
    private Channel channel;

    /**
     * 회원 Id로 회원을 조회합니다.
     * @param memberId 조회할 회원 Id
     * @return memberId로 조회한 회원 정보
     */
    public MemberProto.MemberResponse getMemberById(Long memberId) {
        log.trace("getMemberById 메서드 진입 - 요청 ID: {}", memberId);

        // `newBlockingStub()`를 호출하여 gRPC 서버에 요청을 보낼 stub 인스턴스 생성.
        MemberServiceGrpc.MemberServiceBlockingStub stub =
                MemberServiceGrpc.newBlockingStub(channel);

        // grpc 요청 객체 생성
        MemberProto.MemberIdRequest request = MemberProto.MemberIdRequest.newBuilder()
                .setId(memberId)
                .build();

        try {
            return stub.getMemberById(request);
        } catch (StatusRuntimeException e) {
            log.error("gRPC 호출 실패 - 상태: {}, 설명: {}, 원인: {}",
                    e.getStatus(),
                    e.getStatus().getDescription(),
                    e.getCause());
            throw e;
        }
    }
}
