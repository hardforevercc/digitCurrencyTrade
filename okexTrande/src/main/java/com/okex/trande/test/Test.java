package com.okex.trande.test;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import com.okex.trande.utils.SignatureUtils;

public class Test {

	public static void main(String[] args) {
		BigDecimal costUsdt = new BigDecimal("11.470000");
		BigDecimal myUsdt = new BigDecimal("88.5139");
		BigDecimal adaPercent = costUsdt.divide(costUsdt.add(myUsdt));
		System.out.println(adaPercent);
		Map<String,String> map = new HashMap<String,String>();
		map.put("api_key", "8d8f9938-3e93-40b7-bb42-cedcbf49235a");
		map.put("symbol","btc_usdt");
		map.put("type", "buy");
		map.put("price", "680");
		map.put("amount", "1.0");
		String sortStr = SignatureUtils.signBody(map);
		System.out.println(sortStr);
		String secret = "&secret_key=D5E9619FE8646D4B3BDB90CC83B62CE7";
		String finalStr = sortStr +secret;
		System.out.println(finalStr);
		System.out.println(getMD5Str(finalStr));
	}
	public static String encrypt() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("api_key", "ca481c37-aa5f-48f4-864d-3728411b5a7c");
//		map.put("symbol","btc_usdt");
//		map.put("type", "buy");
//		map.put("price", "680");
//		map.put("amount", "1.0");
		String sortStr = SignatureUtils.signBody(map);
		System.out.println(sortStr);
		String secret = "&secret_key=3D913940E284DB838365567C7D04230B";
		String finalStr = sortStr +secret;
		System.out.println(finalStr);
		System.out.println(getMD5Str(finalStr));
		return getMD5Str(finalStr);
	}
	
	private static String getMD5Str(String str)  
    {  
        MessageDigest messageDigest = null;  
        try  
        {  
            messageDigest = MessageDigest.getInstance("MD5");  
            messageDigest.reset();  
            messageDigest.update(str.getBytes("UTF-8"));  
        } catch (NoSuchAlgorithmException e)  
        {  
            System.out.println("NoSuchAlgorithmException caught!");  
            System.exit(-1);  
        } catch (UnsupportedEncodingException e)  
        {  
            e.printStackTrace();  
        }  
  
        byte[] byteArray = messageDigest.digest();  
  
        StringBuffer md5StrBuff = new StringBuffer();  
  
        for (int i = 0; i < byteArray.length; i++)  
        {  
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));  
            else  
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
        }  
        return md5StrBuff.toString().toUpperCase();  
    }  
	

}
