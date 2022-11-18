package com.mustache.bbs2.service;

import com.mustache.bbs2.domain.dto.HospitalResponse;
import com.mustache.bbs2.domain.entity.Hospital;
import com.mustache.bbs2.repository.HospitalRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class HospitalServiceTest {

    private HospitalRepository hospitalRepository = Mockito.mock(HospitalRepository.class);

    private HospitalService hospitalService;

    @BeforeEach
    void setUp() {
        hospitalService = new HospitalService(hospitalRepository);
    }

    @Test
    @DisplayName("값이 3일 때 '폐업'을 잘 출력하는지 test")
    void businessStatus3() {
        Hospital hospital = Hospital.builder()
                .id(71857L)
                .businessStatusCode(3)
                .build();

        Mockito.when(hospitalRepository.findById(any()))
                .thenReturn(Optional.of(hospital));

        HospitalResponse closedHospitalResponse = hospitalService.getHospital(71857L);

        assertEquals("폐업", closedHospitalResponse.getBusinessStatus());
    }

    @Test
    @DisplayName("값이 13일 때 '영업중'을 잘 출력하는지 test")
    void businessStatus13() {
        Hospital hospital = Hospital.builder()
                .id(4L)
                .businessStatusCode(13)
                .build();

        Mockito.when(hospitalRepository.findById(any()))
                .thenReturn(Optional.of(hospital));

        HospitalResponse hospitalResponse = hospitalService.getHospital(4L);

        assertEquals("영업중", hospitalResponse.getBusinessStatus());
    }
}