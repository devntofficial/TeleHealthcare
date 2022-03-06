package com.bmc.ratingservice.services;

import com.bmc.ratingservice.dto.RatingDto;
import com.bmc.ratingservice.entities.RatingEntity;
import com.bmc.ratingservice.repositories.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RatingService {
    private final RatingRepository repository;
    private final RatingProducerService kafkaProducerService;

    public void saveDoctorRating(RatingDto ratingDto) {
        RatingEntity rating = ratingDto.mapToEntity();
        repository.save(rating);
        kafkaProducerService.publish(rating);
    }
}
