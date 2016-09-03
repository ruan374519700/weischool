
package com.bugframework.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.pojo.PageModel;


 



/**
 * 通用泛型Dao接口
 */
public interface BaseDao<T> {
	 
	/**
	 * 将datagrid赋值
	 * @param cq 
	 * @param datagrid 
	 * @param isOffset 是否分页
	 */
	public void setDataGridData(final Criteria cq, DataGrid<T> datagrid, final boolean isOffset);
	
	public void datagrid(T t, DataGrid<T> datagrid,
			HttpServletRequest request);

	
	public void add(T entity) ;
	public void batchAdd(List<T> entity);
	public void update(T entity) ;
	public void delete(T entity) ;
	public void delete(String name,Serializable id) ;
	public void deleteByProperty(String propertyName,Object propertyVal);
	public void delete(Serializable id) ;
	public void deleteAll(Serializable id);
	/**
	 * 逻辑删除
	 * @param id
	 */
	public void deletelogic(Serializable id);
	public void deletelogic(Serializable id,String className);
	/**
	 * 逻辑批量删除
	 * @param id
	 */
	public void deleteAlllogic(Serializable id);
	public void deleteAlllogic(Serializable id,String className);
	public T get(Serializable id) ;
	public T load(Serializable id) ;
	
	public long countResult() ;
	public int countHqlResult(String hql, Object... values) ;


	public List<T> findAll() ;
	public List<T> findAll(String orderByProperty, boolean desc) ;
	
	public PageModel<T> findByPager() ;
	public PageModel<T> findByPager(int pageNo, int pageSize, String orderByProperty, boolean desc) ;
	
	public List<T> find(String hql, Object... values) ;
	public PageModel<T> find(int pageNo, int pageSize, String hql, Object... values) ;
	public List<Object[]> find2(String hql, Object... values);
	
	public T findUniqueResult(String hql, Object... values) ;
	public T findUniqueResult(String hql, Map<String,?> values) ;
	
	public List<T> find(String hql, Map<String,?> values) ;
	public PageModel<T> find(int pageNo, int pageSize, String hql, Map<String,?> values) ;
	
	public int batchExecute(String hql, Object... values) ;
	public List<T> getBatch(Serializable... ids) ;

	void batchDel(List<T> entity);
}
