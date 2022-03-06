package com.bmc.doctorservice.services;

import com.bmc.doctorservice.entities.DoctorEntity;
import com.bmc.doctorservice.repositories.DoctorRepository;
import com.bmc.ratingservice.entities.RatingEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RatingConsumerService {

    private final DoctorRepository repository;

    @KafkaListener(topics = "${kafka.rating.consumer.topic}", groupId = "${kafka.group.id}", containerFactory = "ratingListenerContainerFactory")
    public void listen(@Payload RatingEntity doctorRating)
    {
        System.out.println("Received doctor rating: " + doctorRating);
        DoctorEntity doctor = repository.findById(doctorRating.getDoctorId()).orElse(null);
        if(doctor == null) {
            System.out.println("No doctor found with given id. Ignoring message.");
        }
        else {
            Double existingRating = doctor.getAverageRating();
            Double newRating = existingRating == null || existingRating == 0.0 ? doctorRating.getRating() : ((existingRating + doctorRating.getRating())/2);
            doctor.setAverageRating(newRating);
            repository.save(doctor);
        }
        System.out.println("Average doctor rating updated successfully");
    }
}
