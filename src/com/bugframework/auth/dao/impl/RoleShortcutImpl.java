package com.bugframework.auth.dao.impl;
 

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.springframework.stereotype.Repository;

import com.bugframework.auth.dao.RoleShortcutDao;
import com.bugframework.auth.pojo.AuthRoleShortcut;
import com.bugframework.common.dao.impl.BaseDaoImpl;
import com.bugframework.common.utility.DataUtils;
 

@Repository("roleShortcutDao")
public class RoleShortcutImpl extends BaseDaoImpl<AuthRoleShortcut> implements RoleShortcutDao {

	@Override
	public void delelteRoleShortcutByShortcutId(String id) {
		
		Criteria cq =this.getCriteria();
		cq.createAlias("shortcut", "s");
		cq.add(Expression.in("s.id", id.split(",")));
		List  list =cq.list();
		if(DataUtils.isListNotEmpty(list)){
			this.batchDel(list);
		}
	}
 


}
