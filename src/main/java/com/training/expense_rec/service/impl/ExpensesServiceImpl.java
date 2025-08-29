package com.training.expense_rec.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.training.expense_rec.models.Expense;
import com.training.expense_rec.service.ExpensesService;

@Service
public class ExpensesServiceImpl implements ExpensesService{
	
	private List<Expense> expenses;
	
	public ExpensesServiceImpl() {
		this.expenses = new ArrayList<>();
	}
	
	public List<Expense> getAllExpenses(){
		return this.expenses;
	}
	
	public void addNewExpense(Expense newExpense) {
		this.expenses.add(newExpense);
	}
}
