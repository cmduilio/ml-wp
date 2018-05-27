package com.ml.wp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ml.wp.model.WeatherConditionDate;

@Repository
public interface WeatherConditionDateRepository extends JpaRepository<WeatherConditionDate, Long> {
	
}
