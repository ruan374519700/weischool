package com.bugframework.auth.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 角色快捷入口中间表
 * @author 许增飞
 *
 */
@Entity
@Table(name="auth_role_shortcut")
public class AuthRoleShortcut {

	private String id;
	private AuthRole role;
	private AuthShortcut shortcut;
	
	@Id
	@GeneratedValue(generator="hibernate-uuid")
	@GenericGenerator(name="hibernate-uuid", strategy="uuid")
	@Column(name="id",length=36)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name="role_id",nullable=false)
	public AuthRole getRole() {
		return role;
	}
	public void setRole(AuthRole role) {
		this.role = role;
	}
	@ManyToOne
	@JoinColumn(name="shortcut_id",nullable=false)
	public AuthShortcut getShortcut() {
		return shortcut;
	}
	public void setShortcut(AuthShortcut shortcut) {
		this.shortcut = shortcut;
	}
	
	
}
