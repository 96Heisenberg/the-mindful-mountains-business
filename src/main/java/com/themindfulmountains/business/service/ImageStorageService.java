package com.themindfulmountains.business.service;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImageStorageService {

    @Autowired
    private Storage storage;

    @Value("${spring.cloud.gcp.bucket.name}")
    private String bucketName;

    @Value("${spring.cloud.gcp.bucket.prefix_url}")
    private String urlTemplate; // Example: "https://storage.googleapis.com/%s/%s"

    // Optional: Define a subfolder in your bucket for images
    private final String folder = "property_images/";

    public List<String> uploadToGcp(MultipartFile[] files) throws IOException {
        List<String> uploadedUrls = new ArrayList<>();

        for (MultipartFile file : files) {
            // 1. Create a unique filename to prevent overwriting
            String originalName = file.getOriginalFilename();
            String extension = originalName != null ? originalName.substring(originalName.lastIndexOf(".")) : ".jpg";
            String fileName = folder + UUID.randomUUID().toString() + "_" + System.currentTimeMillis() + extension;

            // 2. Prepare Blob metadata
            BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, fileName)
                    .setContentType(file.getContentType())
                    .build();

            // 3. Upload bytes to GCP
            storage.create(blobInfo, file.getBytes());

            // 4. Generate and add the URL to the list
            // Assuming urlTemplate looks like: "https://storage.googleapis.com/%s/%s"
            String publicUrl = String.format(urlTemplate, bucketName, fileName);
            uploadedUrls.add(publicUrl);
        }

        return uploadedUrls;
    }
}
