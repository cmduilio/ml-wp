package com.ml.wp.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ml.wp.model.Galaxy;
import com.ml.wp.service.GalaxyService;

@Component
public class GalaxyPersistenceJob implements Job {
	
	@Autowired
	private GalaxyService galaxyService;
	
	@Autowired
	private Galaxy defaultGalaxy;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("========== Executing GalaxyPersistanceJob! =========");
		galaxyService.calculateAndSaveWeatherPredictionInYears(defaultGalaxy, 10L, true);
	}
	

}
