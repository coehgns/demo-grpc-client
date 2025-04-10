package com.example.demogrpcclient.mapper;

import com.example.demogrpcclient.presentation.dto.request.AddressDTO;
import com.example.demogrpcclient.presentation.dto.request.ContactDTO;
import com.example.demogrpcclient.presentation.dto.response.ResponseMemberDTO;
import com.test.member.grpc.MemberProto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;

@Component
public class GrpcMemberMapper {
    public ResponseMemberDTO protoToDto(MemberProto.MemberResponse proto) {
        return ResponseMemberDTO.builder()
                .id(proto.getId())
                .name(proto.getName())
                .email(proto.getEmail())
                .profileImageBase64(proto.getProfileImageBase64())
                .address(mapAddress(proto.getAddress()))
                .contact(mapContact(proto.getContact()))
                .skills(new HashSet<>(proto.getSkillsList()))
                .interests(new HashSet<>(proto.getInterestsList()))
                .metadata(proto.getMetadata())
                .build();
    }

    private AddressDTO mapAddress(MemberProto.Address proto) {
        return AddressDTO.builder()
                .city(proto.getCity())
                .country(proto.getCountry())
                .street(proto.getStreet())
                .additionalInfo(new HashMap<>(proto.getAdditionalInfoMap()))
                .postalCode(proto.getPostalCode())
                .build();
    }

    private ContactDTO mapContact(MemberProto.Contact proto) {
        return ContactDTO.builder()
                .phone(proto.getPhone())
                .emails(proto.getEmailsList())
                .mobile(proto.getMobile())
                .socialMedia(proto.getSocialMediaMap())
                .workPhone(proto.getWorkPhone())
                .build();
    }
}
