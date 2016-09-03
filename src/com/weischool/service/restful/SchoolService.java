package com.weischool.service.restful;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bugframework.common.pojo.DataGrid;
import com.weischool.pojo.restful.ScInfo;

/**
 * 
 * @author 煎鱼
 * @since 2016-8-21
 * Description	服务层接口
 */
public interface SchoolService {
	/**
	 * 通过主键查找
	 * 
	 * @param id
	 *            传入主键ID
	 * @return 如果主键ID为空或者找不到数据 返回null
	 */
	ScInfo getById(String id);

	/**
	 * 保存数据
	 * 
	 * @param restful
	 *            传入对象值
	 */
	void save(ScInfo school);
	
	/**
	 * 更新
	 * @param school 对象值，ID不能为空
	 */
	void update(ScInfo school);
	
	/**
	 * 添加操作
	 * @param scInfo
	 */
	void add(ScInfo scInfo);
	
	void del(String id);
	/**
	 * 获得列表
	 * @param ScInfo 传入对象值
	 * @param datagrid 对象模型
	 * @param request HttpServletRequest 请求对象
	 */
	
	List<ScInfo> findByName(String param);
	
	void datagrid(ScInfo scinfo, DataGrid<ScInfo> datagrid,
			HttpServletRequest request);

}
