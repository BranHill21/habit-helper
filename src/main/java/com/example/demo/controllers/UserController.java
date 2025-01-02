package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	@GetMapping("/home")
    public String getUserHome() {
        return "User/home"; // This corresponds to landingIndex.html
    }
}
