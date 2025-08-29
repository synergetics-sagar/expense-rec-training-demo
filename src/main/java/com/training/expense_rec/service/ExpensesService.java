package com.training.expense_rec.service;

import java.util.List;

import com.training.expense_rec.models.Expense;

public interface ExpensesService {
	
	public List<Expense> getAllExpenses();
	
	public void addNewExpense(Expense newExpense);
}
