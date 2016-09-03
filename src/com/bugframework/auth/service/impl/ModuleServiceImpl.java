package com.bugframework.auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugframework.auth.dao.ModuleDao;
import com.bugframework.auth.pojo.AuthModule;
import com.bugframework.auth.service.ModuleService;
import com.bugframework.common.pojo.DataGrid;
/**
 * 
 * <p>Title:服务接口实现层</p>
 * <p>Description: 服务接口实现层</p>
 * @author 许增飞
 * @date 2016-8-14 下午12:31:09
 */

@Service("moduleService")
public class ModuleServiceImpl implements ModuleService {
	@Autowired
	private ModuleDao moduleDao;

	@Override
	public List<AuthModule> find(int delFlag, int iseabled) {
		return moduleDao
				.find("from AuthModule a where a.delFlag =? and a.isenable =? order by orderby asc,createTime desc",
						delFlag, iseabled);
	}

	@Override
	public List<AuthModule> find() {
		return this.moduleDao
				.find("from AuthModule a where a.delFlag =? order by a.orderby asc,a.createTime desc",
						0);
	}

	@Override
	public List<AuthModule> find(String name, String pid) {
		return this.moduleDao.find(
				" from AuthModule r where r.name=? and  r.pid =?", name, pid);
	}

	@Override
	public void datagrid(AuthModule module, DataGrid<AuthModule> datagrid,
			HttpServletRequest request) {
		this.moduleDao.datagrid(module, datagrid, request);
	}

	@Override
	public AuthModule get(String id) {
		if (id == null)
			return new AuthModule();
		return this.moduleDao.get(id);
	}

	@Override
	public void update(AuthModule s) {
		this.moduleDao.update(s);

	}

	@Override
	public void add(AuthModule module) {
		this.moduleDao.add(module);
	}

	@Override
	public void deleteAlllogic(String id) {
		if (id != null)
			this.moduleDao.deleteAlllogic(id);

	}

	@Override
	public void deletelogic(String id) {
		if (id != null)
			this.moduleDao.deleteAlllogic(id);

	}

	@Override
	public List<AuthModule> get(String[] ids) {
	    if(ids.length==0)
	    	return new ArrayList<AuthModule>();
	    this.moduleDao.getBatch(ids);
		return null;
	}

}
