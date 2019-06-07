package com.okex.trande.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {
	public static String getTime() {
		return new SimpleDateFormat("yyyyMMddHHmmssSSSS").format(new Date());
	} 
	
	public static BigDecimal divide(BigDecimal amt1,BigDecimal amt2) {
		return amt1.divide(amt2,BigDecimal.ROUND_HALF_DOWN).setScale(4, BigDecimal.ROUND_HALF_DOWN);
	}
}
