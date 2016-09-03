package com.bugframework.auth.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bugframework.auth.pojo.AuthRole;
import com.bugframework.common.pojo.DataGrid;
/**
 * 
 * <p>Title:角色服务接口层 </p>
 * <p>Description: 角色服务接口层</p>
 * @author 许增飞
 * @date 2016-8-14 上午10:27:05
 */
public interface RoleService {
	/**
	 * 获得启用的角色
	 * 
	 * @return
	 */
	List<AuthRole> getRoleList();

	/**
	 * 通过主键获取角色
	 * 
	 * @param id
	 *            角色ID
	 * @return
	 */
	AuthRole get(String id);
	/**
	 * 分页查询
	 * @param role 角色对象值
	 * @param datagrid 分页模型
	 * @param request  HttpServletRequest 请求
	 */
	void datagrid(AuthRole role, DataGrid<AuthRole> datagrid,HttpServletRequest request);
	/**
	 * 更新
	 * @param s 对象值，ID不能为空
	 */
	void update(AuthRole s);
	/**
	 * 批量删除角色
	 * @param id 角色ID，逗号隔开
	 */
	void deleteAlllogic(String id);
	/**
	 * 单个删除操作
	 * @param id 角色ID
	 */
	void delete(String id);
	/**
	 * 添加操作
	 * @param role
	 */
	void add(AuthRole role);
	/**
	 * 通过名字查询
	 * @param param
	 * @return
	 */
	List<AuthRole> findByName(String param);
}
