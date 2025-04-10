package com.example.demogrpcclient.presentation.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class AddressDTO {
    private String street;
    private String city;
    private String country;
    private String postalCode;
    private Map<String, String> additionalInfo;
}
