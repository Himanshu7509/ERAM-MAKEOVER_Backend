package com.Elite.Erum_Makeover.Services;

import com.Elite.Erum_Makeover.Model.Image;
import com.Elite.Erum_Makeover.Repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Service
public class S3Service {

    @Autowired
    private S3Client s3Client;
    @Autowired
    private ImageRepository imageRepository;


    @Value("${aws.bucket-name}")
    private String bucketName;

    public S3Service(S3Client s3Client, ImageRepository imageRepository) {
        this.s3Client = s3Client;
        this.imageRepository = imageRepository;
    }

    public String uploadFile(MultipartFile file, String folderName) {

        try {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            String key = "ErumMakeover/" + folderName + "/" + fileName;

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .contentType(file.getContentType())
                    .build();

            s3Client.putObject(
                    putObjectRequest,
                    RequestBody.fromBytes(file.getBytes())
            );

            String imageUrl = "https://" + bucketName + ".s3.amazonaws.com/" + key;

            Image image = new Image(fileName, imageUrl);
            imageRepository.save(image);

            return imageUrl;

        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file to S3", e);
        }
    }
    }
