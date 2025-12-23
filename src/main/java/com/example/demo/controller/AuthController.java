package com.example.demo.controller;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.SignUpRequest;
import com.example.demo.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Auth")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@Operation(summary = "creat account")
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@Valid @RequestBody SignUpRequest request) {
		authService.register(request);
		return ResponseEntity.ok("User registered successfully");
	}

	@Operation(summary = "login user")
	@PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

	    String jwtToken = authService.login(request);

	    AuthResponse response = AuthResponse.builder()
	            .token(jwtToken)
	            .status("SUCCESS")
	            .statusCode(HttpStatus.OK.value())
	            .timestamp(Instant.now())
	            .message("Login successful")
	            .expiresAt(Instant.now().plusSeconds(3600)) // token valid for 1 hour
	            .build();

	    return ResponseEntity.ok(response);
	}
}
