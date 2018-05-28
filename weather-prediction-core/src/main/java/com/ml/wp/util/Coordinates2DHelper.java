package com.ml.wp.util;

import com.ml.wp.model.Coordinates;

public class Coordinates2DHelper {
	
	public static boolean areAligned(Coordinates c1, Coordinates c2, Coordinates c3) {
		double x1 = c1.getX();
		double y1 = c1.getY();
		double x2 = c2.getX();
		double y2 = c2.getY();
		double x3 = c3.getX();
		double y3 = c3.getY();
		if (((x2-x1)*(y3-y2))-((y2-y1)*(x3-x2)) == 0.00) return true;
		else return false;
	}
	
	public static Double getDistanceBetweenTwoCoordinates(Coordinates c1, Coordinates c2) {
		return RoundUtil.getDoubleWith4Decimals(Math.sqrt(Math.pow((c2.getX()-c1.getX()),2)+Math.pow((c2.getY()-c1.getY()),2)));
	}
	
	public static Double getTrianglePerimeter(Coordinates c1, Coordinates c2, Coordinates c3) {
		Double c1c2 = getDistanceBetweenTwoCoordinates(c1, c2);
		Double c1c3 = getDistanceBetweenTwoCoordinates(c1, c3);
		Double c2c3 = getDistanceBetweenTwoCoordinates(c2, c3);
		return RoundUtil.getDoubleWith4Decimals(c1c2+c1c3+c2c3);
	}
	
	public static boolean isCoordinateInsideTriangle(Coordinates t1, Coordinates t2, Coordinates t3, Coordinates x) throws Exception {
		return isInside(t1.getX(), t1.getY(), t2.getX(), t2.getY(), t3.getX(), t3.getY(), x.getX(), x.getY());
	}

	public static Double calculateTriangleArea(Coordinates t1, Coordinates t2, Coordinates t3) throws Exception {
		return RoundUtil.getDoubleWith4Decimals(area(t1.getX(), t1.getY(), t2.getX(), t2.getY(), t3.getX(), t3.getY()));
	}
	
	
	private static double area(double x1, double y1, double x2, double y2, double x3, double y3) {
		return Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0);
	}

	private static boolean isInside(double x1, double y1, double x2, double y2, double x3, double y3, double x, double y) {
		double A = area(x1, y1, x2, y2, x3, y3);
		double A1 = area(x, y, x2, y2, x3, y3);
		double A2 = area(x1, y1, x, y, x3, y3);
		double A3 = area(x1, y1, x2, y2, x, y);
		return (A == A1 + A2 + A3);
	}
	

}
