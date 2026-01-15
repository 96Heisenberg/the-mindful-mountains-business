package com.themindfulmountains.business.config;

import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class GcsConfig {
    @Bean
    public Storage storage() throws IOException {
        // This automatically finds credentials in the environment (ADC)
        // Locally: It looks for GOOGLE_APPLICATION_CREDENTIALS env var
        // On Cloud Run: It uses the built-in Service Account
        return StorageOptions.getDefaultInstance().getService();
    }

    //Local Config
//    @Bean
//    public Storage storage() throws IOException {
//        InputStream keyFile = new ClassPathResource("gcp-key.json").getInputStream();
//        return StorageOptions.newBuilder()
//                .setCredentials(GoogleCredentials.fromStream(keyFile))
//                .build()
//                .getService();
//    }
}
