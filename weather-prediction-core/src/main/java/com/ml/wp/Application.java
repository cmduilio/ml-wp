package com.ml.wp;

import org.quartz.SchedulerException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {
	
	public static void main(String[] args) throws SchedulerException {
		ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
	
	}

}
