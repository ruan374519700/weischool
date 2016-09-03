package com.bugframework.auth.pojo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.bugframework.common.pojo.IdEntity;

@Entity
@Table(name="auth_role")
public class AuthRole extends IdEntity implements java.io.Serializable{

	private String name;//权限名称
	private String description;//描述
	private Short isAdmin=0;//是否为超级管理员
	 
	@Column(name="name",length=20)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="description",length=100)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="isadmin",length=1)
	public Short getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Short isAdmin) {
		this.isAdmin = isAdmin;
	}
	 
	
}
