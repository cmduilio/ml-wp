package com.ml.wp.service;

import com.ml.wp.model.Galaxy;
import com.ml.wp.model.PredictionResult;

public interface WeatherService {
	
	public Integer isPeriodOfDrought(Galaxy g);
	public Integer isPeriodOfRain(Galaxy g) throws Exception;
	public Double getMaxPerimeter(Galaxy g);
	public Integer isPeriodsOfOptimalConditions(Galaxy g);
	public PredictionResult evaluateAllClimates(Galaxy g) throws Exception;

}
