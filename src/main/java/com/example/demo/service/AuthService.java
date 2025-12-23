package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.SignUpRequest;

public interface AuthService {
	void register(SignUpRequest request);

    String login(LoginRequest request);
}
