package org.launchcode.britaneygroupa;

import com.mysql.cj.log.Log;
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

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        List<Product> expiringProducts = productRepository.findAllByDateOfExpiry(new Date());
        log.info(String.valueOf(expiringProducts));
    }
}
