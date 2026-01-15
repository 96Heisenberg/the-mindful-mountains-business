package com.themindfulmountains.business.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.cloud.storage.Storage;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class GcsConfig {

    @Value("${spring.cloud.gcp.storage.credentials.location}")
    private String gcpKey;

    @Bean
    public Storage storage() throws IOException {
        InputStream keyFile = new ClassPathResource(gcpKey).getInputStream();
        return StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(keyFile))
                .build()
                .getService();
    }
}

