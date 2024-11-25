package com.expenseTracker.services.expense;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.expenseTracker.dto.ExpenseDto;
import com.expenseTracker.entity.Expense;
import com.expenseTracker.repository.ExpenseRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

	private final ExpenseRepository expenseRepository;
	
	public Expense postExpense(ExpenseDto expenseDto) {
		return saveOrUpdateExpense(new Expense(), expenseDto);
	}
	
	private Expense saveOrUpdateExpense(Expense expense, ExpenseDto expenseDto) {
		expense.setTitle(expenseDto.getTitle());
		expense.setDate(expenseDto.getDate());
		expense.setAmount(expenseDto.getAmount());
		expense.setCategory(expenseDto.getCategory());
		expense.setDescription(expenseDto.getDescription());
		
		return expenseRepository.save(expense);
	}
	
	public Expense updateExpense(Long id, ExpenseDto expenseDto) {
		Optional<Expense> optionalExpense = expenseRepository.findById(id);
		if(optionalExpense.isPresent()) {
			return saveOrUpdateExpense(optionalExpense.get(), expenseDto);
		}else {
			throw new EntityNotFoundException("Expense is not present with id : "+ id);
		}
	}
	
	public List<Expense> getAllExpenses(){
		return expenseRepository.findAll().stream()
				.sorted(Comparator.comparing(Expense::getDate)
				.reversed()).collect(Collectors.toList());
	}
	
	public Expense getExpenseById(Long id) {
		Optional<Expense> optionalExpense = expenseRepository.findById(id);
		
		if(optionalExpense.isPresent()) {
			return optionalExpense.get();
		}else {
			throw new EntityNotFoundException("Expense is not present with id : "+ id);
		}
	}
	
	public void deleteExpense(Long id) {
		Optional<Expense> optionalExpense = expenseRepository.findById(id);
		
		if(optionalExpense.isPresent()) {
			expenseRepository.deleteById(id);
		}else {
			throw new EntityNotFoundException("Expense is not present with id : "+ id);
		}
	}
}




















