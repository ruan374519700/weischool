package com.bugframework.auth.dao;

import java.util.List;
import com.bugframework.auth.pojo.AuthPermisstion;

import com.bugframework.common.dao.BaseDao;

public interface PermissionDao extends BaseDao<AuthPermisstion> {

	List<AuthPermisstion> findPermissionByRole(String id);

	List<AuthPermisstion> getleftMenu(String id);
}
