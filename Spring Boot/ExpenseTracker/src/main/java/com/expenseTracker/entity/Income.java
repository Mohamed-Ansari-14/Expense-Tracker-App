package com.expenseTracker.entity;

import java.time.LocalDate;

import com.expenseTracker.dto.IncomeDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Income {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private Integer amount;
	
	private LocalDate date;
	
	private String category;
	
	private String description;
	
	public IncomeDto getIncomeDto() {
		IncomeDto incomeDto = new IncomeDto();
		
		incomeDto.setId(id);
		incomeDto.setTitle(title);
		incomeDto.setAmount(amount);
		incomeDto.setCategory(category);
		incomeDto.setDate(date);
		incomeDto.setDescription(description);
		
		return incomeDto;
	}
}
















