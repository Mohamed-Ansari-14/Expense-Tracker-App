package com.expenseTracker.services.stats;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

import org.springframework.stereotype.Service;

import com.expenseTracker.dto.GraphDto;
import com.expenseTracker.dto.StatsDto;
import com.expenseTracker.entity.Expense;
import com.expenseTracker.entity.Income;
import com.expenseTracker.repository.ExpenseRepository;
import com.expenseTracker.repository.IncomeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {

	private final IncomeRepository incomeRepository;
	
	private final ExpenseRepository expenseRepository;
	
	public GraphDto getChartData() {
		LocalDate endDate = LocalDate.now();
		LocalDate startDate = endDate.minusDays(27);
		
		GraphDto graphDto = new GraphDto();
		graphDto.setExpenseList(expenseRepository.findByDateBetween(startDate, endDate));
		graphDto.setIncomeList(incomeRepository.findByDateBetween(startDate, endDate));
		
		return graphDto;
	}
	
	public StatsDto getStats() {
		Double totalIncome = incomeRepository.sumAllAmounts();
		Double totalExpense = expenseRepository.sumAllAmounts();
		
		Optional<Income> optionalIncome = incomeRepository.findFirstByOrderByDateDesc();
		Optional<Expense> optionalExpense = expenseRepository.findFirstByOrderByDateDesc();
		
		StatsDto statsDto = new StatsDto();
		statsDto.setIncome(totalIncome);
		statsDto.setExpense(totalExpense);
		
		optionalIncome.ifPresent(statsDto::setLatestIncome);
		optionalExpense.ifPresent(statsDto::setLatestExpense);
		
		statsDto.setBalance(totalIncome-totalExpense);
		
		List<Income> incomeList = incomeRepository.findAll();
		List<Expense> expenseList = expenseRepository.findAll();
		
		OptionalDouble minIncome = incomeList.stream().mapToDouble(Income::getAmount).min();
		OptionalDouble maxIncome = incomeList.stream().mapToDouble(Income::getAmount).max();
		
		OptionalDouble minExpense = expenseList.stream().mapToDouble(Expense::getAmount).min();
		OptionalDouble maxExpense = expenseList.stream().mapToDouble(Expense::getAmount).max();
		
		statsDto.setMaxExpense(maxExpense.isPresent() ? maxExpense.getAsDouble() : null);
		statsDto.setMinExpense(minExpense.isPresent() ? minExpense.getAsDouble() : null);
		
		statsDto.setMaxIncome(maxIncome.isPresent() ? maxIncome.getAsDouble() : null);
		statsDto.setMinIncome(minIncome.isPresent() ? minIncome.getAsDouble() : null);
		
		return statsDto;
	}
}





























