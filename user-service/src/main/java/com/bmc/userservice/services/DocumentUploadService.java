package com.bmc.userservice.services;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class DocumentUploadService {
    private AmazonS3 s3Client;

    @Value("${document.upload.accessKey}")
    private String documentUploadAccessKey;

    @Value("${document.upload.secretKey}")
    private String documentUploadSecretKey;

    @Value("${document.upload.bucketName}")
    private String documentUploadBucketName;

    @PostConstruct
    public void init(){
        AWSCredentials credentials = new BasicAWSCredentials(documentUploadAccessKey,documentUploadSecretKey);
        s3Client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1)
                .build();
    }

    public void uploadFiles(String id, MultipartFile file) throws IOException {
        String key = id + "/"+ file.getOriginalFilename();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        if(!s3Client.doesBucketExistV2(documentUploadBucketName)){
            s3Client.createBucket(documentUploadBucketName);
        }

        s3Client.putObject(documentUploadBucketName,key,file.getInputStream(), metadata);
    }
}
