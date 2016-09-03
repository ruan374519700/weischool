package com.weischool.service.restful.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bugframework.common.pojo.DataGrid;
import com.weischool.dao.restful.RestfulDao;
import com.weischool.pojo.restful.RestfulInfo;
import com.weischool.service.restful.RestfulService;

/**
 * 
 * <p>
 * Title:RestfulServiceImpl
 * </p>
 * <p>
 * Description: restful接口实现层
 * </p>
 * 
 * @author 许增飞
 * @date 上午11:51:04
 */
@Service("restfulService")
public class RestfulServiceImpl implements RestfulService {
	@Autowired
	private RestfulDao restfulDao;

	@Override
	public RestfulInfo getById(String id) {
	 
		if (id == null)
			return null;
		return this.restfulDao.get(id);
	}
	@Transactional
	@Override
	public void save(RestfulInfo restful) {
		System.out.println("restful 插入="+restful);
		this.restfulDao.add(restful);
		 
	}

	@Override
	public void del(String id) {
		restfulDao.delete(id);
		
	}
	@Override
	public void datagrid(RestfulInfo restfulInfo,
			DataGrid<RestfulInfo> datagrid, HttpServletRequest request) {
		restfulDao.datagrid(restfulInfo, datagrid, request);
		
	}

}
