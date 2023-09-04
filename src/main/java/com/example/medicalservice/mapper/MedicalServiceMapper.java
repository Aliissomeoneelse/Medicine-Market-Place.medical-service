package com.example.medicalservice.mapper;

import com.example.medicalservice.client.service.CentreClient;
import com.example.medicalservice.dto.MedicalServiceDto;
import com.example.medicalservice.module.MedicalService;
import com.example.medicalservice.service.MedSphereService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class MedicalServiceMapper {

    @Autowired
    protected MedSphereService medSphereService;
    @Autowired
    protected CentreClient centreClient;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract MedicalService toEntity(MedicalServiceDto dto);

    @Mapping(target = "medicalSpheres", ignore = true)
    public abstract MedicalServiceDto toDto(MedicalService medicalService);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateMedicalServiceFromDto(MedicalServiceDto dto, @MappingTarget MedicalService medicalService);

    @Mapping(target = "medicalSpheres", expression = "java(medSphereService.getMedicalSphereByMedicalServiceId(medicalService.getId()).getData())")
    public abstract MedicalServiceDto toDtoWithMedicalSphere(MedicalService medicalService);

    @Mapping(target = "centre", expression = "java(centreClient.getCentreByMedicalService(medicalService.getId()).getData())")
    public abstract MedicalServiceDto toDtoWithCentre(MedicalService medicalService);
}