package com.bmc.paymentservice.services;

import com.bmc.paymentservice.dto.PaymentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentProducerService {

    @Value("${kafka.producer.topic}")
    private String producerTopic;

    private final KafkaTemplate<String, PaymentDto> kafkaTemplate;

    public void publish(PaymentDto payment) {
        System.out.println("Publishing payment: " + payment);
        kafkaTemplate.send(producerTopic, payment);
    }

}
