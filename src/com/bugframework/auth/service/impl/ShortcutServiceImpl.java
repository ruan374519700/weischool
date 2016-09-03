package com.bugframework.auth.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugframework.auth.pojo.AuthRoleShortcut;
import com.bugframework.auth.pojo.AuthShortcut;
import com.bugframework.auth.service.ShortcutService;
import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.auth.dao.RoleShortcutDao;
import com.bugframework.auth.dao.ShortcutDao;
@Service("shortcutService")
public class ShortcutServiceImpl implements ShortcutService {
	@Autowired
	private ShortcutDao shortcutDao;
	@Autowired
	private RoleShortcutDao roleShortcutDao;
	@Override
	public int getMaxOrderby() {
		return this.shortcutDao.getMaxOrderby();
	}

	@Override
	public void orderUpOrDown(String id, String type, AjaxJson j) {
		/*
		 * Criteria cq=this.getCriteria(); cq.addOrder(Order.asc("orderby"));
		 * List<AuthShortcut> list=cq.list();
		 */
		List<AuthShortcut> list = this.shortcutDao.findAll("orderby", false);
		if (list != null && list.size() > 0) {
			int size = list.size();
			Integer orderby1 = 0;
			Integer orderby2 = 0;
			for (int i = 0; i < size; i++) {
				AuthShortcut data1 = list.get(i);
				if (data1.getId().equals(id)) {
					if ("up".equals(type) && i > 0) {// 上移操作
						AuthShortcut data2 = list.get(i - 1);
						orderby1 = data1.getOrderby();
						orderby2 = data2.getOrderby();
						data1.setOrderby(orderby2);// 调换两个排序号
						data2.setOrderby(orderby1);// 调换两个排序号
						this.shortcutDao.update(data2);
						this.shortcutDao.update(data1);
						j.setSuccess(true);
						break;
					} else if ("down".equals(type) && i < size - 1) {// 下移操作
						AuthShortcut data2 = list.get(i + 1);
						orderby1 = data1.getOrderby();
						orderby2 = data2.getOrderby();
						data1.setOrderby(orderby2);// 调换两个排序号
						data2.setOrderby(orderby1);// 调换两个排序号
						this.shortcutDao.update(data2);
						this.shortcutDao.update(data1);
						j.setSuccess(true);
						break;
					} else {
						j.setMsg("up".equals(type) ? "无法再继续上移！" : "无法继续下移！");
						j.setSuccess(false);
					}
				}
			}
		}

	}

	@Override
	public List<AuthRoleShortcut> getshortcutByrole(String roleid) {
		return this.shortcutDao.getshortcutByrole(roleid);
	}

	@Override
	public List<AuthShortcut> findShortcutByIds(String id) {
		return this.shortcutDao.findShortcutByIds(id);
	}

	@Override
	public List<AuthShortcut> find(int isenabled) {
		return this.shortcutDao.find(" from AuthShortcut a where a.isenable=? order by a.orderby asc", 1);
	}

	@Override
	public void datagrid(AuthShortcut authShortcut,
			DataGrid<AuthShortcut> datagrid, HttpServletRequest request) {
		 this.shortcutDao.datagrid(authShortcut, datagrid, request);
		
	}

	@Override
	public AuthShortcut get(String id) {
		if(id==null)
			return new AuthShortcut();
		return this.shortcutDao.get(id);
	}

	@Override
	public void delete(String id) {
		 if(id!=null){
			this.roleShortcutDao.delelteRoleShortcutByShortcutId(id);//清除旧的数据
			 this.shortcutDao.deleteAll(id);
		 }
		
	}

	@Override
	public void update(AuthShortcut authShortcut) {
		this.shortcutDao.update(authShortcut);
	}

	@Override
	public void add(AuthShortcut authShortcut) {
	 this.shortcutDao.add(authShortcut);
		
	}

	@Override
	public List<AuthShortcut> find(String param) {
		if(param==null)
			return null;
		return shortcutDao.find(" from AuthShortcut r where r.name=?", param);
		 
	}

}
