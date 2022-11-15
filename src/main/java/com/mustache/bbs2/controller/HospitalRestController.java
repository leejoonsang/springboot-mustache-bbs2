package com.mustache.bbs2.controller;

import com.mustache.bbs2.domain.dto.HospitalResponse;
import com.mustache.bbs2.domain.entity.Hospital;
import com.mustache.bbs2.repository.HospitalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/hospitals")
public class HospitalRestController {

    private final HospitalRepository hospitalRepository;

    public HospitalRestController(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponse> get(@PathVariable Long id) {
        Optional<Hospital> optHospital = hospitalRepository.findById(id);
        HospitalResponse hospitalResponse = Hospital.of(optHospital.get());
        return ResponseEntity.ok().body(hospitalResponse);
    }
}
