package com.training.expense_rec.service;

import com.training.expense_rec.dto.LoginRequest;
import com.training.expense_rec.dto.RegisterRequest;

public interface AuthService {
	
	public void register(RegisterRequest request);
	
	public String login(LoginRequest request);
	
}
