package com.bugframework.auth.dao.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.springframework.stereotype.Repository;
import com.bugframework.auth.dao.RoleDao;
import com.bugframework.auth.pojo.AuthRole;
import com.bugframework.common.dao.impl.BaseDaoImpl;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.HqlGenerateUtil;

 

@Repository("roleDao")
public class RoleImpl extends BaseDaoImpl<AuthRole> implements RoleDao{

	@Override
	public List<AuthRole> getRoleList() {
		 Criteria criteria=getSession().createCriteria(AuthRole.class);
		 criteria.add(Expression.eq("isenable", 1));
		 criteria.add(Expression.eq("isAdmin", (short)0));
		 criteria.add(Expression.eq("delFlag", 0));
		 List<AuthRole> deptList=criteria.list();  
		return deptList;
	}

	 @Override
		public void datagrid(AuthRole t, DataGrid<AuthRole> datagrid,HttpServletRequest request) {
			Criteria cq =this.getCriteria();
			HqlGenerateUtil.installHql(cq, t);
			this.setDataGridData(cq,datagrid,true);
		}

}
