package com.example.medicalservice.repository;

import com.example.medicalservice.module.MedicalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface MedicalServiceRepository extends JpaRepository<MedicalService,Integer> {
    @Query(value = "select * from medical_service where deleted_at is null and id = :id", nativeQuery = true)
    Optional<MedicalService> findByIdAndDeletedAtIsNull(@Param(value = "id") Integer id);

    Set<MedicalService> findAllByOrdersIdAndDeletedAtIsNull(Integer ordersId);

}