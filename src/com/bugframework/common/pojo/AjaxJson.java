package com.bugframework.common.pojo;

import java.util.HashMap;
import java.util.Map;

public class AjaxJson {

	private String msg ="操作失败！";
	private boolean success =false;
	private Object obj;
	private Map<String, Object> attributes = new HashMap<String, Object>();
	
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	
	
	
}
