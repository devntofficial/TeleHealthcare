package com.bmc.ratingservice.services;

import com.bmc.ratingservice.entities.RatingEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RatingProducerService {
    @Value("${kafka.producer.topic}")
    private String producerTopic;

    private final KafkaTemplate<String, RatingEntity> kafkaTemplate;

    public void publish(RatingEntity doctorRating) {
        System.out.println("Publishing doctor rating: " + doctorRating);
        kafkaTemplate.send(producerTopic, doctorRating);
    }
}
