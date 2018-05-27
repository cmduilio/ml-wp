package com.ml.wp.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.ml.wp.util.RoundUtil;

public class Planet {
	
	private String civilization;
	private Double angularSpeed;
	private Double radious;
	private Double angle;
	private Coordinates pos;
	
	private static final double TWO_PI = 360.00;
	
	public Planet(String civilization, Double angularSpeed, Double radious, Double angle, Double posX, Double posY) {
		super();
		this.civilization = civilization;
		this.angularSpeed = angularSpeed;
		this.radious = radious;
		this.angle = angle;
		this.pos = new Coordinates(posX, posY);
	}
	
	public void simulatePlanetMovementInADay() {
		Double angleTmp = this.getAngle() + this.getAngularSpeed();
		if (angleTmp>0 && angleTmp>=TWO_PI) angleTmp-=TWO_PI;
		if (angleTmp<0 && angleTmp<=-TWO_PI) angleTmp+=TWO_PI;
		Double posX = RoundUtil.getDoubleWith4Decimals(this.getRadious() * Math.cos(Math.toRadians(angleTmp)));
		Double posY = RoundUtil.getDoubleWith4Decimals(this.getRadious() * Math.sin(Math.toRadians(angleTmp)));
		this.updateDynamicParameters(angleTmp, posX, posY);
	}
	
	public void updateDynamicParameters(Double angl, Double x, Double y) {
		this.angle = angl;
		this.setPosX(x);
		this.setPosY(y);
	}
	
	public String getCivilization() {
		return civilization;
	}
	public void setCivilization(String civilization) {
		this.civilization = civilization;
	}
	public Double getAngularSpeed() {
		return angularSpeed;
	}
	public void setAngularSpeed(Double angularSpeed) {
		this.angularSpeed = angularSpeed;
	}
	public Double getRadious() {
		return radious;
	}
	public void setRadious(Double radious) {
		this.radious = radious;
	}
	public Double getPosX() {
		return this.pos.getX();
	}
	public void setPosX(Double x) {
		this.pos.setX(x);
	}
	public Double getPosY() {
		return this.pos.getY();
	}
	public void setPosY(Double posY) {
		this.pos.setY(posY);
	}
	public Double getAngle() {
		return angle;
	}
	public void setAngle(Double angle) {
		this.angle = angle;
	}

	public Coordinates getPos() {
		return pos;
	}

	public void setPos(Coordinates pos) {
		this.pos = pos;
	}
	
	
	

}
