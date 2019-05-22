package com.okex.trande.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {
	public static String getTime() {
		return new SimpleDateFormat("yyyyMMddHHmmssSSSS").format(new Date());
	} 
}
