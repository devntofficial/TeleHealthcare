package com.bmc.doctorservice.repositories;

import com.bmc.doctorservice.entities.DoctorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends MongoRepository<DoctorEntity, String> {

    List<DoctorEntity> findTop20ByStatusOrderByAverageRatingDesc(String status);

    List<DoctorEntity> findTop20ByStatusAndSpecialityOrderByAverageRatingDesc(String status, String speciality);
}
