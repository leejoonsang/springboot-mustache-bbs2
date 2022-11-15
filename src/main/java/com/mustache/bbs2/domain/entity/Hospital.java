package com.mustache.bbs2.domain.entity;

import com.mustache.bbs2.domain.dto.HospitalResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="nation_hospital")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Hospital {
    @Id
    private Long id;

    @Column(name = "hospital_name")
    private String hospitalName;

    @Column(name = "road_name_address")
    private String roadNameAddress;

    @Column(name = "phone")
    private String phone;

    private String businessTypeName;
    private Integer patientRoomCount;

    public static HospitalResponse of(Hospital hospital) {
        return new HospitalResponse(hospital.getId(), hospital.getHospitalName(), hospital.getRoadNameAddress(),
                hospital.getPhone(), hospital.getBusinessTypeName(), hospital.getPatientRoomCount());
    }
}
