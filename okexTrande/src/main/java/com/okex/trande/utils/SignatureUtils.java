package com.okex.trande.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class SignatureUtils {
	private static final String UTF8 = "utf-8";
	private static final String MD5 = "MD5";
	/**
	 * 执行加密加签
	 * @param map
	 * @param secret
	 * @return 加密加签字符串
	 */
	public static String encrypt(Map<String,String> map,String secret) {		
		String sortStr = SignatureUtils.signBody(map);
		String finalStr = sortStr +"&secret_key="+secret;
		return getMD5Str(finalStr);
	}
	/**
	 * 排序并加签
	 * @param params
	 * @return
	 */
	public static String signBody(Map<String,String> params) {
		    
		List<String> paramKeys = new LinkedList(); 
		for(Map.Entry entry:params.entrySet()){
			if (entry.getValue() == null) {
				return null;
			}
			if ((entry.getValue() instanceof String) && ((String) entry.getValue()).isEmpty()) {
				return null;
			}
			paramKeys.add((String) entry.getKey());
		}
		Collections.sort(paramKeys);
		StringBuilder sb = new StringBuilder(100);
		for(String paramKey:paramKeys){
			sb.append(paramKey).append("=").append(params.get(paramKey)).append("&");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	/**
	 * 32位MD5加密
	 * @param str
	 * @return 加密字符串
	 */
	private static String getMD5Str(String str)  
    {  
        MessageDigest messageDigest = null;  
        try  
        {  
            messageDigest = MessageDigest.getInstance(MD5);  
            messageDigest.reset();  
            messageDigest.update(str.getBytes(UTF8));  
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
