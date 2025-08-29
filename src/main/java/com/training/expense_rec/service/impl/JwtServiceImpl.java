package com.training.expense_rec.service.impl;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.training.expense_rec.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService{
	
	@Value("${jwt.secret}")
	private String secretBase64;
	
	@Value("${jwt.expiration}")
	private long expirationSeconds;
	
	private Key signingKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretBase64);
		return (Key) Keys.hmacShaKeyFor(keyBytes);
	}
	
	public String generateToken(String subject, Map<String, Object> claims) {
		Date now = new Date();
		Date expiry = new Date(now.getTime() + (expirationSeconds*1000));
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(now)
				.setExpiration(expiry)
				.signWith(signingKey(), SignatureAlgorithm.HS256)
				.compact();
		
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> resolver) {
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(signingKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
		return resolver.apply(claims);
	}
	
	public String extractUsername(String token) {
			return extractClaim(token, Claims::getSubject);
	}
	
	public boolean isTokenValid(String token, String username) {
		String subject = extractUsername(token);
		Date exp = extractClaim(token, Claims::getExpiration);
		return subject.equals(username) && exp.after(new Date());
	}
}
