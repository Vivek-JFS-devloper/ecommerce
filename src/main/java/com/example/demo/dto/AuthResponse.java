package com.example.demo.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AuthResponse {

	private String token; // JWT token
	private String status; // HTTP status e.g., "SUCCESS"
	private int statusCode; // HTTP status code e.g., 200
	private Instant timestamp; // When token is issued
	private String message; // Optional message
	private Instant expiresAt; // Token expiry time
}
