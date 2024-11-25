package com.expenseTracker.services.stats;

import com.expenseTracker.dto.GraphDto;
import com.expenseTracker.dto.StatsDto;

public interface StatsService {

	GraphDto getChartData();
	
	StatsDto getStats();
}
