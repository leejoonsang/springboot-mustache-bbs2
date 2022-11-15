package com.mustache.bbs2.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class HospitalResponse {
    private Long id;
    private String hospitalName;
    private String roadNameAddress;
    private String phone;
    private String businessTypeName;
    private Integer patientRoomCount;
}
