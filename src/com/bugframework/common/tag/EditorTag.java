package com.bugframework.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.bugframework.common.utility.DataUtils;
/**
 * 百度编辑器标签
 * @author 许增飞
 *
 */
public class EditorTag  extends TagSupport{

	private String id;//texarea的id和name
	private Integer length=1000;//字数长度限制
	private Integer width=500;//百度编辑器宽度，默认500
	private Integer height=500;//百度编辑器长度，默认500
	private String  getTxtOrHtml="html";//获得txt或者html内容,默认 html
	private String className;//可以引入样式
	private String value;//默认值
	private String datatype;//texteara校验
	private String errormsg;//错误信息提醒，和datatype 一起使用
	private String nullmsg;//为空信息提醒，和datatype 一起使用
	@Override
	public int doEndTag() throws JspException {
		try {
			JspWriter out = this.pageContext.getOut();
			StringBuffer sb = new StringBuffer();
			sb.append("<textarea name=\""+this.id+"\" id=\""+this.id+"\"  style=\"display:none\"   "+this.getDatatype()+" "+this.getErrormsg()+" "+this.getNullmsg()+" >"+this.getValue()+"</textarea>");
		    sb.append("<script id=\"editor"+this.id+"\" type=\"text/plain\" "+this.getClassName()+" style=\"padding-left:100px\"></script>");
		    sb.append("<script type=\"text/javascript\">");
		    sb.append("var ue"+this.id+" = UE.getEditor('editor"+this.id+"',{  maximumWords:"+this.length+",initialFrameWidth:"+this.width+",initialFrameHeight:"+this.height+"          });");//maximumWords允许的最大字符数
		    sb.append("ue"+this.id+".addListener('ready',function(){");
		    sb.append("ue"+this.id+".setContent($(\"#"+this.id+"\").val());");//读取已有内容放入ueditor\
		    sb.append("});");
		    sb.append("ue"+this.id+".addListener('blur',function (){");
		    if(this.getTxtOrHtml.equals("txt")){
		    	sb.append("$(\"#"+this.id+"\").val(ue"+this.id+".getContentTxt());");//ueditor失去焦点就将ueditor的内容放入content文本框..存放 txt
		    }else{
		    	sb.append("$(\"#"+this.id+"\").val(ue"+this.id+".getContent());");//ueditor失去焦点就将ueditor的内容放入content文本框。。存放 html
		    }
		    sb.append("});");
		    sb.append("</script>");
			out.print(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return EVAL_PAGE;
	}
	 public void setId(String id) {
		this.id = id;
	}


	public void setLength(Integer length) {
		this.length = length;
	}


	public void setWidth(Integer width) {
		this.width = width;
	}


	public void setHeight(Integer height) {
		this.height = height;
	}


	public void setGetTxtOrHtml(String getTxtOrHtml) {
		this.getTxtOrHtml = getTxtOrHtml;
	}


	public void setClassName(String className) {
		this.className = className;
	}


	public void setValue(String value) {
		this.value = value;
	}

	

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}


	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}


	public void setNullmsg(String nullmsg) {
		this.nullmsg = nullmsg;
	}
	

	public String getId() {
		return id;
	}


	public Integer getLength() {
		return length;
	}


	public Integer getWidth() {
		return width;
	}


	public Integer getHeight() {
		return height;
	}


	public String getGetTxtOrHtml() {
		return getTxtOrHtml;
	}


	public String getClassName() {
		className =DataUtils.isStrEmpty(this.className)?"":"class=\""+this.className+"\"";
		return className;
	}


	public String getValue() {
		return DataUtils.isStrEmpty(this.value)?"":this.value;
	}


	public String getDatatype() {
		datatype =DataUtils.isStrEmpty(this.datatype)?"":"datatype=\""+this.datatype+"\"";
		return datatype;
	}


	public String getErrormsg() {
		errormsg =DataUtils.isStrEmpty(this.errormsg)?"":"errormsg=\""+this.errormsg+"\"";
		return errormsg;
	}


	public String getNullmsg() {
		nullmsg =DataUtils.isStrEmpty(this.nullmsg)?"":"nullmsg=\""+this.nullmsg+"\"";
		return nullmsg;
	}


	
}
