package com.bugframework.auth.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.bugframework.common.pojo.IdEntity;
import com.bugframework.common.utility.DataUtils;

@Entity
@Table(name = "auth_user")
public class AuthUser extends IdEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;// 姓名
	private String account;// 帐号(学号)
	private String password;// 密码
	private String loginIp;// 最后一次登录IP
	private AuthRole sysRole;

	private String roleName;

	@Column(name = "name", length = 10, nullable = true, unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "account", length = 50, nullable = true, unique = true)
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "password", length = 200)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "loginip", length = 20)
	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	@Transient
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@ManyToOne
	@JoinColumn(name = "roleid")
	public AuthRole getSysRole() {
		return sysRole;
	}

	public void setSysRole(AuthRole sysRole) {
		if (sysRole != null && DataUtils.isStrNotEmpty(sysRole.getId())) {
			setRoleName(sysRole.getName());
		}
		this.sysRole = sysRole;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "{\"name\":\"" + name + "\",\"account\":\"" + account
				+ "\",\"password\":\"" + password + "\",\"loginIp\":\""
				+ loginIp + "\",\"sysRole\":\"" + sysRole
				+ "\",\"roleName\":\"" + roleName + "\"}";
	}
	
}
