package com.ml.wp.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundUtil {
	
	public static Double getDoubleWith4Decimals(Double d){
		return new BigDecimal(d).setScale(4, RoundingMode.HALF_UP).doubleValue();
	}

}
