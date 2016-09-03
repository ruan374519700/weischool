package com.bugframework.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.bugframework.common.utility.DataUtils;

/**
 * 
 * @author 许增飞
 *
 */
public class SelectTag extends TagSupport {
	 
	private static final long serialVersionUID = 1L;
	protected   String basePath;
	private String title;//下拉框标题名称
	private String id;//下拉框id,如果ID为空，那么ID默认为name
	private String name;//下拉框名称属性
	private String option;//字段text(名字)_value(值)例子 : 男_0,女_1
	private String value;//下拉框选中值
	private String width="345";//下拉框自定义宽度，默认345
	private Short isValidate=0;//是否校验,默认不校验
	
	
	public void setId(String id) {
		this.id = id;
	}
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public void setIsValidate(Short isValidate) {
		this.isValidate = isValidate;
	}
	@Override
	public int doStartTag() throws JspException {
	 
		return EVAL_PAGE;
	}
	@Override
	public int doEndTag() throws JspException {
		try {
			if(DataUtils.isStrEmpty(this.id)){
				this.id=this.name;
			}
			JspWriter out = this.pageContext.getOut();
			StringBuffer sb = new StringBuffer();
			sb.append("<script type=\"text/javascript\">");
			sb.append("$(document).ready(function(e) {");
			sb.append("   $(\"#"+this.id+"\").uedSelect({");
			sb.append("		width :"+this.width+"			");  
			sb.append("	});");
			sb.append("});");
			sb.append("</script>");
			sb.append("<div class=\"vocation\">");
			sb.append("<select name=\""+this.name+"\"  id=\""+this.id+"\"");
			if(isValidate==1){
				sb.append(" datatype=\"*\" nullmsg=\"请选择"+this.title+"！\"");
			}
			sb.append(	">");
			sb.append("<option value=\"\">--选择"+this.title+"--</option>");
			 if(DataUtils.isStrNotEmpty(this.option)){//如果option不为空的时候，则遍历text和value
				 String[] optionArray =this.option.split(",");
				 for(int i=0;i<optionArray.length;i++){
					 String[] strarray =optionArray[i].split("_");
					 sb.append(" <option value=\""+strarray[1]+"\"  ");
					 if(DataUtils.isStrNotEmpty(this.value)&&strarray[1].equals(this.value)){
						 sb.append(" selected=\"selected\"");
					 }
					 sb.append(">"+strarray[0]+"</option>");
				 }
			 }
			sb.append("</select>");
		    sb.append("</div>");	
			out.print(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
}
