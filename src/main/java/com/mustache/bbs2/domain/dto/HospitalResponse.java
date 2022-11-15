package com.mustache.bbs2.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HospitalResponse {
    private Long id;
    private String hospitalName;
    private String roadNameAddress;
    private String phone;
    private String businessTypeName;
    private Integer patientRoomCount;
    private String businessStatus;

    public void setBusinessStatus(String businessStatus) {
        this.businessStatus = businessStatus;
    }

    public HospitalResponse(Long id, String hospitalName, String roadNameAddress, String phone, String businessTypeName, Integer patientRoomCount, Integer businessStatusCode) {
        this.id = id;
        this.hospitalName = hospitalName;
        this.roadNameAddress = roadNameAddress;
        this.phone = phone;
        this.businessTypeName = businessTypeName;
        this.patientRoomCount = patientRoomCount;
    }

}
