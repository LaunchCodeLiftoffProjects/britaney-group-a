package org.launchcode.britaneygroupa.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class EmailReminderSender {

    private static final Logger log = LoggerFactory.getLogger(EmailReminderSender.class);

    @Autowired
    private JavaMailSender mailSender;

    //referencing this instead of hardcoding the email address in "message.setFrom" below
    @Value("mail.username")
    private String fromEmail;

    public void sendSimpleEmail(String toEmail,
                                String body,
                                String subject) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject("Your Subscriptions Are Ending");

        mailSender.send(message);
        log.info("Mail Sent " + new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
    }
}