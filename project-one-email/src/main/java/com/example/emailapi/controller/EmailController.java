package com.example.emailapi.controller;

import com.example.emailapi.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.List;

/**
 * EmailController.java
 * Email controller for sending out emails to employees
 *
 * @author Jia ying Li
 */
@RestController
@RequestMapping("mail")
public class EmailController {

    private EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailController.class);

    /**
     * Sending out email
     *
     * @param list A string list with employees' name, email address and their reimbursement status
     * @return Accepted status
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendEmail(@RequestBody List<String> list) {
        LOGGER.info("Received request. Sending out the email. ");

        emailService.sendEmail(list.get(1), "Reimbursement Update",
                emailService.templateMail(list.get(0),list.get(2)));

        //return accepted
        return ResponseEntity.accepted().build();
    }

}
