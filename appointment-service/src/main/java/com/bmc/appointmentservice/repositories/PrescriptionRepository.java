package com.bmc.appointmentservice.repositories;

import com.bmc.appointmentservice.entities.PrescriptionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrescriptionRepository extends MongoRepository<PrescriptionEntity, String> {
}
