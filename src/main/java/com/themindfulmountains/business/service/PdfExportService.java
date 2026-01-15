package com.themindfulmountains.business.service;

import com.google.cloud.storage.*;
import com.itextpdf.html2pdf.HtmlConverter;
import com.themindfulmountains.business.model.QueryItinerary;
import com.themindfulmountains.business.repository.QueryRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;

@Service
public class PdfExportService {

    @Autowired
    private QueryRepository repository; // Your JPA Repository

    @Autowired
    @Qualifier("stringTemplateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private Storage storage;

    @Value("${spring.cloud.gcp.bucket.name}")
    private String bucketName;

    @Value("${spring.cloud.gcp.bucket.prefix_url}")
    private String url;

    @Value("${spring.cloud.gcp.bucket.invoice}")
    private String invoice;

    @Value("${spring.cloud.gcp.bucket.template}")
    private String template;

    @Transactional
    public String generateAndUploadPdf(String queryId) throws IOException {
        // 1. Get Data from DB
        // 1. Fetch HTML Template from GCS
        // Path example: "templates/query.html"
        //comment line 53 54 if want to use context template
        QueryItinerary queryItinerary = repository.findById(queryId).orElse(null);
        Blob blob = storage.get(BlobId.of(bucketName, template));
        String htmlTemplate = new String(blob.getContent(), StandardCharsets.UTF_8);

        // 2. Prepare Thymeleaf Context
        Context context = new Context();
        context.setVariable("itinerary", queryItinerary);

        // 3. Render HTML to String
        //default templatein HTML and run in local
        //String htmlContent = templateEngine.process("query", context);
        //Template from GCS
        String htmlContent = templateEngine.process(htmlTemplate, context);

        // 4. Convert HTML to PDF (in-memory)
        ByteArrayOutputStream target = new ByteArrayOutputStream();
        HtmlConverter.convertToPdf(htmlContent, target);
        byte[] pdfBytes = target.toByteArray();

        // 5. Upload to GCP
        String fileName = invoice +"query_" + System.currentTimeMillis() + ".pdf";
        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, fileName)
                .setContentType("application/pdf")
                // ACL makes it public; ensure your bucket allows public ACLs
                //.setAcl(new ArrayList<>(Collections.singletonList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))))
                .build();

        storage.create(blobInfo, pdfBytes);
        //6. Save url in DB
        queryItinerary.setQueryPdfLink(String.format(url, bucketName, fileName));
        repository.save(queryItinerary);
        // 6. Return Public URL
        return String.format(url, bucketName, fileName);
    }
}
