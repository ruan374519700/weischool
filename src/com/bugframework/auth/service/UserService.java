package com.bugframework.auth.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bugframework.auth.pojo.AuthUser;
import com.bugframework.common.pojo.DataGrid;
/**
 * 
 * <p>Title:用户服务接口层 </p>
 * <p>Description: 用户服务接口层</p>
 * @author 许增飞
 * @date 上午8:43:28
 */
public interface UserService {
	/**
	 * 查找用户，通过帐号和密码
	 * 
	 * @param user
	 * @return
	 */
	AuthUser findUser(AuthUser user);

	/**
	 * 获取可用的用户
	 * 
	 * @return
	 */
	List<AuthUser> enableUser();

	/**
	 * 更新用户
	 * 
	 * @param sysuser
	 */
	void update(AuthUser sysuser);

	/**
	 * 获得分页列表
	 * 
	 * @param user
	 *            用户
	 * @param datagrid
	 *            分页模型
	 * @param request
	 *            HttpServletRequest
	 */
	void datagrid(AuthUser user, DataGrid<AuthUser> datagrid,
			HttpServletRequest request);

	/**
	 * 通过主键获取用户
	 * 
	 * @param id
	 * @return
	 */
	AuthUser get(String id);

	/**
	 * 添加用户
	 * 
	 * @param sysUser
	 */
	void add(AuthUser sysUser);

	/**
	 * 批量逻辑删除
	 * 
	 * @param id
	 *            传入用户ID，多个ID 逗号隔开
	 */
	void deleteAlllogic(String id);

	/**
	 * 逻辑删除
	 * 
	 * @param id
	 */
	void deletelogic(String id);

	/**
	 * 获取用户
	 * 
	 * @param name
	 *            用户表属性名称
	 * @param value
	 *            用户表属性值
	 * @return List<AuthUser>
	 */
	List<AuthUser> getUser(String name, String value);


}
