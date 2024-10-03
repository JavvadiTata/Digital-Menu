package com.karthiksoft.digitalmenu.digital_menu.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Service
public class S3Service {

    private final S3Client s3Client;
    private final String bucketName;

    public S3Service(@Value("${aws.access-key-id}") String accessKeyId,
                     @Value("${aws.secret-access-key}") String secretAccessKey,
                     @Value("${aws.s3.region}") String region,
                     @Value("${aws.s3.bucket-name}") String bucketName) {

        // Check if any value is missing or null (this can help pinpoint the issue)
        if (accessKeyId == null || secretAccessKey == null || region == null || bucketName == null) {
            throw new IllegalArgumentException("AWS credentials or configuration not set properly");
        }

        // Initialize S3 client
        this.s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKeyId, secretAccessKey)
                ))
                .build();

        this.bucketName = bucketName;
    }

    // Other methods for uploading and retrieving files



    public String uploadFile(MultipartFile file) throws IOException {
        String key = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            s3Client.putObject(putObjectRequest, Paths.get(file.getOriginalFilename()));
        } catch (S3Exception e) {
            throw new IOException("Failed to upload file", e);
        }

        return getFileUrl(key);
    }

    public String getFileUrl(String key) {
        S3Presigner presigner = S3Presigner.create();
        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofDays(7))  // Set how long the link should be valid
                .getObjectRequest(b -> b.bucket(bucketName).key(key))
                .build();

        return presigner.presignGetObject(presignRequest).url().toString();
    }
}
