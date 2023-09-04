package com.example.medicalservice.client.service;

import com.example.medicalservice.client.dto.CentreDto;
import com.example.medicalservice.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@FeignClient(name = "centre-service",path = "/centre-service/centre")
public interface CentreClient {
    @GetMapping("/get-by-medical-service/{id}")
    ResponseDto<Set<CentreDto>> getCentreByMedicalService(@PathVariable(value = "id") Integer id);
}
