package com.training.expense_rec.service;

import java.security.Key;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.Claims;

public interface JwtService {

	public String generateToken(String subject, Map<String, Object> claims);
	
	public <T> T extractClaim(String token, Function<Claims, T> resolver);
	
	public String extractUsername(String token);
	
	public boolean isTokenValid(String token, String username);
}
