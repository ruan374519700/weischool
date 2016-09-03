package com.weischool.service.restful;

import javax.servlet.http.HttpServletRequest;

import com.bugframework.common.pojo.DataGrid;
import com.weischool.pojo.restful.RestfulInfo;

/**
 * 
 * <p>
 * Title:RestfulService
 * </p>
 * <p>
 * Description: 服务接口层
 * </p>
 * 
 * @author 许增飞
 * @date 上午11:46:02
 */
public interface RestfulService {
	/**
	 * 通过主键查找
	 * 
	 * @param id
	 *            传入主键ID
	 * @return 如果主键ID为空或者找不到数据 返回null
	 */
	RestfulInfo getById(String id);

	/**
	 * 保存数据
	 * 
	 * @param restful
	 *            传入对象值
	 */
	void save(RestfulInfo restful);
	
	void del(String id);
	/**
	 * 获得列表
	 * @param restfulInfo 传入对象值
	 * @param datagrid 对象模型
	 * @param request HttpServletRequest 请求对象
	 */
	void datagrid(RestfulInfo restfulInfo, DataGrid<RestfulInfo> datagrid,
			HttpServletRequest request);

}
