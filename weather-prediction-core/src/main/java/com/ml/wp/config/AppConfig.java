package com.ml.wp.config;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.ml.wp.model.Galaxy;
import com.ml.wp.model.Planet;

@Configuration
@ComponentScan("com.ml.wp")
public class AppConfig {
	
	private static Logger logger = Logger.getLogger(AppConfig.class.getName());
	
	@Bean("defaultGalaxy")
	public Galaxy defaultGalaxy(){
		List<Planet> planets = new ArrayList<Planet>();
		planets.add(new Planet("Ferengis", -1.0, 500.0, 0.0, 500.0, 0.0));
		planets.add(new Planet("Betasoides", -3.0, 2000.0, 0.0, 2000.0, 0.0));
		planets.add(new Planet("Vulcanos", 5.0, 1000.0, 0.0, 1000.0, 0.0)); //en positivo porque va antihorario
		return new Galaxy(1L, planets);
	 }
}


