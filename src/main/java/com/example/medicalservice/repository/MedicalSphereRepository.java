package com.example.medicalservice.repository;

import com.example.medicalservice.module.MedicalSphere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface MedicalSphereRepository extends JpaRepository<MedicalSphere, Integer> {
    @Query(value = "select * from medical_sphere where deleted_at is null and id = :id", nativeQuery = true)
    Optional<MedicalSphere> findByIdAndDeletedAtIsNull(@Param(value = "id") Integer id);

    Set<MedicalSphere> findAllByMedicalServiceIdAndDeletedAtIsNull(Integer id);

}