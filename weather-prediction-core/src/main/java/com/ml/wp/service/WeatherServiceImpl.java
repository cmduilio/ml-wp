package com.ml.wp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ml.wp.model.Coordinates;
import com.ml.wp.model.Galaxy;
import com.ml.wp.model.Planet;
import com.ml.wp.model.PredictionResult;
import com.ml.wp.util.Coordinates2DHelper;

@Service
public class WeatherServiceImpl implements WeatherService {
	
	private Coordinates sun = new Coordinates();
	
	@Override
	public Integer isPeriodOfDrought(Galaxy g) {
		List<Planet> planets = g.getPlanets();
		if (!planets.isEmpty()) {
			if (Coordinates2DHelper.areAligned(planets.get(0).getPos(), planets.get(1).getPos(), planets.get(2).getPos())) {
				if(Coordinates2DHelper.areAligned(planets.get(0).getPos(), planets.get(1).getPos(), sun))
					return 1;
			}
		}
		return 0;
	}

	@Override
	public Integer isPeriodOfRain(Galaxy g) throws Exception {
		List<Planet> planets = g.getPlanets();
		if (!planets.isEmpty()) {
			if (Coordinates2DHelper.isCoordinateInsideTriangle(planets.get(0).getPos(), planets.get(1).getPos(), planets.get(2).getPos(), sun)) {
				return 1;
			}
		}
		return 0;
	}

	@Override
	public Double getMaxPerimeter(Galaxy g) {
		List<Planet> planets = g.getPlanets();
		if (!planets.isEmpty()) {
			return Coordinates2DHelper.getTrianglePerimeter(planets.get(0).getPos(), planets.get(1).getPos(), planets.get(2).getPos());
		}
		return 0.00;
	}

	@Override
	public Integer isPeriodsOfOptimalConditions(Galaxy g) {
		List<Planet> planets = g.getPlanets();
		if (!planets.isEmpty()) {
			if (Coordinates2DHelper.areAligned(planets.get(0).getPos(), planets.get(1).getPos(), planets.get(2).getPos())) {
				
				if(!Coordinates2DHelper.areAligned(planets.get(0).getPos(), planets.get(1).getPos(), sun)) 
					if(!Coordinates2DHelper.areAligned(planets.get(0).getPos(), planets.get(2).getPos(), sun)) 
						if(!Coordinates2DHelper.areAligned(planets.get(1).getPos(), planets.get(2).getPos(), sun)) 
							return 1;
			}
		}
		return 0;
	}

	@Override
	public PredictionResult evaluateAllClimates(Galaxy g) throws Exception {
		try {
			PredictionResult pr = new PredictionResult();
			List<Planet> planets = g.getPlanets();
			if (!planets.isEmpty()) {
				//estan alineados?
				if (Coordinates2DHelper.areAligned(planets.get(0).getPos(), planets.get(1).getPos(), planets.get(2).getPos())) {
					if(Coordinates2DHelper.areAligned(planets.get(0).getPos(), planets.get(2).getPos(), sun)) {//alineados con el sol?
						pr.setPeriodsOfDrought(1);
					}
					else {
						pr.setPeriodsOfOptimalConditions(1);
					}
				}
				//si no lo estan forman un triangulo con el sol en su interior?
				else {
					if (Coordinates2DHelper.isCoordinateInsideTriangle(planets.get(0).getPos(), planets.get(1).getPos(), planets.get(2).getPos(), sun)) {
						pr.setPeriodsOfRain(1);
						pr.setMaxPerimeter(Coordinates2DHelper.getTrianglePerimeter(planets.get(0).getPos(), planets.get(1).getPos(), planets.get(2).getPos()));
					}
				}
			}

			return pr;
		} catch (Exception e) {
			throw new Exception("Error evaluating all climates in WeatherServiceImpl - " + e.getMessage());
		}

	}

}
