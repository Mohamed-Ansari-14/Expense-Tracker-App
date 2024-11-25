package com.expenseTracker.services.expense;

import java.util.List;

import com.expenseTracker.dto.ExpenseDto;
import com.expenseTracker.entity.Expense;

public interface ExpenseService {

	Expense postExpense(ExpenseDto expenseDto);
	
	List<Expense> getAllExpenses();
	
	Expense getExpenseById(Long id);
	
	Expense updateExpense(Long id, ExpenseDto expenseDto);
	
	void deleteExpense(Long id);
}
