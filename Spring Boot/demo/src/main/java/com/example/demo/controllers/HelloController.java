package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("/hello") //http://localhost:8080/hello?name=Dolly
    public String sayHello(@RequestParam(required = false,
            defaultValue = "World") String name, Model model) {

        // Spring takes keys and values from the model and adds them
        //to the HTTP request
        model.addAttribute("user", name);

        // Spring forwards to /templates/<name>.html
        // using the Thymeleaf view resolver
        return "hello";
    }

}
