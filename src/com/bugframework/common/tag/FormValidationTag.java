package com.bugframework.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.bugframework.common.utility.DataUtils;

public class FormValidationTag extends TagSupport{

	private String action;
	private String formid;
	private String callback;
	private String beforeSubmit;
	private String btnsub;
	
	public void setAction(String action) {
		this.action = action;
	}
	public void setFormid(String formid) {
		this.formid = formid;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public void setBeforeSubmit(String beforeSubmit) {
		this.beforeSubmit = beforeSubmit;
	}
	public void setBtnsub(String btnsub) {
		this.btnsub = btnsub;
	}
	
	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = this.pageContext.getOut();
			StringBuffer sb = new StringBuffer();
			sb.append("<script type=\"text/javascript\">");
			//回车登录
			sb.append("  $(document).keydown(function(e){ ");
					sb.append("if(e.keyCode == 13) {");
							sb.append("	 $('#"+this.formid+"').submit();");
					sb.append("}");
			sb.append("});  ");
			
			
			sb.append("$(function(){");
			sb.append("var "+this.formid+"_options = { ");
			sb.append(" beforeSerialize:"+this.formid+"_showRequest,   ");
			sb.append(" success:"+this.formid+"_showResponse,  ");
			sb.append("resetForm: false, ");
			sb.append("	dataType:  'json' ");
			sb.append(" }; ");
			  
			sb.append("$('#"+this.formid+"').submit(function() {");
			sb.append("$(this).ajaxSubmit("+this.formid+"_options); ");
			sb.append("return false; ");
			sb.append("}); ");
			sb.append("});");
			  
			sb.append("function "+this.formid+"_showRequest(formData, jqForm, options) { if($(\".Validform_error\").length>0){return false;}");
			if(DataUtils.isStrNotEmpty(this.beforeSubmit)){
			 	sb.append(" if("+this.beforeSubmit+"==false){return false;}");	
			}
			sb.append(" if($(\".Validform_error\").length>0){return false;}else{  return true;} ");
			sb.append("} ");
			 
			sb.append("function "+this.formid+"_showResponse(responseText, statusText)  { ");
			sb.append(this.callback);
			sb.append("} ");
			/**
			 * 自定义那个按钮进行提交
			 */
			sb.append(" $(function(){  $('#"+this.btnsub+"').click(function(){ $('#"+this.formid+"').submit();}) })  ");
			
			sb.append("</script>"); 
			sb.append("<form id=\"" + this.formid + "\" action=\"" + this.action + "\" name=\"" + this.formid + "\" method=\"post\"   class='ajform'>");
			out.print(sb.toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	@Override
	public int doEndTag() throws JspException {
		try {
			JspWriter out = this.pageContext.getOut();
			StringBuffer sb = new StringBuffer();
			
			sb.append("</form>");
			sb.append("<script type=\"text/javascript\">");
			sb.append("$(\"#"+this.formid+"\").Validform();");//valiform 初始化
			sb.append("</script>"); 
			out.print(sb.toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
}
