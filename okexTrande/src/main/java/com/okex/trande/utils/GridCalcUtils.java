package com.okex.trande.utils;

import java.math.BigDecimal;

public class GridCalcUtils {
	/**
	 * 计算结果为y 底数为x
	 * @return
	 */
	public static int getN(double x,double y) {
			
		return (int) (Math.log(1-y)/Math.log(1-x));
		
	}
}
