package com.ml.wp.service;

import org.springframework.stereotype.Component;

import com.ml.wp.model.Galaxy;
import com.ml.wp.model.PredictionResult;
import com.ml.wp.model.WeatherConditionDate;

public interface GalaxyService {
	
	public PredictionResult calculateWeatherPredictionInYears(Galaxy g, Long years);
	public WeatherConditionDate getWeatherConditionByDayNumber(Long dayNumber);
	public PredictionResult simulateOneDay(Galaxy g) throws Exception;
	public void savePrediction(Long dayNumber, String conditionDesc);
	public PredictionResult calculateAndSaveWeatherPredictionInYears(Galaxy g, Long years, boolean save);
	
	
}
