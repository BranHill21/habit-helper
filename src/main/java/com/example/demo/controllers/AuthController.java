package com.example.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import helpers.LoginRequest;
import helpers.LoginResponse;
import services.AuthService;

@Controller
public class AuthController {
	private AuthService authService = new AuthService();
	
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
		if(authService.attemptLogin(loginRequest)){
			return ResponseEntity.ok(new LoginResponse("Login successful!", "Token"));
		}
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
		}
	}

}
