package org.launchcode.britaneygroupa.controllers;

import org.launchcode.britaneygroupa.models.User;
import org.launchcode.britaneygroupa.models.data.UserRepository;
import org.launchcode.britaneygroupa.models.dto.ForgotPasswordFormDTO;
import org.launchcode.britaneygroupa.models.dto.LoginFormDTO;
import org.launchcode.britaneygroupa.models.dto.RegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;

    private static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session) {
        User user = (User) session.getAttribute(userSessionKey);
        if (user == null) {
            return null;
        }

        return user;
    }

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user);
    }

    @GetMapping("/register")
    public String displayRegistrationForm(Model model) {
        model.addAttribute(new RegisterFormDTO());
        model.addAttribute("title", "Register");
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute @Valid RegisterFormDTO registerFormDTO,
                                          Errors errors, HttpServletRequest request, Model model) {

        if(errors.hasErrors()) {
            model.addAttribute("title", "Register");
            return "register";
        }

        User existingUser = userRepository.findByUserName(registerFormDTO.getUserName());

        if(existingUser != null) {
            errors.rejectValue("userName", "userName.alreadyexists", "A user with the username already exists");
            model.addAttribute("title", "Register");
            return "register";
        }

        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();
        if(!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Register");
            return "register";
        }

        User newUser = new User(registerFormDTO.getFirstName(), registerFormDTO.getLastName(), registerFormDTO.getEmail(), registerFormDTO.getUserName(), registerFormDTO.getPassword());
        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);

        return "redirect:/products";
    }

    @GetMapping("/login")
    public String displayLoginForm(Model model) {
        model.addAttribute(new LoginFormDTO());
        model.addAttribute("title", "Log In");
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
                                   Errors errors, HttpServletRequest request,
                                   Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Log In");
            return "login";
        }

        User theUser = userRepository.findByUserName(loginFormDTO.getUserName());

        if (theUser == null) {
            errors.rejectValue("userName", "user.invalid", "The given username does not exist");
            model.addAttribute("title", "Log In");
            return "login";
        }

        String password = loginFormDTO.getPassword();

        if (!theUser.isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
            model.addAttribute("title", "Log In");
            return "login";
        }

        setUserInSession(request.getSession(), theUser);

        return "redirect:/products";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "index";
    }

    @GetMapping("/forgotPassword")
    public String displayForgotPasswordForm(Model model) {
        model.addAttribute(new ForgotPasswordFormDTO());
        model.addAttribute("title", "Forgot Password");
        return "forgotPassword";
    }

    @PostMapping("/forgotPassword")
    public String processForgotPasswordForm(Model model){
        model.addAttribute(new ForgotPasswordFormDTO());
        return "forgotPassword";
    }

}
