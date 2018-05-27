package com.ml.wp.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.ml.wp.model.Coordinates;

public class Coordinates2DHelper {
	
	public static boolean areAligned(Coordinates c1, Coordinates c2, Coordinates c3) {
		
		BigDecimal c1x = new BigDecimal(c1.getX());
		BigDecimal c1y = new BigDecimal(c1.getY());
		BigDecimal c2x = new BigDecimal(c2.getX());
		BigDecimal c2y = new BigDecimal(c2.getY());
		BigDecimal c3x = new BigDecimal(c3.getX());
		BigDecimal c3y = new BigDecimal(c3.getY());
		
		BigDecimal part1 = (c2x.subtract(c1x)).multiply(c3y.subtract(c2y)).setScale(4, RoundingMode.HALF_UP);
		BigDecimal part2 = (c2y.subtract(c1y)).multiply(c3x.subtract(c2x)).setScale(4, RoundingMode.HALF_UP);
		
		if (part1.compareTo(part2) == 0) return true; 
		else return false;
		
//		Double part1 = RoundUtil.getDoubleWith4Decimals((c2.getX() - c1.getX()) * (c3.getY() - c2.getY()));
//		Double part2 = RoundUtil.getDoubleWith4Decimals((c2.getY() - c1.getY()) * (c3.getX() - c2.getX()));
//		if (part1.compareTo(part2)==0) return true;
//		else return false;
	}
	
	public static Double getDistanceBetweenTwoCoordinates(Coordinates c1, Coordinates c2) {
		return RoundUtil.getDoubleWith4Decimals(Math.sqrt(Math.pow((c2.getX()-c1.getX()),2)+Math.pow((c2.getY()-c1.getY()),2)));
		
//		BigDecimal c1x = new BigDecimal(c1.getX()).setScale(4, RoundingMode.HALF_UP);
//		BigDecimal c1y = new BigDecimal(c1.getY()).setScale(4, RoundingMode.HALF_UP);
//		BigDecimal c2x = new BigDecimal(c2.getX()).setScale(4, RoundingMode.HALF_UP);
//		BigDecimal c2y = new BigDecimal(c2.getY()).setScale(4, RoundingMode.HALF_UP);
//		return RoundUtil.getDoubleWith4Decimals(Math.sqrt(((c2x.subtract(c1x)).pow(2).add((c2y.subtract(c1y)).pow(2))).setScale(4, RoundingMode.HALF_UP).doubleValue()));
		
	}
	
	public static Double getTrianglePerimeter(Coordinates c1, Coordinates c2, Coordinates c3) {
		Double c1c2 = getDistanceBetweenTwoCoordinates(c1, c2);
		Double c1c3 = getDistanceBetweenTwoCoordinates(c1, c3);
		Double c2c3 = getDistanceBetweenTwoCoordinates(c2, c3);
		return (c1c2+c1c3+c2c3);
	}
	
	public static boolean isCoordinateInsideTriangle(Coordinates t1, Coordinates t2, Coordinates t3, Coordinates x) throws Exception {
		Double tmp = 0.0;
		Double t0 = calculateTriangleArea(t1, t2, t3);
		tmp += calculateTriangleArea(t1, t2, x);
		tmp += calculateTriangleArea(t2, t3, x);
		tmp += calculateTriangleArea(t3, t1, x);
		
		tmp = RoundUtil.getDoubleWith4Decimals(tmp);
		t0 = RoundUtil.getDoubleWith4Decimals(t0);
		if (tmp.compareTo(t0)==0) {
			return true;
		}
		return false;
	}

	private static Double calculateTriangleArea(Coordinates t1, Coordinates t2, Coordinates t3) throws Exception {
		
		//hallar area por formula de Heron
		//1ero Semiperimetro
		Double s = RoundUtil.getDoubleWith4Decimals(getTrianglePerimeter(t1,t2,t3)/2);
		
		//calculo los lados
		Double ab = getDistanceBetweenTwoCoordinates(t1, t2);
		Double ac = getDistanceBetweenTwoCoordinates(t1, t3);
		Double bc = getDistanceBetweenTwoCoordinates(t2, t3);
		
		BigDecimal sab = new BigDecimal(s-ab).setScale(4, RoundingMode.HALF_UP);
		BigDecimal sac = new BigDecimal(s-ac).setScale(4, RoundingMode.HALF_UP);
		BigDecimal sbc = new BigDecimal(s-bc).setScale(4, RoundingMode.HALF_UP);
		
		Double aux = new BigDecimal(s).multiply(sab).multiply(sac).multiply(sbc).setScale(2,RoundingMode.HALF_UP).doubleValue();
		
//		return RoundUtil.getDoubleWith4Decimals(Math.sqrt(aux));
		return Math.sqrt(aux);
	}
	

}
