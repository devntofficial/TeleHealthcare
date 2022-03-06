package com.bmc.notificationservice.services;

import com.bmc.userservice.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserConsumerService {
    private final EmailVerificationService emailVerificationService;

    @KafkaListener(topics = "${kafka.user.consumer.topic}", groupId = "${kafka.group.id}", containerFactory = "userListenerContainerFactory")
    public void listen(@Payload UserEntity user)
    {
        System.out.println("Received user: " + user);
        emailVerificationService.sendVerificationEmailTo(user.getEmailId());
        System.out.println("Verification email sent successfully");
    }
}
