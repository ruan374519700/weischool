package com.bugframework.common.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlUtils {
	
	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式  
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式  
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式  
    private static final String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符
    
    public static String delHTMLTag(String htmlStr) {  
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);  
        Matcher m_script = p_script.matcher(htmlStr);  
        htmlStr = m_script.replaceAll(""); // 过滤script标签  
  
        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);  
        Matcher m_style = p_style.matcher(htmlStr);  
        htmlStr = m_style.replaceAll(""); // 过滤style标签  
  
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);  
        Matcher m_html = p_html.matcher(htmlStr);  
        htmlStr = m_html.replaceAll(""); // 过滤html标签  
  
        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);  
        Matcher m_space = p_space.matcher(htmlStr);  
        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签  
        return htmlStr.trim(); // 返回文本字符串  
    }  
      
    public static String getTextFromHtml(String htmlStr){  
        htmlStr = delHTMLTag(htmlStr);  
        htmlStr = htmlStr.replaceAll("&nbsp;", "");  
//        htmlStr = htmlStr.substring(0, htmlStr.indexOf("。")+1);  
        return htmlStr;  
    }
	
	// 把内容替换成html格式，同时过滤掉部分html的标签--保存
	public static String toHTML(String sourcestr) {
		String targetstr = "";
		if (sourcestr != null) {
			targetstr = insteadCode(sourcestr, ">", "&gt;");
			targetstr = insteadCode(targetstr, "<", "&lt;");
			targetstr = insteadCode(targetstr, "\n", "<br>");
//			targetstr = insteadCode(targetstr, " ", "&nbsp;");
		}
		return targetstr;
	}

	// 输出
	public static String toTEXT(String sourcestr) {
		String targetstr = "";
		if (sourcestr != null) {
			targetstr = insteadCode(sourcestr, "&gt;", ">");
			targetstr = insteadCode(targetstr, "&lt;", "<");
			targetstr = insteadCode(targetstr, "<br>", "\n");
			targetstr = insteadCode(targetstr, "&nbsp;", " ");
		}
		return targetstr;
	}

	public static String insteadCode(String s, String r1, String r2) {
		s = s.replace(r1, r2);
		return s;
	}
	
}
