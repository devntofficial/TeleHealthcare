package com.bmc.appointmentservice.repositories;

import com.bmc.appointmentservice.entities.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, String> {
    List<AppointmentEntity> findByUserId(String userId);
}
