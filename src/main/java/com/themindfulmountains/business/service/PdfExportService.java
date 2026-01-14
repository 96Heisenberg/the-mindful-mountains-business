package com.themindfulmountains.business.service;

import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.itextpdf.html2pdf.HtmlConverter;
import com.themindfulmountains.business.model.QueryItinerary;
import com.themindfulmountains.business.repository.QueryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@Service
public class PdfExportService {

    @Autowired
    private QueryRepository repository; // Your JPA Repository

    @Autowired
    private TemplateEngine templateEngine;

    private final Storage storage = StorageOptions.getDefaultInstance().getService();

    @Value("${gcp.bucket.name}")
    private String bucketName;

    @Transactional
    public String generateAndUploadPdf(String queryId) throws IOException {
        // 1. Get Data from DB
        QueryItinerary queryItinerary = repository.findById(queryId).orElse(null);

        // 2. Prepare Thymeleaf Context
        Context context = new Context();
        context.setVariable("QueryItinerary", queryItinerary);

        // 3. Render HTML to String
        String htmlContent = templateEngine.process("query", context);

        // 4. Convert HTML to PDF (in-memory)
        ByteArrayOutputStream target = new ByteArrayOutputStream();
        HtmlConverter.convertToPdf(htmlContent, target);
        byte[] pdfBytes = target.toByteArray();

        // 5. Upload to GCP
        String fileName = "query_" + System.currentTimeMillis() + ".pdf";
        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, fileName)
                .setContentType("application/pdf")
                // ACL makes it public; ensure your bucket allows public ACLs
                //.setAcl(new ArrayList<>(Collections.singletonList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))))
                .build();

        storage.create(blobInfo, pdfBytes);
        //6. Save url in DB
        queryItinerary.setQueryPdfLink(String.format("https://storage.googleapis.com/%s/%s", bucketName, fileName));
        repository.save(queryItinerary);
        // 6. Return Public URL
        return String.format("https://storage.googleapis.com/%s/%s", bucketName, fileName);
    }
}
