package com.training.expense_rec.models;

import java.util.Date;

public class Expense {
	
	private int id;
	private String description;
	private int amount;
	private Date date;
	private String category;
	
	public Expense(int id, String description, int amount, Date date, String category) {
		super();
		this.id = id;
		this.description = description;
		this.amount = amount;
		this.date = date;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", description=" + description + ", amount=" + amount + ", date=" + date
				+ ", category=" + category + "]";
	}
	
	
}
