package com.weischool.junit.restful;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bugframework.auth.dao.RoleDao;
import com.bugframework.auth.pojo.AuthRole;
import com.weischool.junit.BaseTest;
import com.weischool.pojo.restful.RestfulInfo;
import com.weischool.service.restful.RestfulService;

public class RestfulTest extends BaseTest {
	@Autowired
	private RestfulService restfulService;
	@Autowired
	private  RoleDao roleDao ;
	@Test
	public void getById() {
		RestfulInfo restfulInfo = restfulService.getById("1");
		System.out.println("输出" + restfulInfo);
	}
	@Test
	public void save(){
		RestfulInfo restfulInfo = new RestfulInfo();
		restfulInfo.setCt(121212L);
		restfulInfo.setName("张三");
		restfulService.save(restfulInfo);
		 
	}
	@Test
	public void del(){
		restfulService.del("1");
	}
	@Test
	public void roleA(){
		AuthRole r =new AuthRole();
		r.setName("xxx");
		this.roleDao.add(r);
	} 

}
