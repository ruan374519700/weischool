package com.bugframework.auth.pojo;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bugframework.common.pojo.IdEntity;

@Entity
@Table(name="auth_permission")
public class AuthPermisstion extends IdEntity {

		private AuthRole role;
		private AuthModule module;
		@ManyToOne
		@JoinColumn(name="roleid",nullable=false)
		public AuthRole getRole() {
			return role;
		}
		public void setRole(AuthRole role) {
			this.role = role;
		}
		@ManyToOne
		@JoinColumn(name="moduleid",nullable=false)
		public AuthModule getModule() {
			return module;
		}
		public void setModule(AuthModule module) {
			this.module = module;
		}
		
		
}
