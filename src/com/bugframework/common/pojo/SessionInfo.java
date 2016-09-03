package com.bugframework.common.pojo;

import java.io.Serializable;

import com.bugframework.auth.pojo.AuthUser;



public class SessionInfo implements Serializable {
    
	private AuthUser user;
	public AuthUser getUser() {
		return user;
	}

	public void setUser(AuthUser user) {
		this.user = user;
	}

}
