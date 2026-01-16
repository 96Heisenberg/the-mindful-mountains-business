package com.themindfulmountains.business.api;

import com.themindfulmountains.business.service.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RequestMapping("/images")
@RestController
public class ImageUploadController {

    @Autowired
    private ImageStorageService imageStorageService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImages(@RequestParam("files") MultipartFile[] files) {
        try {
            if (files == null || files.length == 0) {
                return ResponseEntity.badRequest().body("No files selected for upload.");
            }

            List<String> urls = imageStorageService.uploadToGcp(files);
            return ResponseEntity.ok(Map.of("urls", urls));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Upload failed: " + e.getMessage());
        }
    }
}