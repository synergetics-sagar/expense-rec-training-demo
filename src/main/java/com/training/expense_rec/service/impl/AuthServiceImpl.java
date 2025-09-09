package com.training.expense_rec.service.impl;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.training.expense_rec.dao.UserRepository;
import com.training.expense_rec.dto.LoginRequest;
import com.training.expense_rec.dto.RegisterRequest;
import com.training.expense_rec.enums.Role;
import com.training.expense_rec.models.User;
import com.training.expense_rec.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
	
	
	private final UserRepository userRepository;
	private final PasswordEncoder encoder;
	private final AuthenticationManager authManager;
	private final JwtServiceImpl jwtService;
	
	public AuthServiceImpl(UserRepository userRepository, PasswordEncoder encoder, AuthenticationManager authManager,
			JwtServiceImpl jwtService) {
		super();
		this.userRepository = userRepository;
		this.encoder = encoder;
		this.authManager = authManager;
		this.jwtService = jwtService;
	}
	
	public void register(RegisterRequest request) {
		if(userRepository.existsByEmail(request.email())) throw new RuntimeException("Email already in use");
	    var user = new User(request.name(), request.email(), encoder.encode(request.password()), Role.USER);
		userRepository.save(user);
	}
	
	public String login(LoginRequest request) {
		System.out.println("|||||||||||AuthService.login()-1|||||||||||");
		var authToken = new UsernamePasswordAuthenticationToken(request.email(), request.password());
		authManager.authenticate(authToken);
		var user = userRepository.findByEmail(request.email()).orElseThrow();
		System.out.println("|||||||||||AuthService.login()-2|||||||||||");

		return jwtService.generateToken(user.getUsername(), Map.of("role", user.getRole().name()));
	}
	
	
}
