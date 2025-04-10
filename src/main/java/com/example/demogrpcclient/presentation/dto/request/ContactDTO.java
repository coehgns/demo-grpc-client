package com.example.demogrpcclient.presentation.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Builder
@Getter
public class ContactDTO {
    private String phone;
    private String mobile;
    private String workPhone;
    private List<String> emails;
    private Map<String, String> socialMedia;
}
