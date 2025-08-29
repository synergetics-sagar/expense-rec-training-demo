package com.training.expense_rec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.expense_rec.models.Expense;
import com.training.expense_rec.service.impl.ExpensesServiceImpl;

@RestController
@RequestMapping("/expenses")
public class ExpensesController {
		
	@Autowired
	private ExpensesServiceImpl expensesService;
	
	@GetMapping
	public List<Expense> getExpenses() {
		return expensesService.getAllExpenses();
	}
	
	@PostMapping
	public ResponseEntity<?> addNewExpense(@RequestBody Expense newExpense){
		this.expensesService.addNewExpense(newExpense);
		return ResponseEntity.ok("Expense Added");
	}
}
