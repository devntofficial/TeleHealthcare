package com.bmc.notificationservice.services;

import com.bmc.appointmentservice.entities.AppointmentEntity;
import com.bmc.appointmentservice.entities.PrescriptionEntity;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;

@RequiredArgsConstructor
@Service
public class PrescriptionConsumerService {
    private final EmailSendingService emailSendingService;

    @KafkaListener(topics = "${kafka.prescription.consumer.topic}", groupId = "${kafka.group.id}", containerFactory = "prescriptionListenerContainerFactory")
    public void listen(@Payload PrescriptionEntity prescription) throws TemplateException, MessagingException, IOException {
        System.out.println("Received prescription: " + prescription);
        emailSendingService.sendPrescriptionEmail(prescription);
        System.out.println("Prescription email sent successfully");
    }
}
