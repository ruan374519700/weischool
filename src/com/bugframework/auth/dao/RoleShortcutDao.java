package com.bugframework.auth.dao;

import com.bugframework.auth.pojo.AuthRoleShortcut;
import com.bugframework.common.dao.BaseDao;

public interface RoleShortcutDao extends BaseDao<AuthRoleShortcut>{

	void delelteRoleShortcutByShortcutId(String id);

}
