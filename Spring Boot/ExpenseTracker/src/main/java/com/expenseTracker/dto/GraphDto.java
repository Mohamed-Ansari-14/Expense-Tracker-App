package com.expenseTracker.dto;

import java.util.List;

import com.expenseTracker.entity.Expense;
import com.expenseTracker.entity.Income;

import lombok.Data;

@Data
public class GraphDto {

	private List<Expense> expenseList;
	
	private List<Income> incomeList;
}
