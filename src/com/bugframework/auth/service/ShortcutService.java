package com.bugframework.auth.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bugframework.auth.pojo.AuthRoleShortcut;
import com.bugframework.auth.pojo.AuthShortcut;
import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.DataGrid;
/**
 * 
 * <p>Title:角色快捷入口服务接口层</p>
 * <p>Description:对外提供服务 </p>
 * @author 许增飞
 * @date 2016-8-14 上午9:25:09
 */
public interface ShortcutService {
	/**
	 * 获得最大的排序号
	 * @return
	 */
	int getMaxOrderby();
	/**
	 * 上移还是下移
	 * @param id 快捷入口ID
	 * @param type 类型：up上移，down 下移
	 * @param j
	 */
	void orderUpOrDown(String id, String type, AjaxJson j);
	/**
	 * 获得角色所属的快捷入口列表
	 * @param roleid 传入角色ID
	 * @return List<AuthRoleShortcut>
	 */
	List<AuthRoleShortcut> getshortcutByrole(String roleid);
	/**
	 * 批量获得快捷入口
	 * @param id 快捷入口ID，多个ID以英文逗号隔开
	 * @return List<AuthShortcut>
	 */
	List<AuthShortcut> findShortcutByIds(String id);
	/**
	 * 获得已经启用的快捷入口列表
	 * @param isenabled
	 * @return
	 */
	List<AuthShortcut> find(int isenabled);
	/**
	 * 分页查询
	 * @param authShortcut 快捷入口对象值
	 * @param datagrid 分页查询模型
	 * @param request HttpServletRequest请求对象
	 */
	void datagrid(AuthShortcut authShortcut, DataGrid<AuthShortcut> datagrid,HttpServletRequest request);
	/**
	 * 获得对象
	 * @param id
	 * @return
	 */
	AuthShortcut get(String id);
	/**
	 * 删除，可以单个删除也可以批量删除，如果是批量删除，那么id 逗号隔开
	 * @param id
	 */
	void delete(String id);
	/**
	 * 更新
	 * @param authShortcut
	 */
	void update(AuthShortcut authShortcut);
	/**
	 * 添加
	 * @param authShortcut
	 */
	void add(AuthShortcut authShortcut);
	/**
	 * 通过名字查询
	 * @param param
	 * @return
	 */
	List<AuthShortcut> find(String param);
	
}
