package com.bugframework.auth.service;

import java.util.List;

import com.bugframework.auth.pojo.AuthRoleShortcut;
 

public interface RoleShortcutService {
	/**
	 * 删除
	 * @param id
	 */
	void delelteRoleShortcutByShortcutId(String id);
	/**
	 * 通过roleId批量删除
	 * @param string
	 * @param roleid
	 */
	void deleteAll(String string, String roleid);
	/**
	 * 批量添加
	 * @param authRoleShortcuts
	 */
	void batchAdd(List<AuthRoleShortcut> authRoleShortcuts);

}
