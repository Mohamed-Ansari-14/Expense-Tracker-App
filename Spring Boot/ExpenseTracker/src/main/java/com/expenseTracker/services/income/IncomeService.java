package com.expenseTracker.services.income;

import java.util.List;

import com.expenseTracker.dto.IncomeDto;
import com.expenseTracker.entity.Income;

public interface IncomeService {

	Income postIncome(IncomeDto incomeDto);
	
	Income updateIncome(Long id, IncomeDto incomeDto);
	
	List<IncomeDto> getAllIncome();
	
	Income getIncomeById(Long id);
	
	void deleteIncome(Long id);
}
