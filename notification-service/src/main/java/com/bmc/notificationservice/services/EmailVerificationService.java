package com.bmc.notificationservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Service
public class EmailVerificationService {
    @Value("${ses.user.accessKey}")
    private String sesUserAccessKey;
    @Value("${ses.user.secretKey}")
    private String sesUserSecretKey;

    private SesClient sesClient;

    @PostConstruct
    public void init(){
        StaticCredentialsProvider staticCredentialsProvider = StaticCredentialsProvider
                .create(AwsBasicCredentials.create(sesUserAccessKey,sesUserSecretKey));
        sesClient = SesClient.builder()
                .credentialsProvider(staticCredentialsProvider)
                .region(Region.US_EAST_1)
                .build();
    }

    public void sendVerificationEmailTo(String emailId) {
        sesClient.verifyEmailAddress(req -> req.emailAddress(emailId));
    }

}
