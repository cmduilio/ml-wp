package com.ml.wp.model;

import java.util.ArrayList;
import java.util.List;

public class PredictionResult {

	private Integer periodsOfDrought = 0;
	private Integer periodsOfRain = 0;
	private Double maxPerimeter = 0.0;
	private List<Integer> maxPerimeterDays = new ArrayList<Integer>();	
	private Integer periodsOfOptimalConditions = 0;
	
	public PredictionResult() {
	}

	public PredictionResult(Integer periodsOfDrought, Integer periodsOfRain, Double maxPerimeter,
			List<Integer> maxPerimeterDays, Integer periodsOfOptimalConditions) {
		super();
		this.periodsOfDrought = periodsOfDrought;
		this.periodsOfRain = periodsOfRain;
		this.maxPerimeter = maxPerimeter;
		this.maxPerimeterDays = maxPerimeterDays;
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
	
	public List<Integer> getMaxPerimeterDays() {
		return maxPerimeterDays;
	}

	public void setMaxPerimeterDays(List<Integer> maxPerimeterDays) {
		this.maxPerimeterDays = maxPerimeterDays;
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
		this.periodsOfOptimalConditions += temp.getPeriodsOfOptimalConditions();
		if (this.maxPerimeter==temp.getMaxPerimeter()) {
			this.maxPerimeterDays.addAll(temp.getMaxPerimeterDays());
		}
		else if (this.maxPerimeter<temp.getMaxPerimeter()) {
			this.maxPerimeter = temp.getMaxPerimeter();
			this.maxPerimeterDays = temp.getMaxPerimeterDays();
		}

	}

	@Override
	public String toString() {
		return "PredictionResult [periodsOfDrought=" + periodsOfDrought + ", periodsOfRain=" + periodsOfRain
				+ ", maxPerimeter=" + maxPerimeter + ", maxPerimeterDays=" + this.getMaxPerimeterDaysAsString()
				+ ", periodsOfOptimalConditions=" + periodsOfOptimalConditions + "]";
	}

	private String getMaxPerimeterDaysAsString() {
		String str = "(";
		if(!maxPerimeterDays.isEmpty()) {
			for (Integer integer : maxPerimeterDays) {
				str = str.concat(integer.toString()).concat(";");
			}
		}
		str = str.concat(")");
		return str;
	}
	
	
	
	
	

	
	
}
