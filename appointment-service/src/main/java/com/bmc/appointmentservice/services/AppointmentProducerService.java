package com.bmc.appointmentservice.services;

import com.bmc.appointmentservice.entities.AppointmentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AppointmentProducerService {
    @Value("${kafka.appointment.producer.topic}")
    private String producerTopic;

    private final KafkaTemplate<String, AppointmentEntity> kafkaTemplate;

    public void publish(AppointmentEntity appointment) {
        System.out.println("Publishing appointment: " + appointment);
        kafkaTemplate.send(producerTopic, appointment);
    }
}
