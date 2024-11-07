package com.example.service;

import com.example.model.CepLog;
import com.example.repository.CepLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
public class CepService {

    @Autowired
    private CepLogRepository cepLogRepository;

    private final String API_URL = "http://localhost:8080/cep/";
    private S3Client s3Client;
    private final String bucketName;

    @Autowired
    private RestTemplate restTemplate;

    public CepService() {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(
                System.getenv("AWS_ACCESS_KEY_ID"),
                System.getenv("AWS_SECRET_ACCESS_KEY")
        );

        this.s3Client = S3Client.builder()
                .region(Region.of(System.getenv("AWS_REGION")))
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();

        this.bucketName = System.getenv("AWS_S3_BUCKET_NAME");
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void setCepLogRepository(CepLogRepository cepLogRepository) {
        this.cepLogRepository = cepLogRepository;
    }

    public void setS3Client(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String buscarCep(String cep) {
        String response = restTemplate.getForObject(API_URL + cep, String.class);

        CepLog log = new CepLog();
        log.setCep(cep);
        log.setResponse(response);
        log.setTimestamp(LocalDateTime.now());
        cepLogRepository.save(log);

        saveLogToS3(log);

        return response;
    }

    private void saveLogToS3(CepLog log) {
        String logContent = "CEP: " + log.getCep() + "\nResponse: " + log.getResponse() + "\nTimestamp: " + log.getTimestamp();
        InputStream logStream = new ByteArrayInputStream(logContent.getBytes(StandardCharsets.UTF_8));

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key("logs/" + log.getId() + ".txt")
                .build();

        s3Client.putObject(putObjectRequest, logStream);
    }
}