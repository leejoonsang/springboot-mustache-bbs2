package com.mustache.bbs2.repository;

import com.mustache.bbs2.domain.entity.Hospital;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HospitalRepositoryTest {
    @Autowired
    HospitalRepository hospitalRepository;

    @Test
    @DisplayName("출력하는 코드 분리")
    void printHospitalNameAndAddress(List<Hospital> hospitals) {
        for(var hospital : hospitals){
            System.out.printf("%s | %s\n", hospital.getHospitalName(), hospital.getRoadNameAddress());
        }

        System.out.println(hospitals.size());
    }

    @Test
    @DisplayName("BusinessTypeName 보건소, 보건지소, 보건진료소가 잘 나오는지 test")
    void findByBusinessTypeNameIn() {
        List<String> inClues = new ArrayList<>();
        inClues.add("보건소");
        inClues.add("보건지소");
        inClues.add("보건진료소");
        List<Hospital> hospitals = hospitalRepository.findByBusinessTypeNameIn(inClues);
        for(var hospital : hospitals){
            System.out.println(hospital.getHospitalName());
        }
    }

    @Test
    @DisplayName("특정 지역을 포함하는 병원이 잘 나오는지 test")
    void containing() {
        List<Hospital> hospitals = hospitalRepository.findByRoadNameAddressContaining("광진구");
        printHospitalNameAndAddress(hospitals);
    }

    @Test
    @DisplayName("병상 수가 10~20개인 병원 찾기 test")
    void between() {
        List<Hospital> hospitals = hospitalRepository.findByPatientRoomCountBetween(10, 20);
        printHospitalNameAndAddress(hospitals);
    }

    @Test
    @DisplayName("병상 수가 10~20개인 병원을 병상 수 오름차순으로 정렬 test")
    void betweenAndOrderBy() {
        List<Hospital> hospitals = hospitalRepository.findByPatientRoomCountBetweenOrderByPatientRoomCount(10, 20);
        printHospitalNameAndAddress(hospitals);
    }

}