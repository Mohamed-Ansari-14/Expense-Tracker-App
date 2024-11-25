package com.expenseTracker.dto;

import com.expenseTracker.entity.Expense;
import com.expenseTracker.entity.Income;

import lombok.Data;

@Data
public class StatsDto {

	private Double income;
	
	private Double expense;
	
	private Income latestIncome;
	
	private Expense latestExpense;
	
	private Double balance;
	
	private Double minIncome;
	
	private Double minExpense;
	
	private Double maxIncome;
	
	private Double maxExpense;
}
