package com.expenseTracker.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ExpenseDto {

	private Long id;

	private String title;

	private String description;

	private String category;

	private LocalDate date;

	private Integer amount;
}
