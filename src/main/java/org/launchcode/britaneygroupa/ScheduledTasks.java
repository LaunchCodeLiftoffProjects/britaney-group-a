package org.launchcode.britaneygroupa;

import com.mysql.cj.log.Log;
import org.launchcode.britaneygroupa.controllers.EmailReminderSender;
import org.launchcode.britaneygroupa.models.Product;
import org.launchcode.britaneygroupa.models.data.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private EmailReminderSender emailSender;

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        List<Product> expiringProducts = productRepository.findAllByDateOfExpiryAndNotifiedIsNull(new Date());
        log.info(String.valueOf(expiringProducts));String subject = "Here's the link to reset your password";

        for (Product expiringProduct : expiringProducts) {

            String content = "<p>Hello,</p>"
                    + "<p>You have requested to reset your password.</p>"
                    + "<p>Click the link below to change your password:</p>"
                    + "<br>"
                    + "<p>Ignore this email if you do remember your password, "
                    + "or you have not made the request.</p>";

            try {
                // Send email to user
                emailSender.sendSimpleEmail(expiringProduct.getUser().getEmail(), content, subject);

                // update user product indicating notification is sent
                expiringProduct.setNotified(Boolean.TRUE);
                productRepository.save(expiringProduct);
            } catch (Throwable ex) {

                log.error(String.format("Error sending email to user '%s' for product '%s'", expiringProduct.getUser().getEmail(), expiringProduct.getId()));
            }
        }
    }
}
