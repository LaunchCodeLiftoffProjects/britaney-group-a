package org.launchcode.britaneygroupa.controllers;

import org.launchcode.britaneygroupa.models.Product;
import org.launchcode.britaneygroupa.models.User;
import org.launchcode.britaneygroupa.models.data.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReminderEmailBuilder {
    @Autowired
    ProductRepository productRepository;

    @GetMapping (value = "/sendemails")
    public void sendEmails() {
        List<Product> listOfExpiringProducts = productRepository.findExpiringItems();
        Integer currentUserId = null;
        List<Product> tempList = new ArrayList<>();

        for (Product p: listOfExpiringProducts) {
            if (currentUserId == null){
                tempList.add(p);
            } else {
                if (p.getUserId() == currentUserId){
                    tempList.add(p);
                } else{
                    emailFiller();
                    tempList.clear();
                    tempList.add(p);
                }
            }
        }
        emailFiller();
    }

    public void emailFiller (){
        //To Do: format body and send email as above -- this is capturing the last user who would otherwise be left out of the loop
        EmailReminderSender ers = new EmailReminderSender();
        //ers.sendSimpleEmail(/* TODO Fill in your parameters (subject, to, body, etc.) same as above.*/);
    }

}
