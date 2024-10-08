package com.themindfulmountains.business.api;

import com.themindfulmountains.business.payload.UserEnquiry;
import com.themindfulmountains.business.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class NotificationController {

    @Autowired
    NotificationService service;

    @PostMapping("/sendEnquirySubmissionNotification")
    public ResponseEntity<String> sendEnquirySubmissionNotification(@RequestBody UserEnquiry userEnquiry){
        service.sendEnquirySubmissionNotification(userEnquiry);
        return ResponseEntity.ok("Thanks for request Submission. Our team will contact you soon!");
    }
}
