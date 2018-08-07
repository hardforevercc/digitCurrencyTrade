package com.okex.trande.utils;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public class HttpUtils {
	public static String getMsg(HttpServletRequest request) throws IOException {
    	
        BufferedReader reader = request.getReader();
        StringBuffer sb=new StringBuffer();
        String line;
        while((line=reader.readLine())!=null){
            sb.append(line);
        }
        return sb.toString();
    }
}
