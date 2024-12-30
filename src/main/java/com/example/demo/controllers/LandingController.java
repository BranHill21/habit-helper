package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandingController {
	
	@GetMapping("/")
    public String showLandingPage() {
        return "landingIndex"; // This corresponds to landingIndex.html
    }
}
