package com.expenseTracker.services.income;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.expenseTracker.dto.IncomeDto;
import com.expenseTracker.entity.Income;
import com.expenseTracker.repository.IncomeRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {

	private final IncomeRepository incomeRepository;
	
	public Income postIncome(IncomeDto incomeDto) {
		return saveOrUpdateIncome(new Income(), incomeDto);
	}
	
	private Income saveOrUpdateIncome(Income income, IncomeDto incomeDto) {
		income.setTitle(incomeDto.getTitle());
		income.setDate(incomeDto.getDate());
		income.setAmount(incomeDto.getAmount());
		income.setCategory(incomeDto.getCategory());
		income.setDescription(incomeDto.getDescription());
		
		return incomeRepository.save(income);
	}
	
	public Income updateIncome(Long id, IncomeDto incomeDto) {
		Optional<Income> optionalIncome = incomeRepository.findById(id);
		if(optionalIncome.isPresent()) {
			return saveOrUpdateIncome(optionalIncome.get(), incomeDto);
		}else {
			throw new EntityNotFoundException("Income is not present with id : "+ id);
		}
	}
	
	public List<IncomeDto> getAllIncome(){
		return incomeRepository.findAll().stream()
				.sorted(Comparator.comparing(Income::getDate).reversed())
				.map(Income::getIncomeDto).collect(Collectors.toList());
	}
	
	public Income getIncomeById(Long id) {
		Optional<Income> optionalIncome = incomeRepository.findById(id);
		
		if(optionalIncome.isPresent()) {
			return optionalIncome.get();
		}else {
			throw new EntityNotFoundException("Income is not present with id : "+ id);
		}
	}
	
	public void deleteIncome(Long id) {
		Optional<Income> optionalIncome = incomeRepository.findById(id);
		
		if(optionalIncome.isPresent()) {
			incomeRepository.deleteById(id);
		}else {
			throw new EntityNotFoundException("Income is not present with id : "+ id);
		}
	}
}














