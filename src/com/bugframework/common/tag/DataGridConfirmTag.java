package com.bugframework.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import com.bugframework.common.utility.DataUtils;

public class DataGridConfirmTag extends TagSupport{
	private String title;
	private String url;
	private String message;
	private String exp;
	
	public void setTitle(String title) {
		this.title = title;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
	 
	@Override
	public int doEndTag() throws JspException {
		 Tag t = findAncestorWithClass(this, DataGridTag.class);
		 DataGridTag parent = (DataGridTag) t;
		 DataGridButton button = new DataGridButton();
		 button.setExp(exp);
		 button.setUrl(url);
		 button.setTitle(title);
		 StringBuffer sb = new StringBuffer();
		 if(DataUtils.isStrNotEmpty(url)){
			 sb.append("datagridConfirm('"+url+"','"+this.message+"')");
			 button.setFunname(sb.toString());
		 }
		 parent.setCzBtn(button);
		return EVAL_PAGE;
	}

}
