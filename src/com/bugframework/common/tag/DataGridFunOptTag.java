package com.bugframework.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class DataGridFunOptTag  extends TagSupport{
	protected String title;
	private String exp;//判断链接是否显示的表达式
	private String funname;//自定义函数名称
	public void setTitle(String title) {
		this.title = title;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
	public void setFunname(String funname) {
		this.funname = funname;
	}
 @Override
public int doEndTag() throws JspException {
	 
	 Tag t = findAncestorWithClass(this, DataGridTag.class);
	 DataGridTag parent = (DataGridTag) t;
	 DataGridButton button = new DataGridButton();
	 button.setExp(exp);
	 button.setFunname(funname);
	 button.setTitle(title);
	 parent.setCzBtn(button);
	 return EVAL_PAGE;
}
}
