package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
	
	@GetMapping("/signup")
	public String getSignUpPage() {
		return "Auth/signup";
	}
	
	@GetMapping("/login")
	public String getLogInPage() {
		return "Auth/login";
	}

}
