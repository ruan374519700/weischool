package com.bugframework.common.tag;

import java.util.HashMap;
import java.util.Map;

import com.bugframework.common.utility.DataUtils;

public class DataGridColumn {
	private String title;//字段标题
	private String field;//字段值
	private String width;//字段宽度
	private String align="left";//数据对其方式,默认左对齐，可选值有left,right,center
	private boolean sortable=false;//是否排序，默认不排序
	private boolean checkbox =false;//是否显示选择框，默认不显示
	private String formatter;//自定义函数名称
	private boolean hidden = false;//列隐藏(隐藏:true)
	private String replace;//字段值替换(例子 : 0_男,1_女)
	private boolean image;//是否显示图片字段，默认 false
	private String imgWidth;//图片宽度
	private String imgHeight;//图片高度
	private boolean query =false;//是否为添加查询列
	private String url;//自定义链接地址
	
	private Map<String, String> replaceMap = new HashMap<String, String>();//将replace字段的拆分，存放到map中，便于查询
	
	public DataGridColumn(){
		
	}
	
	public DataGridColumn(String title, String field, String width,
			String align, boolean sortable, boolean checkbox, String formatter,
			boolean hidden, String replace, boolean image, String imgWidth,
			String imgHeight, boolean query, String url) {
		this.title = title;
		this.field = field;
		this.width = width;
		this.align = align;
		this.sortable = sortable;
		this.checkbox = checkbox;
		this.formatter = formatter;
		this.hidden = hidden;
		this.replace = replace;
		this.image = image;
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
		this.query = query;
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		this.align = align;
	}
	public boolean isSortable() {
		return sortable;
	}
	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}
	public boolean isCheckbox() {
		return checkbox;
	}
	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}
	public String getFormatter() {
		return formatter;
	}
	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}
	public boolean isHidden() {
		return hidden;
	}
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	public String getReplace() {
		return replace;
	}
	public void setReplace(String replace) {
		if(DataUtils.isStrNotEmpty(replace)){
			String[] replaceArray =replace.split(",");
			for(int i =0;i<replaceArray.length;i++){
				String[] ck = replaceArray[i].split("_");
				replaceMap.put(ck[0], ck[1]);
			}
		}
		this.replace = replace;
	}
	public boolean isImage() {
		return image;
	}
	public void setImage(boolean image) {
		this.image = image;
	}
	public String getImgWidth() {
		return imgWidth;
	}
	public void setImgWidth(String imgWidth) {
		this.imgWidth = imgWidth;
	}
	public String getImgHeight() {
		return imgHeight;
	}
	public void setImgHeight(String imgHeight) {
		this.imgHeight = imgHeight;
	}
	public boolean isQuery() {
		return query;
	}
	public void setQuery(boolean query) {
		this.query = query;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, String> getReplaceMap() {
		return replaceMap;
	}

	public void setReplaceMap(Map<String, String> replaceMap) {
		this.replaceMap = replaceMap;
	}
	
	
}
