package com.bmc.notificationservice.services;

import com.bmc.appointmentservice.entities.AppointmentEntity;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;

@RequiredArgsConstructor
@Service
public class AppointmentConsumerService {
    private final EmailSendingService emailSendingService;

    @KafkaListener(topics = "${kafka.appointment.consumer.topic}", groupId = "${kafka.group.id}", containerFactory = "appointmentListenerContainerFactory")
    public void listen(@Payload AppointmentEntity appointment) throws TemplateException, MessagingException, IOException {
        System.out.println("Received appointment: " + appointment);
        emailSendingService.sendAppointmentConfirmedEmail(appointment);
        System.out.println("Appointment confirmation email sent successfully");
    }
}
