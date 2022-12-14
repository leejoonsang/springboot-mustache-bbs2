package com.mustache.bbs2.controller;

import com.mustache.bbs2.domain.dto.HospitalResponse;
import com.mustache.bbs2.service.HospitalService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HospitalRestController.class)
class HospitalRestControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    HospitalService hospitalService;

    @Test
    @DisplayName("1개의 Json 형태로 response 가 잘 오는지 test")
    void jsonResponse() throws Exception {
        HospitalResponse hospitalResponse = HospitalResponse.builder()
                .id(2321L)
                .hospitalName("노소아청소년과의원")
                .roadNameAddress("서울특별시 서초구 서초중앙로 230, 202호 (반포동, 동화반포프라자빌딩)")
                .phone("534-6938")
                .businessTypeName("의원")
                .patientRoomCount(0)
                .businessStatus("의원")
                .build();
        given(hospitalService.getHospital(2321L))
                .willReturn(hospitalResponse);

        int hospitalId = 2321;
        String url = String.format("/api/v1/hospitals/%d", hospitalId);
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hospitalName").exists())
                .andExpect(jsonPath("$.hospitalName").value("노소아청소년과의원"))
                .andDo(print());

        verify(hospitalService).getHospital(2321L);
    }
}