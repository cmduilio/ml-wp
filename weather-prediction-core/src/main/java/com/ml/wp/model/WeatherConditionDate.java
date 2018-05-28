package com.ml.wp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="WEATHER_COND_DATE")
public class WeatherConditionDate {
	
	@Id
	@Column(name="DAY_NUMBER")
	private Long dayNumber;
	
	@Column(name="WEATHER_COND_DESC" , nullable=false)
	private String weatherConditionDesc;
	
	public WeatherConditionDate() {
		super();
	}
	public WeatherConditionDate(Long dayNumber, String conditionDesc) {
		this.dayNumber = dayNumber;
		this.weatherConditionDesc = conditionDesc;
	}
	public Long getDayNumber() {
		return dayNumber;
	}
	public void setDayNumber(Long dayNumber) {
		this.dayNumber = dayNumber;
	}
	public String getWeatherCondition() {
		return weatherConditionDesc;
	}
	public void setWeatherCondition(String weatherCondition) {
		this.weatherConditionDesc = weatherCondition;
	}

}
