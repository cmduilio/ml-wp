package com.ml.wp.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ml.wp.model.Galaxy;
import com.ml.wp.model.Planet;
import com.ml.wp.model.PredictionResult;
import com.ml.wp.model.WeatherConditionDate;
import com.ml.wp.repository.WeatherConditionDateRepository;

@Service
public class GalaxyServiceImpl implements GalaxyService {

	private static final Logger LOGGER = Logger.getLogger( GalaxyServiceImpl.class.getName() );
	
	@Autowired
	private WeatherService weatherService;
	
	@Autowired
	private WeatherConditionDateRepository weatherConditionDateRepository;
	
	@Override
	public PredictionResult calculateWeatherPredictionInYears(Galaxy g, Long years) {
		return calculateAndSaveWeatherPredictionInYears(g, years, false);
	}

	@Override
	public WeatherConditionDate getWeatherConditionByDayNumber(Long dayNumber) {
		Optional<WeatherConditionDate> obj = weatherConditionDateRepository.findById(dayNumber);
		return obj.get();
		}

	@Override
	public PredictionResult simulateOneDay(Galaxy g) throws Exception {
		try{
			List<Planet> planets = g.getPlanets();
			for (Planet p : planets) {
				p.simulatePlanetMovementInADay();
			}
			PredictionResult pr = weatherService.evaluateAllClimates(g);
			return pr;
			}
			catch (Exception e) {
				LOGGER.log(Level.SEVERE, e.getMessage());
				throw new Exception("Error in GalaxyServiceImpl - simulateOneDay: " + e.getMessage());
		}

	}
	
	@Override
	public PredictionResult calculateAndSaveWeatherPredictionInYears(Galaxy g, Long years, boolean save) {
		try {
			Long yearsInDays = years * 365;
			PredictionResult predictionResult = new PredictionResult();
			for (int i = 1; i <= yearsInDays; i++) {
				PredictionResult temp = simulateOneDay(g);
				temp.setMaxPerimeterDay(i);
				predictionResult.add(temp);	
				if (save) {
					String wcdDescription = getWeatherConditionDateDesc(temp);
					savePrediction(Long.valueOf(i), wcdDescription);
				}
				//===Debug ===
				System.out.println(temp.toString());
			}
			return predictionResult;
		}
		catch (Exception e) {
			LOGGER.log(Level.WARNING, e.getMessage());
			return null;
		}
	}

	private String getWeatherConditionDateDesc(PredictionResult temp) {
		if (temp.getPeriodsOfDrought()>=1) return "Periodo de sequias"; 
		if (temp.getPeriodsOfRain()>=1) return "Periodo de lluvias";
		if (temp.getPeriodsOfOptimalConditions() >= 1) return "Periodo de condiciones optimas de presion y temperatura";
		return "";
	}

	@Override
	public void savePrediction(Long dayNumber, String conditionDesc) {
		WeatherConditionDate wcd = new WeatherConditionDate(dayNumber, conditionDesc);
		weatherConditionDateRepository.save(wcd);
	}

	public WeatherService getWeatherService() {
		return weatherService;
	}

	public void setWeatherService(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	public WeatherConditionDateRepository getWeatherConditionDateRepository() {
		return weatherConditionDateRepository;
	}

	public void setWeatherConditionDateRepository(WeatherConditionDateRepository weatherConditionDateRepository) {
		this.weatherConditionDateRepository = weatherConditionDateRepository;
	}
	
	
}