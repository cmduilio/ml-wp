package com.ml.wp.model;

public class Coordinates {
	
	private Double x;
	private Double y;
	
	public Coordinates() {
		x=0.00;
		y=0.00;
	}
	
	public Coordinates(Double x, Double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Double getX() {
		return x;
	}
	public void setX(Double x) {
		this.x = x;
	}
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}
	
	

}
