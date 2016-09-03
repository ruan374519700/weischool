package com.bugframework.auth.dao.impl;

 
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;
 
import com.bugframework.auth.dao.ShortcutDao;
import com.bugframework.auth.pojo.AuthRoleShortcut;
import com.bugframework.auth.pojo.AuthShortcut;
import com.bugframework.common.dao.impl.BaseDaoImpl;
 
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.DataUtils;
import com.bugframework.common.utility.HqlGenerateUtil;
@Repository(value="shortcutDao")
public class ShortcutImpl extends BaseDaoImpl<AuthShortcut> implements ShortcutDao {

	 @Override
	public void datagrid(AuthShortcut t, DataGrid<AuthShortcut> datagrid,
			HttpServletRequest request) {
			Criteria cq =this.getSession().createCriteria(AuthShortcut.class);
			cq.addOrder(Order.asc("orderby"));
			HqlGenerateUtil.installHql(cq, t);
			this.setDataGridData(cq,datagrid,true);
	}

	@Override
	public int getMaxOrderby() {
		Criteria cq=this.getCriteria();
		ProjectionList prolist=Projections.projectionList();
		prolist.add(Projections.max("orderby"));
		cq.setProjection(prolist);   
		List list=cq.list();
		Iterator it=list.iterator();   
		if(it.hasNext()){ 
			Integer value =(Integer)it.next();
			if(value!=null&&!"null".equals(value+""))
				 return value;  
		}  
		return 0;
	}

	 

	@Override
	public List<AuthRoleShortcut> getshortcutByrole(String roleid) {
		Criteria cq =this.getSession().createCriteria(AuthRoleShortcut.class); 
		cq.createAlias("role", "r");
		cq.add(Expression.eq("r.id", roleid));
		cq.createAlias("shortcut", "s");
		cq.addOrder(Order.asc("s.orderby"));
		List list =cq.list();
		return list;
	}

	@Override
	public List<AuthShortcut> findShortcutByIds(String id) {
		if(DataUtils.isStrNotEmpty(id)){
			Criteria cq =this.getCriteria();
			cq.add(Expression.in("id", id.split(",")));
			List list =cq.list();
			return list;
		}
		return null;
	}

}
