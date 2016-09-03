package com.bugframework.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class DataGridToolBarTag extends TagSupport{
	/**
	 * add:添加按钮
	 * update:更新按钮
	 * del:删除按钮
	 * count:统计按钮
	 */
	public final static String[] ICON_TYPE_NAME={"add","update","del","count"};
	public final static String[] ICON_TYPE_VALUE = {"images/t01.png","images/t02.png","images/t03.png","images/t04.png"};
	private String title;
	private String icon;
	private String funname;
	/**标题*/
	public void setTitle(String title) {
		this.title = title;
	}
	 
	/**按钮图片名称或者地址*/
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**执行函数*/
	public void setFunname(String funname) {
		this.funname = funname;
	}
	 @Override
	 public int doEndTag() throws JspException {
		 Tag t = findAncestorWithClass(this, DataGridTag.class);
		 DataGridTag parent = (DataGridTag) t;
		 DataGridButton button = new DataGridButton();
		 button.setFunname(funname);
		 button.setTitle(title);
		 button.setIcon(icon);
		 parent.setToolBar(button);
		 return EVAL_PAGE;
	 }
	
}
