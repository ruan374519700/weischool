package com.bugframework.common.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CharacterUtils {
	//获得[0-9][a-z][A-Z]的随机码
	public static String getRandomString(int length){  
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";  
        Random random = new Random();  
        StringBuffer sb = new StringBuffer();  
          
        for(int i = 0 ; i < length; ++i){  
            int number = random.nextInt(62);//[0,62)  
              
            sb.append(str.charAt(number));  
        }  
        return sb.toString();  
    }
	
	//获得纯数字格式的时间(例:2015年8月1号 19:17:20 -> 20150801191720)
	public static String getNumericDate() {
		   Date currentTime = new Date();
		   SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		   String dateString = formatter.format(currentTime);
		   return dateString;
	}
}
