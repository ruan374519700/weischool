package com.bugframework.auth.dao;

import java.util.List;


import com.bugframework.auth.pojo.AuthUser;
import com.bugframework.common.dao.BaseDao;

public interface UserDao extends BaseDao<AuthUser> {
	/**
	 * 查找用户，通过帐号和密码
	 * 
	 * @param user
	 * @return
	 */
	public AuthUser findUser(AuthUser user);

	/**
	 * 获取可用的用户
	 * 
	 * @return
	 */
	public List<AuthUser> enableUser();

}
