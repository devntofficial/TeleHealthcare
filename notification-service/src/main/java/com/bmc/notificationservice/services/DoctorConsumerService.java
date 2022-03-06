package com.bmc.notificationservice.services;

import com.bmc.doctorservice.entities.DoctorEntity;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;

@RequiredArgsConstructor
@Service
public class DoctorConsumerService {

    private final EmailVerificationService emailVerificationService;
    private final EmailSendingService emailSendingService;

    @KafkaListener(topics = "${kafka.doctor.consumer.topic}", groupId = "${kafka.group.id}", containerFactory = "doctorListenerContainerFactory")
    public void listen(@Payload DoctorEntity doctor) throws IOException, TemplateException, MessagingException {
        System.out.println("Received doctor: " + doctor);
        String status = doctor.getStatus();

        if(status.equals("Pending")) {
            System.out.println("Sending doctor email verification to " + doctor.getEmailId());
            emailVerificationService.sendVerificationEmailTo(doctor.getEmailId());
            System.out.println("Verification email sent successfully");
        }
        else if(status.equals("Active")) {
            System.out.println("Sending doctor approval request accepted email to " + doctor.getEmailId());
            emailSendingService.sendApprovedEmailTo(doctor);
            System.out.println("Doctor approval request accepted email sent successfully");
        }
        else if(status.equals("Rejected")) {
            System.out.println("Sending doctor approval request rejected email to " + doctor.getEmailId());
            emailSendingService.sendRejectedEmailTo(doctor);
            System.out.println("Doctor approval request rejected email sent successfully");
        }
    }
}
