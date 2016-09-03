package com.bugframework.auth.dao;


import java.util.List;

import com.bugframework.auth.pojo.AuthRole;
import com.bugframework.common.dao.BaseDao;
 

public interface RoleDao  extends BaseDao<AuthRole>{
	/**
	 * 获得启用的角色
	 * @return
	 */
	List<AuthRole> getRoleList();
}
