package com.bugframework.common.utility;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 數據工具類
 * @author Administrator
 *
 */
public class DataUtils {
	/**
	 * 判斷字符串是否為空
	 * @param str  傳入字符串
	 * @return
	 */
	public static boolean isStrEmpty(String str){
		if(str==null||"".equals(str)){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isStrNotEmpty(String str){
		if(str==null||"".equals(str)){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 判断数组是否为空
	 * @param list
	 * @return
	 */
	public static boolean  isListEmpty(List list){
		if(list!=null&&!list.isEmpty()&&list.size()>0){
			return false;
		}else{
			return true;
		}
	}
	public static boolean  isListNotEmpty(List list){
		if(list!=null&&!list.isEmpty()&&list.size()>0){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 判断数组是否存在该字符串
	 * @param instr
	 * @param strs
	 * @return
	 */
	public static boolean isIn(String instr,String[] strs){
		if(strs!=null&&strs.length>0){
			for(int i =0;i<strs.length;i++){
				if(strs[i].equals(instr)){
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 去掉html標籤
	 * @param htmlStr
	 * @return
	 */
	 public static String delHTMLTag(String htmlStr){ 
	        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
	        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
	        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
	         
	        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
	        Matcher m_script=p_script.matcher(htmlStr); 
	        htmlStr=m_script.replaceAll(""); //过滤script标签 
	         
	        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
	        Matcher m_style=p_style.matcher(htmlStr); 
	        htmlStr=m_style.replaceAll(""); //过滤style标签 
	         
	        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
	        Matcher m_html=p_html.matcher(htmlStr); 
	        htmlStr=m_html.replaceAll(""); //过滤html标签 

	        return htmlStr.trim(); //返回文本字符串 
	    }
	public static String decode(String params) {
		if(isStrEmpty(params)){
			return null;
		}else{
			try {
				String paramsTrans = new String(params.getBytes("ISO-8859-1"),"UTF-8");
				params = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return params;
		}
		
	} 
	/**
	 * 是否位数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){
		 try {
			   Integer.parseInt(str);
			   return true;
			  } catch (NumberFormatException e) {
			  }
		 
		 try {
			   Double.parseDouble(str);
			    return true;
			  } catch (NumberFormatException e) {
			  }
		 try {
			   Float.parseFloat(str);
			    return true;
			  } catch (NumberFormatException e) {
			  }
		 try {
			   Short.parseShort(str);
			    return true;
			  } catch (NumberFormatException e) {
			  }
		 return false;
		}
	
	public static String nullToStr(String str){
		if(isStrEmpty(str)){
			return "暂无数据";
		}else{
			return str;
		}
	}
	public static void main(String[] args) {
	}
}
