package com.training.expense_rec.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.expense_rec.dto.AuthResponse;
import com.training.expense_rec.dto.LoginRequest;
import com.training.expense_rec.dto.RegisterRequest;
import com.training.expense_rec.service.impl.AuthServiceImpl;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private final AuthServiceImpl authService;
	
	public AuthController(AuthServiceImpl authService) {
		this.authService = authService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest request){
		authService.register(request);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest req) {
	    String token = authService.login(req);
	    return ResponseEntity.ok(new AuthResponse(token));
	}

}
