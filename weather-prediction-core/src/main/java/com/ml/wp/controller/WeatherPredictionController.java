package com.ml.wp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ml.wp.model.WeatherConditionDate;
import com.ml.wp.service.GalaxyService;

@RestController
@RequestMapping("/weather")
public class WeatherPredictionController {

	@Autowired
	private GalaxyService galaxyService;

	@RequestMapping(value = "/{daynumber}", method = RequestMethod.GET)
	@ResponseBody
	public WeatherConditionDate findById(@PathVariable("daynumber") Long dayNumber) {
		return galaxyService.getWeatherConditionByDayNumber(dayNumber);
	}

	public GalaxyService getGalaxyService() {
		return galaxyService;
	}

	public void setGalaxyService(GalaxyService galaxyService) {
		this.galaxyService = galaxyService;
	}

	
	
}
