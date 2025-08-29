package com.training.expense_rec.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.training.expense_rec.models.Expense;
import com.training.expense_rec.service.ExpensesService;


class ExpensesServiceImplTest {

    private ExpensesService expensesService;

    @BeforeEach
    void setUp() {
        expensesService = new ExpensesServiceImpl();
    }

    @Test
    void testAddNewExpense() {
        Expense expense = new Expense(1, "Laptop", 50000, new Date(1756469003), "Electronics");

        expensesService.addNewExpense(expense);
        List<Expense> allExpenses = expensesService.getAllExpenses();

        assertEquals(1, allExpenses.size());
        assertEquals("Laptop", allExpenses.get(0).getDescription());
        assertEquals(50000, allExpenses.get(0).getAmount());
    }

    @Test
    void testGetAllExpensesWhenEmpty() {
        List<Expense> allExpenses = expensesService.getAllExpenses();
        assertTrue(allExpenses.isEmpty(), "Expenses list should be empty initially");
    }

    @Test
    void testGetAllExpensesWithMultipleEntries() {
        expensesService.addNewExpense(new Expense(2, "Books", 200, new Date(1756469003), "Books"));
        expensesService.addNewExpense(new Expense(3, "Shoes", 1500, new Date(1756469003), "Shoes"));

        List<Expense> allExpenses = expensesService.getAllExpenses();

        assertEquals(2, allExpenses.size());
        assertEquals("Books", allExpenses.get(0).getDescription());
        assertEquals("Shoes", allExpenses.get(1).getDescription());
    }

    @Test
    void testAddMultipleExpensesAndVerifyAmounts() {
        Expense e1 = new Expense(4, "Coffee", 100, new Date(1756469003), "Food");
        Expense e2 = new Expense(5, "Headphones", 2500, new Date(1756469003), "Electronics");

        expensesService.addNewExpense(e1);
        expensesService.addNewExpense(e2);

        List<Expense> allExpenses = expensesService.getAllExpenses();

        assertEquals(2, allExpenses.size());
        assertEquals(100, allExpenses.get(0).getAmount());
        assertEquals(2500, allExpenses.get(1).getAmount());
    }
}
