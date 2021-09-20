package org.launchcode.britaneygroupa.controllers;

import org.launchcode.britaneygroupa.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ForgotPasswordController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private User user;

    @GetMapping("/forgot_password")
    public String showForgotPasswordForm() {

    }

    @PostMapping("/forgot_password")
    public String processForgotPassword() {
    }

    public void sendEmail(){

    }


    @GetMapping("/reset_password")
    public String showResetPasswordForm() {

    }

    @PostMapping("/reset_password")
    public String processResetPassword() {

    }

}
