package org.launchcode.britaneygroupa.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
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

    @Value("")
    private String name;

    public void sendEmail(String toEmail,
                                String body,
                                String subject) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom(fromEmail, name);
            helper.setTo(toEmail);
            helper.setText(body, true);
            helper.setSubject("Your Subscriptions Are Ending");

            // send email
            mailSender.send(message);

            log.info("Mail Sent " + new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        } catch (Exception ex) {
            log.error("Error sending email.", ex);
        }
    }
}