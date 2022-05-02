package com.test.hplus.controllers;

import com.test.hplus.beans.Login;
import com.test.hplus.beans.User;
import com.test.hplus.exceptions.ApplicationException;
import com.test.hplus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public String login(@ModelAttribute("login")Login login) {
        User user = userRepository.findByUsername(login.getUsername());
        if(user == null) {
            throw new ApplicationException("User not found");
        }
        return "search";
    }

//    @ExceptionHandler(ApplicationException.class)
//    public String handleException() {
//        System.out.println("n exception handler of Login controller");
//        return "error";
//    }

}
