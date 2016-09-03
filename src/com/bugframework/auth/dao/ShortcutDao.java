package com.bugframework.auth.dao;

import java.util.List;

import com.bugframework.auth.pojo.AuthRoleShortcut;
import com.bugframework.auth.pojo.AuthShortcut;
import com.bugframework.common.dao.BaseDao;
import com.bugframework.common.pojo.AjaxJson;

public interface ShortcutDao extends BaseDao<AuthShortcut>{

	public int getMaxOrderby();
 
	public List<AuthRoleShortcut> getshortcutByrole(String roleid);

	public List<AuthShortcut> findShortcutByIds(String id);

}
