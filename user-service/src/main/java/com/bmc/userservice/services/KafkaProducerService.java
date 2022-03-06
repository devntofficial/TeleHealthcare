package com.bmc.userservice.services;

import com.bmc.userservice.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KafkaProducerService {
    @Value("${kafka.user.producer.topic}")
    private String producerTopic;

    private final KafkaTemplate<String, UserEntity> kafkaTemplate;

    public void publish(UserEntity user) {
        System.out.println("Publishing user: " + user);
        kafkaTemplate.send(producerTopic, user);
    }
}
