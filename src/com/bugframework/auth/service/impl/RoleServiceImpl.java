package com.bugframework.auth.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugframework.auth.dao.RoleDao;
import com.bugframework.auth.pojo.AuthRole;
import com.bugframework.auth.service.RoleService;
import com.bugframework.common.pojo.DataGrid;
/**
 * 
 * <p>Title:RoleServiceImpl </p>
 * <p>Description:角色服务实现层 </p>
 * @author 许增飞
 * @date 2016-8-14 上午9:17:20
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;

	@Override
	public List<AuthRole> getRoleList() {
		return this.roleDao.getRoleList();
	}

	@Override
	public AuthRole get(String id) {
		if (id == null)
			return new AuthRole();
		return this.roleDao.get(id);
	}

	@Override
	public void datagrid(AuthRole role, DataGrid<AuthRole> datagrid,
			HttpServletRequest request) {
		 this.roleDao.datagrid(role, datagrid, request);
		
	}

	@Override
	public void update(AuthRole s) {
		this.roleDao.update(s);
		
	}

	@Override
	public void deleteAlllogic(String id) {
		 this.roleDao.deleteAlllogic(id);
		
	}

	@Override
	public void delete(String id) {
		this.roleDao.delete(id);
		
	}

	@Override
	public void add(AuthRole role) {
		this.roleDao.add(role);
		
	}

	@Override
	public List<AuthRole> findByName(String name) {
		return this.roleDao.find(" from AuthRole r where r.name=?", name);
	}

}
