package com.bmc.appointmentservice.services;

import com.bmc.appointmentservice.entities.PrescriptionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrescriptionProducerService {
    @Value("${kafka.prescription.producer.topic}")
    private String producerTopic;

    private final KafkaTemplate<String, PrescriptionEntity> kafkaTemplate;

    public void publish(PrescriptionEntity prescription) {
        System.out.println("Publishing prescription: " + prescription);
        kafkaTemplate.send(producerTopic, prescription);
    }
}
