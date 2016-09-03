package com.bugframework.auth.dao.impl;



import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import com.bugframework.auth.dao.PermissionDao;
import com.bugframework.auth.pojo.AuthPermisstion;
import com.bugframework.common.dao.impl.BaseDaoImpl;

 

@Repository("permissionDao")
public class PermissionImpl extends BaseDaoImpl<AuthPermisstion> implements PermissionDao{

	@Override
	public List<AuthPermisstion> findPermissionByRole(String id) {
	 	Criteria cq = this.getCriteria();
	 	cq.createAlias("role", "r");
	 	cq.add(Expression.eq("r.id", id));
	 	cq.createAlias("module", "m");
	 	cq.add(Expression.eq("m.isenable", 1));
	 	cq.add(Expression.eq("m.delFlag", 0));
		return cq.list();
	}

 
	@Override
	public List<AuthPermisstion> getleftMenu(String id) {
	 	Criteria cq = this.getCriteria();
	 	cq.createAlias("role", "r");
	 	cq.add(Expression.eq("r.id", id));
	 	cq.createAlias("module", "m");
	 	cq.add(Expression.eq("m.isenable", 1));
	 	cq.add(Expression.eq("m.delFlag", 0));
	 	cq.addOrder(Order.asc("m.orderby"));
	 	cq.addOrder(Order.desc("m.createTime"));
		return cq.list();
	}

	 
}
