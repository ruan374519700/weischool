package com.weischool.dao.restful.impl;

import org.springframework.stereotype.Repository;

import com.bugframework.common.dao.impl.BaseDaoImpl;
import com.weischool.dao.restful.RestfulDao;
import com.weischool.pojo.restful.RestfulInfo;
@Repository("restfulDao")
public class RestfulDaoImpl extends BaseDaoImpl<RestfulInfo> implements RestfulDao{

}
