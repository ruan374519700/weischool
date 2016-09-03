package com.bugframework.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugframework.auth.dao.RoleShortcutDao;
import com.bugframework.auth.pojo.AuthRoleShortcut;
import com.bugframework.auth.service.RoleShortcutService;

@Service("roleShortcutService")
public class RoleShortcutServiceImpl implements RoleShortcutService {
	@Autowired
	private RoleShortcutDao roleShortcutDao;

	@Override
	public void delelteRoleShortcutByShortcutId(String id) {
		roleShortcutDao.delelteRoleShortcutByShortcutId(id);

	}

	@Override
	public void deleteAll(String hql, String roleid) {

		this.roleShortcutDao.batchExecute(hql, roleid);
	}

	@Override
	public void batchAdd(List<AuthRoleShortcut> authRoleShortcuts) {

		this.roleShortcutDao.batchAdd(authRoleShortcuts);

	}

}
