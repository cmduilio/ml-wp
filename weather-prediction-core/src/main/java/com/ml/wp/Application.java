package com.ml.wp;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.ml.wp.job.GalaxyPersistenceJob;

@SpringBootApplication
public class Application {
	
	private final static int JOB_FREQUENCY_IN_YEARS = 10;
	
	public static void main(String[] args) throws SchedulerException {
		ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
		
		int frequencyInHours = JOB_FREQUENCY_IN_YEARS * 356* 24;
		JobDetail job = JobBuilder.newJob(GalaxyPersistenceJob.class).withIdentity("galaxyjob", "group1").build();
		
		// Trigger the job to run on the next round minute
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("galaxytrigger", "group1")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(frequencyInHours).repeatForever()).build();

		// schedule it
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		scheduler.scheduleJob(job, trigger);
	}
}
