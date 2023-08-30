package com.example.medicalservice.mapper;

import com.example.medicalservice.dto.MedicalSphereDto;
import com.example.medicalservice.module.MedicalSphere;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class MedicalSphereMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract MedicalSphere toEntity(MedicalSphereDto dto);

    public abstract MedicalSphereDto toDto(MedicalSphere medicalSphere);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateMedicalServiceFromDto(MedicalSphereDto dto, @MappingTarget MedicalSphere medicalSphere);
}
