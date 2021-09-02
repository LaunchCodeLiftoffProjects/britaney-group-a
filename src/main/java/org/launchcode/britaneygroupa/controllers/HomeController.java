package org.launchcode.britaneygroupa.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/index")
    public String displayLandingPage(Model model) {
        return "index";
    }

    @GetMapping("/contact")
    public String displayContactPage(Model model) {return "contact"; }
}
