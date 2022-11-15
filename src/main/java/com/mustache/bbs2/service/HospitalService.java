package com.mustache.bbs2.service;

import com.mustache.bbs2.domain.dto.HospitalResponse;
import com.mustache.bbs2.domain.entity.Hospital;
import com.mustache.bbs2.repository.HospitalRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HospitalService {
    private final HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public HospitalResponse getHospital(Long id) {
        Optional<Hospital> optHospital = hospitalRepository.findById(id);
        Hospital hospital = optHospital.get();
        HospitalResponse hospitalResponse = Hospital.of(hospital);
        if(hospital.getBusinessStatusCode() == 13) {
            hospitalResponse.setBusinessStatus("영업중");
        }else if(hospital.getBusinessStatusCode() == 3) {
            hospitalResponse.setBusinessStatus("폐업");
        }else{
            hospitalResponse.setBusinessStatus(String.valueOf(hospital.getBusinessStatusCode()));
        }

        return hospitalResponse;
    }
}
