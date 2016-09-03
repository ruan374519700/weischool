package com.weischool.dao.restful.impl;

import org.springframework.stereotype.Repository;

import com.bugframework.common.dao.impl.BaseDaoImpl;
import com.weischool.dao.restful.SchoolDao;
import com.weischool.pojo.restful.ScInfo;

@Repository("SchoolDao")
public class SchoolDaoImpl extends BaseDaoImpl<ScInfo> implements SchoolDao{

}
