package com.training.expense_rec.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.expense_rec.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);
	boolean existsByEmail(String email);
}
