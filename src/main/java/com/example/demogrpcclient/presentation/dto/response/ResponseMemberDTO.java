package com.example.demogrpcclient.presentation.dto.response;

import com.example.demogrpcclient.presentation.dto.request.AddressDTO;
import com.example.demogrpcclient.presentation.dto.request.ContactDTO;
import lombok.*;

import java.util.Set;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
public class ResponseMemberDTO {
    private Long id;
    private String email;
    private String name;
    private String profileImageBase64;
    private AddressDTO address;
    private ContactDTO contact;
    private Set<String> interests;
    private Set<String> skills;
    private String metadata;
}
