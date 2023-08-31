package com.example.medicalservice.service;

import com.example.medicalservice.dto.MedicalServiceDto;
import com.example.medicalservice.dto.ResponseDto;
import com.example.medicalservice.mapper.MedicalServiceMapper;
import com.example.medicalservice.module.MedicalService;
import com.example.medicalservice.repository.MedicalServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedService {

    private final MedicalServiceRepository medicalServiceRepository;
    private final MedicalServiceMapper medicalServiceMapper;

    public ResponseDto<MedicalServiceDto> create(MedicalServiceDto dto) {
        try {
            MedicalService medicalService = medicalServiceMapper.toEntity(dto);
            medicalService.setCreatedAt(LocalDateTime.now());
            this.medicalServiceRepository.save(medicalService);
            return ResponseDto.<MedicalServiceDto>builder()
                    .success(true)
                    .message("Medical service successful created!")
                    .data(this.medicalServiceMapper.toDto(medicalService))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<MedicalServiceDto>builder()
                    .message("Medical service while saving error :: " + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<MedicalServiceDto> get(Integer id) {
        Optional<MedicalService> optional = medicalServiceRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<MedicalServiceDto>builder()
                    .message("Medical service is not found!")
                    .code(-3)
                    .data(null)
                    .build();
        }
        return ResponseDto.<MedicalServiceDto>builder()
                .success(true)
                .message("OK")
                .data(medicalServiceMapper.toDto(optional.get()))
                .build();
    }

    public ResponseDto<Set<MedicalServiceDto>> getMedicalServiceByOrdersId(Integer id) {
        Set<MedicalService> users = medicalServiceRepository.findAllByOrdersIdAndDeletedAtIsNull(id);
        if(users.isEmpty()){
            return ResponseDto.<Set<MedicalServiceDto>>builder()
                    .message("Users are not found!")
                    .code(-3)
                    .data(null)
                    .build();
        }
        return ResponseDto.<Set<MedicalServiceDto>>builder()
                .success(true)
                .message("OK")
                //to transfer Set<MedicalService> to Set<MedicalServiceDto>
                .data(users.stream().map(medicalServiceMapper::toDto).collect(Collectors.toSet()))
                .build();
    }

    public ResponseDto<MedicalServiceDto> update(MedicalServiceDto dto, Integer id) {
        Optional<MedicalService> optional = medicalServiceRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<MedicalServiceDto>builder()
                    .message("Medical service is not found!")
                    .code(-3)
                    .data(null)
                    .build();
        }
        try {
            MedicalService medicalService = optional.get();
            medicalServiceMapper.updateMedicalServiceFromDto(dto, optional.get());
            medicalService.setId(optional.get().getId());
            medicalService.setUpdatedAt(LocalDateTime.now());
            this.medicalServiceRepository.save(medicalService);
            return ResponseDto.<MedicalServiceDto>builder()
                    .success(true)
                    .message("Medical service successful updated!")
                    .data(medicalServiceMapper.toDto(medicalService))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<MedicalServiceDto>builder()
                    .message("Medical service while updating error :: " + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<MedicalServiceDto> delete(Integer id) {
        Optional<MedicalService> optional = medicalServiceRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<MedicalServiceDto>builder()
                    .message("Medical service is not found!")
                    .code(-3)
                    .data(null)
                    .build();
        }
        try {
            MedicalService medicalService = optional.get();
            medicalService.setDeletedAt(LocalDateTime.now());
            this.medicalServiceRepository.save(medicalService);
            return ResponseDto.<MedicalServiceDto>builder()
                    .success(true)
                    .message("Medical service successful deleted!")
                    .data(medicalServiceMapper.toDto(medicalService))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<MedicalServiceDto>builder()
                    .message("Medical service while deleting error :: " + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<MedicalServiceDto> getWithMedicalSphere(Integer id) {
        Optional<MedicalService> optional = this.medicalServiceRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<MedicalServiceDto>builder()
                    .message("Medical service is not found!")
                    .code(-3)
                    .data(null)
                    .build();
        }
        return ResponseDto.<MedicalServiceDto>builder()
                .success(true)
                .message("OK")
                .data(medicalServiceMapper.toDtoWithMedicalSphere(optional.get()))
                .build();
    }
}
