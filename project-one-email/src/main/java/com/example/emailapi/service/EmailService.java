package com.example.emailapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * EmailService.java
 * Service class for email
 *
 * @author Jia ying Li
 */
@Service
public class EmailService {

    private static final String PERSONAL = "gmail.com";
    private JavaMailSender emailSender;
    String message = null;

    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    /**
     * creating a method for sending email
     *
     * @param toCC employee email address
     * @param subject the title of the email
     * @param text the content of the email
     */
    public void sendEmail(String toCC, String subject, String text) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
            message.setFrom("emailapi302@gmail.com");
            message.setTo(toCC);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    /**
     * template of email content
     *
     * @param employeeName employees' name
     * @param status The status of employees reimbursement request
     * @return the email content template
     */
    public String templateMail(String employeeName, String status) {
        message ="Dear " + employeeName + ", your reimbursement request have been " + status;
        return message;
    }

}
