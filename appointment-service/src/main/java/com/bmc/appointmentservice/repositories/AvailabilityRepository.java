package com.bmc.appointmentservice.repositories;

import com.bmc.appointmentservice.entities.AvailabilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AvailabilityRepository extends JpaRepository<AvailabilityEntity, Long> {
    boolean existsByDoctorId(String doctorId);
    List<AvailabilityEntity> findByDoctorId(String doctorId);
    AvailabilityEntity findByDoctorIdAndAvailabilityDateAndTimeSlot(String doctorId, Date availabilityDate, String timeSlot);
}
