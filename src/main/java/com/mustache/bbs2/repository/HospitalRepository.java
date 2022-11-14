package com.mustache.bbs2.repository;

import com.mustache.bbs2.domain.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    List<Hospital> findByBusinessTypeNameIn(List<String> businessTypeName);
    List<Hospital> findByRoadNameAddressContaining(String str);
    List<Hospital> findByRoadNameAddressStartsWith(String str);
    List<Hospital> findByRoadNameAddressEndingWith(String str);
    List<Hospital> findByPatientRoomCountBetween(int num1, int num2);
    List<Hospital> findByPatientRoomCountBetweenOrderByPatientRoomCount(int num1, int num2);

}
