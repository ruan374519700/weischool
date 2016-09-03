package com.bugframework.auth.pojo;

import java.beans.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.bugframework.common.pojo.IdEntity;
@Entity
@Table(name="auth_shortcut")
public class AuthShortcut extends IdEntity{

	private String name;//入口名称
	private String url;//入口地址
	private String icon;//图标名称
	private String iconUrl;//图标地址，方便以后拓展
	private Integer isSelect=0;//是否选中
	/**
	 * 入口名称
	 * @return
	 */
	@Column(name="name",length=10)
	public String getName() {
		return name;
	}
	/**
	 * 入口名称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 入口地址
	 * @return
	 */
	@Column(name="url",length=200)
	public String getUrl() {
		return url;
	}
	/**
	 * 入口地址
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 图标名称
	 */
	@Column(name="icon",length=20)
	public String getIcon() {
		return icon;
	}
	/**
	 * 图标名称
	 * @param icon
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**
	 * 图标地址
	 * @return
	 */
	@Column(name="icon_url",length=200)
	public String getIconUrl() {
		return iconUrl;
	}
	/**
	 * 图标地址
	 * @param iconUrl
	 */
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	/**
	 * 是否选中
	 * @return
	 */
	@Transient
	public Integer getIsSelect() {
		return isSelect;
	}
	/**
	 * 是否选中
	 * @param isSelect
	 */
	public void setIsSelect(Integer isSelect) {
		this.isSelect = isSelect;
	}
	
	
	
}
