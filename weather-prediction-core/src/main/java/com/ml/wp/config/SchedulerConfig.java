package com.ml.wp.config;

import liquibase.integration.spring.SpringLiquibase;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import com.ml.wp.job.GalaxyPersistenceJob;
import com.ml.wp.spring.AutowiringSpringBeanJobFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Configuration
@ConditionalOnProperty(name = "quartz.enabled")
public class SchedulerConfig {

	@Bean
	public JobFactory jobFactory(ApplicationContext applicationContext,
			// injecting SpringLiquibase to ensure liquibase is already initialized and
			// created the quartz tables:
			SpringLiquibase springLiquibase) {
		AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
		jobFactory.setApplicationContext(applicationContext);
		return jobFactory;
	}

	@Bean(name = "galaxyPersistenceJobDetail")
	public JobDetailFactoryBean galaxyPersistenceJobDetail() {
		return createJobDetail(GalaxyPersistenceJob.class);
	}

	@Bean(name = "jobTrigger")
	public SimpleTriggerFactoryBean jobTrigger(@Qualifier("galaxyPersistenceJobDetail") JobDetail jobDetail, @Value("${galaxyPersistenceJob.frequencyInYears}") long frequency) {
		long frequencyInMillis =   1000 * 24 * 365 * frequency; 
		return createTrigger(jobDetail, frequencyInMillis );
	}

	private static JobDetailFactoryBean createJobDetail(Class<GalaxyPersistenceJob> jobClass) {
		JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
		factoryBean.setName("galaxyPersistenceJob");
		factoryBean.setJobClass(jobClass);
		factoryBean.setGroup("group1");
		// job has to be durable to be stored in DB:
		factoryBean.setDurability(true);
		return factoryBean;
	}

	@Bean
	public Scheduler schedulerFactoryBean(DataSource dataSource, JobFactory jobFactory,
			@Qualifier("jobTrigger") Trigger jobTrigger) throws Exception {
		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		
		// this allows to update triggers in DB when updating settings in config file:
		factory.setOverwriteExistingJobs(true);
		
		factory.setDataSource(dataSource);
		factory.setJobFactory(jobFactory);

		factory.setQuartzProperties(quartzProperties());
		factory.afterPropertiesSet();

		Scheduler scheduler = factory.getScheduler();
		
		if (checkJobIsPresent(scheduler, "galaxyPersistenceJob") == false) {
			scheduler.setJobFactory(jobFactory);
			scheduler.scheduleJob((JobDetail) jobTrigger.getJobDataMap().get("jobDetail"), jobTrigger);
		}
		
		scheduler.start();
		return scheduler;
	}

	private boolean checkJobIsPresent(Scheduler scheduler, String searchedJobName) throws SchedulerException {
		for (String groupName : scheduler.getJobGroupNames()) {
			for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
				String jobName = jobKey.getName();
				if(jobName.contains(searchedJobName)) return true;
				//String jobGroup = jobKey.getGroup();
				//// get job's trigger
				//List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
				//Date nextFireTime = triggers.get(0).getNextFireTime();
				//System.out.println("[jobName] : " + jobName + " [groupName] : " + jobGroup + " - " + nextFireTime);
			}
		}
		return false;
	}

	@Bean
	public Properties quartzProperties() throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
		propertiesFactoryBean.afterPropertiesSet();
		return propertiesFactoryBean.getObject();
	}

	private static SimpleTriggerFactoryBean createTrigger(JobDetail jobDetail, long pollFrequencyMs) {
		SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
		factoryBean.setJobDetail(jobDetail);
		factoryBean.setStartDelay(0L);
		factoryBean.setGroup("group1");
		factoryBean.setRepeatInterval(pollFrequencyMs);
		factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
		// in case of misfire, ignore all missed triggers and continue :
		factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT);
		return factoryBean;
	}

}
