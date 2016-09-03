package com.bugframework.common.tag;

import java.util.ArrayList;
import java.util.List;

import com.bugframework.common.utility.DataUtils;


public class DataGridButton {
	private String url;//操作链接地址
	private String title;//按钮名称
	private String icon;//按钮图标
 
	private String isbtn;//是否是操作选项以外的链接
	private String exp;//判断链接是否显示的表达式
	private String funname;//自定义函数名称
	 
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getIsbtn() {
		return isbtn;
	}
	public void setIsbtn(String isbtn) {
		this.isbtn = isbtn;
	}
	public String getExp() {
		return exp;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
	public String getFunname() {
		return funname;
	}
	 
	public void setFunname(String funname) {
		this.funname = funname;
	}
	 
	 
	public String setValueReplace(String str,List<String> fields){
		for(String st:fields){
			if(str.indexOf("{"+st+"}")>-1){
				str = str.replaceAll("\\{"+st+"\\}","\"+value."+st+"+\"");
			}
		}
		return str;
	}
	 
}
