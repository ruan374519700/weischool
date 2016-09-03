package com.bugframework.auth.service;

import java.util.List;

import com.bugframework.auth.pojo.AuthPermisstion;
/**
 * 
 * <p>Title:PermissionService </p>
 * <p>Description: 权限服务接口层</p>
 * @author 许增飞
 * @date 上午9:02:29
 */
public interface PermissionService {
	/**
	 * 通过角色ID获得权限列表
	 * 
	 * @param id
	 *            传入角色ID
	 * @return List<AuthPermisstion>
	 */
	List<AuthPermisstion> findPermissionByRole(String id);

	/**
	 * 获得系统左边菜单栏
	 * 
	 * @param id
	 *            传入角色ID
	 * @return List<AuthPermisstion>
	 */
	List<AuthPermisstion> getleftMenu(String id);
	/**
	 * 指定的属性删除
	 * @param name
	 * @param value
	 */
	void delete(String name, String value);
	/**
	 * 批量添加
	 * @param permisstions
	 */
	void add(List<AuthPermisstion> permisstions);
}
