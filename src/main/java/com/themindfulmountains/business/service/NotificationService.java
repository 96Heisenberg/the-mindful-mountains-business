package com.themindfulmountains.business.service;

import com.themindfulmountains.business.payload.UserEnquiry;
import com.themindfulmountains.business.utils.EmailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class NotificationService {

    @Autowired
    private JavaMailSender emailSender;
    public void sendEnquirySubmissionNotification(UserEnquiry userEnquiry) {
        log.info("Inside Service method");

        String emailBody = EmailUtil.populateSendEnquiryEmail(userEnquiry);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("mindfulmount@gmail.com"); // Set the recipient email address
        message.setSubject("New Enquiry Submission");
        message.setText(emailBody);

        emailSender.send(message);
        log.info("Email sent successfully");

    }
}
