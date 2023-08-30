package com.example.medicalservice.controller;

import com.example.medicalservice.dto.MedicalSphereDto;
import com.example.medicalservice.dto.ResponseDto;
import com.example.medicalservice.service.MedSphereService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medical-sphere")
@RequiredArgsConstructor
public class MedicalSphereController {

    private final MedSphereService medSphereService;

    @PostMapping("/create")
    public ResponseDto<MedicalSphereDto> create(@RequestBody MedicalSphereDto dto){
        return medSphereService.create(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseDto<MedicalSphereDto> get(@PathVariable("id") Integer id){
        return medSphereService.get(id);
    }

    @PutMapping("/update/{id}")
    public ResponseDto<MedicalSphereDto> update(@RequestBody MedicalSphereDto dto, @PathVariable("id") Integer id){
        return medSphereService.update(dto,id);
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseDto<MedicalSphereDto> delete(@PathVariable("id") Integer id){
        return medSphereService.delete(id);
    }

}