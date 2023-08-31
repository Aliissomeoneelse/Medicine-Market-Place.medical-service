package com.example.medicalservice.controller;

import com.example.medicalservice.dto.MedicalServiceDto;
import com.example.medicalservice.dto.ResponseDto;
import com.example.medicalservice.service.MedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("medical-service")
public class MedicalServiceController {
    private final MedService medService;

    @PostMapping("/create")
    public ResponseDto<MedicalServiceDto> create(@RequestBody MedicalServiceDto dto){
        return medService.create(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseDto<MedicalServiceDto> get(@PathVariable("id") Integer id){
        return medService.get(id);
    }

    @GetMapping("/get-with-medical-sphere/{id}")
    public ResponseDto<MedicalServiceDto> getWithMedicalSphere(@PathVariable("id") Integer id){
        return medService.getWithMedicalSphere(id);
    }

    @GetMapping(value = "/get-medical-service-by-orders/{id}")
    public ResponseDto<Set<MedicalServiceDto>> getMedicalServiceByOrdersId(@PathVariable("id") Integer id){
        return medService.getMedicalServiceByOrdersId(id);
    }

    @PutMapping("/update/{id}")
    public ResponseDto<MedicalServiceDto> update(@RequestBody MedicalServiceDto dto, @PathVariable("id") Integer id){
        return medService.update(dto,id);
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseDto<MedicalServiceDto> delete(@PathVariable("id") Integer id){
        return medService.delete(id);
    }


}