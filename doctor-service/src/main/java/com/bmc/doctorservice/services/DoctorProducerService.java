package com.bmc.doctorservice.services;

import com.bmc.doctorservice.entities.DoctorEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DoctorProducerService {
    @Value("${kafka.doctor.producer.topic}")
    private String producerTopic;

    private final KafkaTemplate<String, DoctorEntity> kafkaTemplate;

    public void publish(DoctorEntity doctor) {
        System.out.println("Publishing doctor: " + doctor);
        kafkaTemplate.send(producerTopic, doctor);
    }
}
