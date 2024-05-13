package com.example.demo.CommonFramework.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PartyVerificationDTO {

    @JsonProperty("OTP")
    private String OTP;
    @JsonProperty("RequestId")
    private int requestId;



}
