package com.example.medicalservice.dto;

import com.example.medicalservice.client.dto.CentreDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicalServiceDto {
    private Integer id;
    @NotBlank(message = "Name cannot be null or empty")
    private String name;
    private Double price;
    private String description;

    private Set<MedicalSphereDto> medicalSpheres;
    private Set<CentreDto> centre;
    private Integer ordersId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}