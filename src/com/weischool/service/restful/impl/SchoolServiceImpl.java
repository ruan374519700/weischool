package com.weischool.service.restful.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bugframework.common.pojo.DataGrid;
import com.weischool.dao.restful.SchoolDao;
import com.weischool.pojo.restful.ScInfo;
import com.weischool.service.restful.SchoolService;

/**
 * @author 煎鱼
 * @since 2016-8-21
 * Description	School接口实现层
 */
@Service("SchoolService")
public class SchoolServiceImpl implements SchoolService{
	@Autowired
	private SchoolDao schoolDao;

	@Override
	public ScInfo getById(String id) {
	 
		if (id == null)
			return null; 
		return this.schoolDao.get(id);
	}
	
	@Transactional
	@Override
	public void save(ScInfo school) {
		System.out.println("School 插入="+school);
		this.schoolDao.add(school);
		 
	}

	@Override
	public void add(ScInfo school) {
		this.schoolDao.add(school);
		
	}
	
	@Override
	public void update(ScInfo school) {
		this.schoolDao.update(school);
		
	}
	
	@Override
	public void del(String id) {
		schoolDao.delete(id);
		
	}
	@Override
	public void datagrid(ScInfo schoolInfo,
			DataGrid<ScInfo> datagrid, HttpServletRequest request) {
		schoolDao.datagrid(schoolInfo, datagrid, request);
		
	}
	@Override
	public List<ScInfo> findByName(String param) {
		// TODO Auto-generated method stub
		return null;
	}

}
