package com.murtazin.bootstrap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

//    @GetMapping
//    public String home(Model model) {
//        model.addAttribute("massage", "Welcome to the HOME page");
//        return "home";
//    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }
}
