package com.example.projectwishlist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


//Controller for Forsiden (Home)
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }
}