package com.bugframework.auth.dao.impl;


 

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import com.bugframework.auth.dao.ModuleDao;
import com.bugframework.auth.pojo.AuthModule;
import com.bugframework.common.dao.impl.BaseDaoImpl;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.HqlGenerateUtil;

 

@Repository("moduleDao")
public class ModuleImpl extends BaseDaoImpl<AuthModule> implements ModuleDao{

 

	 @Override
		public void datagrid(AuthModule t, DataGrid<AuthModule> datagrid,HttpServletRequest request) {
			Criteria cq =this.getCriteria();
			cq.add(Expression.eq("pid", request.getParameter("parentid")));
			cq.addOrder(Order.asc("orderby"));
			cq.addOrder(Order.desc("createTime"));
			HqlGenerateUtil.installHql(cq, t);
			this.setDataGridData(cq,datagrid,true);
		}

	 
}
