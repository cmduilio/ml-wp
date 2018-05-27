package com.ml.wp.model;

import java.util.List;

public class Galaxy {

	private Long id;
	private List<Planet> planets;
	
	public Galaxy() {
		super();
	}
	public Galaxy(Long id, List<Planet> planets) {
		super();
		this.id = id;
		this.planets = planets;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Planet> getPlanets() {
		return planets;
	}
	public void setPlanets(List<Planet> planets) {
		this.planets = planets;
	}
	
	
	
	
}
