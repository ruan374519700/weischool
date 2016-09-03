package com.bugframework.auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugframework.auth.dao.PermissionDao;
import com.bugframework.auth.pojo.AuthPermisstion;
import com.bugframework.auth.service.PermissionService;
/**
 * 
 * <p>Title:PermissionServiceImpl </p>
 * <p>Description: 权限管理服务实现层</p>
 * @author 许增飞
 * @date 上午9:05:26
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	private PermissionDao permissionDao;

	@Override
	public List<AuthPermisstion> findPermissionByRole(String id) {
		if (id == null)
			return new ArrayList<AuthPermisstion>();
		return this.permissionDao.findPermissionByRole(id);
	}

	@Override
	public List<AuthPermisstion> getleftMenu(String id) {
		if (id == null)
			return new ArrayList<AuthPermisstion>();
		return this.permissionDao.getleftMenu(id);
	}

	@Override
	public void delete(String name, String value) {
		 this.permissionDao.delete(name, value);
	}

	@Override
	public void add(List<AuthPermisstion> permisstions) {
		this.permissionDao.batchAdd(permisstions);
	}

}
