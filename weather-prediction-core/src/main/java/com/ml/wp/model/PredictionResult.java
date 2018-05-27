package com.ml.wp.model;

public class PredictionResult {

	private Integer periodsOfDrought = 0;
	private Integer periodsOfRain = 0;
	private Double maxPerimeter = 0.0;
	private Integer maxPerimeterDay = 0;	
	private Integer periodsOfOptimalConditions = 0;
	
	public PredictionResult() {
	}

	public PredictionResult(Integer periodsOfDrought, Integer periodsOfRain, Double maxPerimeter,
			Integer maxPerimeterDay, Integer periodsOfOptimalConditions) {
		super();
		this.periodsOfDrought = periodsOfDrought;
		this.periodsOfRain = periodsOfRain;
		this.maxPerimeter = maxPerimeter;
		this.maxPerimeterDay = maxPerimeterDay;
		this.periodsOfOptimalConditions = periodsOfOptimalConditions;
	}
	
	public Integer getPeriodsOfDrought() {
		return periodsOfDrought;
	}
	
	public void setPeriodsOfDrought(Integer periodsOfDrought) {
		this.periodsOfDrought = periodsOfDrought;
	}
	
	public Integer getPeriodsOfRain() {
		return periodsOfRain;
	}
	
	public void setPeriodsOfRain(Integer periodsOfRain) {
		this.periodsOfRain = periodsOfRain;
	}
	
	public Double getMaxPerimeter() {
		return maxPerimeter;
	}
	
	public void setMaxPerimeter(Double maxPerimeter) {
		this.maxPerimeter = maxPerimeter;
	}
	
	public Integer getMaxPerimeterDay() {
		return maxPerimeterDay;
	}
	
	public void setMaxPerimeterDay(Integer maxPerimeterDay) {
		this.maxPerimeterDay = maxPerimeterDay;
	}
	
	public Integer getPeriodsOfOptimalConditions() {
		return periodsOfOptimalConditions;
	}
	
	public void setPeriodsOfOptimalConditions(Integer periodsOfOptimalConditions) {
		this.periodsOfOptimalConditions = periodsOfOptimalConditions;
	}

	public void add(PredictionResult temp) {
		// TODO Auto-generated method stub
		this.periodsOfDrought += temp.getPeriodsOfDrought();
		this.periodsOfRain += temp.getPeriodsOfRain();
		if (this.maxPerimeter<temp.getMaxPerimeter()) {
			this.maxPerimeter = temp.getMaxPerimeter();
			this.maxPerimeterDay = temp.getMaxPerimeterDay();
		}
		this.periodsOfOptimalConditions += temp.getPeriodsOfOptimalConditions();
		
		
	}

	@Override
	public String toString() {
		return "PredictionResult [periodsOfDrought=" + periodsOfDrought + ", periodsOfRain=" + periodsOfRain
				+ ", maxPerimeter=" + maxPerimeter + ", maxPerimeterDay=" + maxPerimeterDay
				+ ", periodsOfOptimalConditions=" + periodsOfOptimalConditions + "]";
	}
	
	
	
	
	

	
	
}
