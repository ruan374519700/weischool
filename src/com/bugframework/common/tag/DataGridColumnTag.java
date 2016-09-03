package com.bugframework.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;


public class DataGridColumnTag extends TagSupport{

	private String title;//字段标题
	private String field;//字段值
	private String width;//字段宽度
	private String align="left";//数据对其方式,默认左对齐，可选值有left,right,center
	private boolean sortable=false;//是否排序，默认不排序
	private boolean checkbox =false;//是否显示选择框，默认不显示
	private String formatter;//自定义函数名称
	private boolean hidden = false;//列隐藏(隐藏:true)
	private String replace;//字段值替换(例子 : 男_0,女_1)
	private boolean image;//是否显示图片字段，默认 false
	private String imgWidth;//图片宽度
	private String imgHeight;//图片高度
	private boolean query =false;//是否为添加查询列
	private String url;//自定义链接地址
	public void setTitle(String title) {
		this.title = title;
	}
	public void setField(String field) {
		this.field = field;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public void setAlign(String align) {
		this.align = align;
	}
	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}
	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}
	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	public void setReplace(String replace) {
		this.replace = replace;
	}
	public void setImage(boolean image) {
		this.image = image;
	}
	public void setImgWidth(String imgWidth) {
		this.imgWidth = imgWidth;
	}
	public void setImgHeight(String imgHeight) {
		this.imgHeight = imgHeight;
	}
	public void setQuery(boolean query) {
		this.query = query;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public int doEndTag() throws JspException {
		Tag t = findAncestorWithClass(this, DataGridTag.class);
		DataGridTag parent = (DataGridTag) t;
		DataGridColumn column = new DataGridColumn(title, field, width, align, sortable, checkbox, formatter, hidden, replace, image, imgWidth, imgHeight, query, url);
		parent.setDataGridColumns(column);
		return EVAL_PAGE;
	}
}
