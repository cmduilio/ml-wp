package com.ml.wp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ml.wp.model.WeatherConditionDate;
import com.ml.wp.service.GalaxyService;

@RestController
public class WeatherPredictionController {
	
	public final String climaURL = "/clima";
	
	@Autowired
	private GalaxyService galaxyService;
	
	@RequestMapping(method = RequestMethod.GET, path=climaURL)
	public WeatherConditionDate findWeatherConditionDescByDayNumber(@RequestParam(value = "dia") Long dayNumber) throws Exception {
		if (dayNumber != null) return galaxyService.getWeatherConditionByDayNumber(dayNumber);
		else return null;
	}

	public GalaxyService getGalaxyService() {
		return galaxyService;
	}

	public void setGalaxyService(GalaxyService galaxyService) {
		this.galaxyService = galaxyService;
	}

	
	
}
