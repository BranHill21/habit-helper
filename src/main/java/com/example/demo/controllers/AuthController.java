package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import helpers.LoginRequest;
import helpers.LoginResponse;

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
	
	@PostMapping("/login")
	public ResponseEntity<?> logIn(@RequestBody LoginRequest loginRequest) {
		return ResponseEntity.ok(new LoginResponse("Login successful!", "Token"));
	}

}
