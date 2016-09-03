package com.weischool.junit;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
 
import com.weischool.pojo.restful.RestfulInfo;
import com.weischool.service.restful.RestfulService;

@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/springmvc-servlet.xml",
		"classpath:/hib-config.xml" })
public class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Before
	public void setUp() throws Exception {
		System.out.println("******************方法执行之前*************************");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("******************方法执行完成*************************");
	}
	
	 
}
