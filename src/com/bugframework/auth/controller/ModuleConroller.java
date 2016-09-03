package com.bugframework.auth.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bugframework.auth.dao.ModuleDao;
import com.bugframework.auth.pojo.AuthModule;
import com.bugframework.auth.pojo.AuthUser;
import com.bugframework.auth.service.ModuleService;
import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.BaseTree;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.DataUtils;
import com.bugframework.common.utility.Globals;
import com.bugframework.common.utility.HqlGenerateUtil;
import com.bugframework.common.utility.ValidForm;

@Controller
@RequestMapping("/auth/module.do")
public class ModuleConroller {
	@Autowired
	private ModuleService moduleService;
	
	@RequestMapping(params="moduletab")
	public ModelAndView  moduletab(){
		return new ModelAndView("/auth/modulemain");
	}
	@RequestMapping(params="top")
	public ModelAndView  top(){
		return new ModelAndView("/auth/moduletop");
	}
	@RequestMapping(params="tab")
	public ModelAndView  tab(){
		return new ModelAndView("/auth/moduletab");
	}
	
	@RequestMapping(params="getTree")
	@ResponseBody
	public List<BaseTree>  getTree( HttpServletRequest request){
	   List<AuthModule>  listModule =	this.moduleService.find();
	   List<BaseTree> list = new ArrayList<BaseTree>();
		for(AuthModule a :listModule){
			if(a.getId().equals("1")||a.getId().equals("0")){
				list.add(new BaseTree(a.getId(),a.getPid(),a.getName(),true,false)) ;
			}else{
				list.add(new BaseTree(a.getId(),a.getPid(),a.getName(),false,false)) ;
			}
		}
		
		return list;
		
	}
	
	@RequestMapping(params="modulelist")
	public ModelAndView  modulelist(HttpServletRequest request,String pid){
		request.setAttribute("pid", pid);
		return new ModelAndView("/auth/modulelist");
	}
	
	@RequestMapping(params="datagrid")
	@ResponseBody
	public AjaxJson  datagrid(HttpServletRequest request,AuthModule module,DataGrid<AuthModule> datagrid){
		this.moduleService.datagrid(module,datagrid,request);
		return HqlGenerateUtil.datagrid(datagrid);
	}
	/**
	 * 進入添加或者修改頁面
	 * @param request
	 * @return
	 */
	@RequestMapping(params="module")
	public ModelAndView  module(String id,String pid,HttpServletRequest request){
		if(!DataUtils.isStrEmpty(id)){
			AuthModule obj = this.moduleService.get(id);
			request.setAttribute("obj", obj);
		}
		request.setAttribute("pid", pid);
		return new ModelAndView("/auth/module");
	}
	@RequestMapping(params = "addorupd")
	@ResponseBody
	public AjaxJson addorupd(AuthModule module, HttpServletRequest request,HttpSession  session) {
		AjaxJson j  =new AjaxJson();
		AuthUser user =(AuthUser)session.getAttribute(Globals.USER_SESSION);
		if(!DataUtils.isStrEmpty(module.getId())){//更新操作
			AuthModule s = this.moduleService.get(module.getId());
			s.setUpdateBy(user.getId());
			s.setUpdateTime(new Date());
			s.setIsenable(module.getIsenable());
			s.setName(module.getName());
			s.setUrl(module.getUrl());
			s.setOrderby(module.getOrderby());
			s.setIcon(module.getIcon());
			this.moduleService.update(s);
			j.setMsg(Globals.UPD_SUCCESS);
			j.setSuccess(true);
		}else{
			if(module.getPid().equals("0")){
				module.setFloor((short)1);
			}else{
				AuthModule s = this.moduleService.get(module.getPid());
				short floor =(short)(s.getFloor()+1);
				module.setFloor(floor);
			}
			module.setCreateBy(user.getId());
			module.setCreateTime(new Date());
			this.moduleService.add(module);
			j.setMsg(Globals.ADD_SUCCESS);
			j.setSuccess(true);
		}
		return j;
	}
	
	
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(AuthModule module, HttpServletRequest request,HttpSession  session) {
		AjaxJson j  =new AjaxJson();
		try {
			if(!DataUtils.isStrEmpty(module.getId())){
				if(module.getId().indexOf(",")>-1){//批量删除
					this.moduleService.deleteAlllogic(module.getId());
				}else{
					this.moduleService.deletelogic(module.getId());
				}
				j.setMsg(Globals.DEL_SUCCESS);
				j.setSuccess(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}
	@RequestMapping(params = "isenabled")
	@ResponseBody
	public AjaxJson isenabled(AuthModule module, HttpServletRequest request) {
		AjaxJson j  =new AjaxJson();
		try {
			if(!DataUtils.isStrEmpty(module.getId())){
				AuthModule s = this.moduleService.get(module.getId());
				s.setIsenable(module.getIsenable());
				this.moduleService.update(s);
				if(module.getIsenable()==1)
					j.setMsg(Globals.ENABLE_SUCCESS);
				else
					j.setMsg(Globals.DISABLE_SUCCESS);
				j.setSuccess(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}
	@RequestMapping(params = "isExist")
	@ResponseBody
	public ValidForm isExist(HttpServletRequest request,String id) {
		ValidForm v = new ValidForm();
		try {
			String param=request.getParameter("param");//获得该字段的值,新输入的值
			String pid =request.getParameter("pid");
			String olderValue =null;
			if(DataUtils.isStrNotEmpty(id)){
				AuthModule m =this.moduleService.get(id);
				if(m!=null)olderValue =m.getName();
			}
			if(param.equals(olderValue)){//这是更新操作的判断,如果相等，说明角色名称没有修改过，也不需要校验
			
			}else{
				List<AuthModule> modules =this.moduleService.find(param,pid);
				if(modules.size()>0){
					v.setStatus("n");
					v.setInfo("【"+param+"】已经存在!");
				}
			}
		} catch (Exception e) {
			v.setStatus("n");
			v.setInfo("数据库异常");
			e.printStackTrace();
		}
		return v;
	}
	@RequestMapping(params="moduleTreeSelect")
	public ModelAndView  moduleTreeSelect(HttpServletRequest request,String pid){
		return new ModelAndView("/auth/moduleTreeSelect");
	}
	
	/**
	 * 获得模块信息
	 * @param request
	 * @return
	 */
	@RequestMapping(params="getmodule")
	@ResponseBody
	public AjaxJson  getmodule(AuthModule module,HttpServletRequest request){
		AjaxJson j  =new AjaxJson();
		if(!DataUtils.isStrEmpty(module.getId())){
			AuthModule obj = this.moduleService.get(module.getId());
			j.setObj(obj) ;
			j.setSuccess(true);
		}
		return j;
	}
}
