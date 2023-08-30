package com.example.medicalservice.service;

import com.example.medicalservice.dto.MedicalServiceDto;
import com.example.medicalservice.dto.MedicalSphereDto;
import com.example.medicalservice.dto.ResponseDto;
import com.example.medicalservice.mapper.MedicalSphereMapper;
import com.example.medicalservice.module.MedicalService;
import com.example.medicalservice.module.MedicalSphere;
import com.example.medicalservice.repository.MedicalSphereRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedSphereService {

    private final MedicalSphereRepository medicalSphereRepository;
    private final MedicalSphereMapper medicalSphereMapper;

    public ResponseDto<MedicalSphereDto> create(MedicalSphereDto dto) {
        try {
            MedicalSphere medicalSphere = medicalSphereMapper.toEntity(dto);
            medicalSphere.setCreatedAt(LocalDateTime.now());
            this.medicalSphereRepository.save(medicalSphere);
            return ResponseDto.<MedicalSphereDto>builder()
                    .success(true)
                    .message("Medical sphere successful created!")
                    .data(medicalSphereMapper.toDto(medicalSphere))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<MedicalSphereDto>builder()
                    .message("Medical sphere while saving error :: " + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<MedicalSphereDto> get(Integer id) {
        Optional<MedicalSphere> optional = medicalSphereRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<MedicalSphereDto>builder()
                    .message("Medical sphere is not found!")
                    .code(-3)
                    .data(null)
                    .build();
        }
        return ResponseDto.<MedicalSphereDto>builder()
                .success(true)
                .message("OK")
                .data(medicalSphereMapper.toDto(optional.get()))
                .build();
    }

    public ResponseDto<MedicalSphereDto> update(MedicalSphereDto dto, Integer id) {
        Optional<MedicalSphere> optional = medicalSphereRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<MedicalSphereDto>builder()
                    .message("Medical sphere is not found!")
                    .code(-3)
                    .data(null)
                    .build();
        }
        try {
            MedicalSphere medicalSphere = optional.get();
            medicalSphereMapper.updateMedicalServiceFromDto(dto, optional.get());
            medicalSphere.setId(optional.get().getId());
            medicalSphere.setUpdatedAt(LocalDateTime.now());
            this.medicalSphereRepository.save(medicalSphere);
            return ResponseDto.<MedicalSphereDto>builder()
                    .success(true)
                    .message("Medical sphere successful updated!")
                    .data(medicalSphereMapper.toDto(medicalSphere))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<MedicalSphereDto>builder()
                    .message("Medical sphere while updating error :: " + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<MedicalSphereDto> delete(Integer id) {
        Optional<MedicalSphere> optional = medicalSphereRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<MedicalSphereDto>builder()
                    .message("Medical sphere is not found!")
                    .code(-3)
                    .data(null)
                    .build();
        }
        try {
            MedicalSphere medicalSphere = optional.get();
            medicalSphere.setDeletedAt(LocalDateTime.now());
            this.medicalSphereRepository.save(medicalSphere);
            return ResponseDto.<MedicalSphereDto>builder()
                    .success(true)
                    .message("Medical sphere successful deleted!")
                    .data(medicalSphereMapper.toDto(medicalSphere))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<MedicalSphereDto>builder()
                    .message("Medical sphere while deleting error :: " + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<Set<MedicalSphereDto>> getMedicalSphereByMedicalServiceId(Integer id) {
        Set<MedicalSphere> medicalSphere = medicalSphereRepository.findAllByMedicalServiceIdAndDeletedAtIsNull(id);
        if(medicalSphere.isEmpty()){
            return ResponseDto.<Set<MedicalSphereDto>>builder()
                    .message("Medical sphere is not found!")
                    .code(-3)
                    .data(null)
                    .build();
        }
        return ResponseDto.<Set<MedicalSphereDto>>builder()
                .message("OK")
                .success(true)
                .data(medicalSphere.stream().map(medicalSphereMapper::toDto).collect(Collectors.toSet()))
                .build();
    }
}