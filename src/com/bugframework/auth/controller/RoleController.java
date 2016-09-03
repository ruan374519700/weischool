package com.bugframework.auth.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
 
import com.bugframework.auth.pojo.AuthModule;
import com.bugframework.auth.pojo.AuthRole;
import com.bugframework.auth.pojo.AuthUser;
import com.bugframework.auth.service.ModuleService;
import com.bugframework.auth.service.RoleService;
import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.BaseTree;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.DataUtils;
import com.bugframework.common.utility.Globals;
import com.bugframework.common.utility.HqlGenerateUtil;
import com.bugframework.common.utility.ValidForm;
 

@Controller
@RequestMapping("/auth/role.do")
public class RoleController {
 @Autowired
 private RoleService roleService;
 @Autowired
 private ModuleService moduleService;
	/**
	 * 進入角色列表
	 * @param request
	 * @return
	 */
	@RequestMapping(params="rolelist")
	public ModelAndView  rolelist(HttpServletRequest request){
		return new ModelAndView("/auth/rolelist");
	}
	/**
	 * 获得列表数据
	 * @param request
	 * @param role
	 * @param datagrid
	 * @return
	 */
	@RequestMapping(params="datagrid")
	@ResponseBody
	public AjaxJson  datagrid(HttpServletRequest request,AuthRole role,DataGrid<AuthRole> datagrid){
		roleService.datagrid(role,datagrid,request);
		return HqlGenerateUtil.datagrid(datagrid);
	}
	/**
	 * 進入添加或者修改頁面
	 * @param request
	 * @return
	 */
	@RequestMapping(params="roleaddorupd")
	public ModelAndView  roleaddorupd(String id,HttpServletRequest request){
		if(!DataUtils.isStrEmpty(id)){
			AuthRole obj = roleService.get(id);
			request.setAttribute("obj", obj);
		}
		return new ModelAndView("/auth/role");
	}
	
	@RequestMapping(params = "addorupd")
	@ResponseBody
	public AjaxJson addorupd(AuthRole role, HttpServletRequest request,HttpSession  session) {
		AjaxJson j  =new AjaxJson();
		AuthUser user =(AuthUser)session.getAttribute(Globals.USER_SESSION);
		if(!DataUtils.isStrEmpty(role.getId())){//更新操作
			AuthRole s = roleService.get(role.getId());
			s.setUpdateBy(user.getId());
			s.setUpdateTime(new Date());
			s.setIsenable(role.getIsenable());
			s.setName(role.getName());
			s.setDescription(role.getDescription());
			roleService.update(s);
			j.setMsg(Globals.UPD_SUCCESS);
			j.setSuccess(true);
		}else{
			role.setCreateBy(user.getId());
			role.setCreateTime(new Date());
			roleService.add(role);
			j.setMsg(Globals.ADD_SUCCESS);
			j.setSuccess(true);
		}
		return j;
	}
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(AuthRole role, HttpServletRequest request,HttpSession  session) {
		AjaxJson j  =new AjaxJson();
		try {
			if(!DataUtils.isStrEmpty(role.getId())){
				//userDao.delete("AuthRole.id",role.getId());//删除多个用户，后续是不给删除，除非该角色未使用
				if(role.getId().indexOf(",")>-1){//批量删除
					roleService.deleteAlllogic(role.getId());
				}else{
					roleService.delete(role.getId());
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
	public AjaxJson isenabled(AuthRole role, HttpServletRequest request) {
		AjaxJson j  =new AjaxJson();
		try {
			if(!DataUtils.isStrEmpty(role.getId())){
				AuthRole s = roleService.get(role.getId());
				s.setIsenable(role.getIsenable());
				roleService.update(s);
				if(role.getIsenable()==1)
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
			String olderValue=null;//旧的角色
			if(DataUtils.isStrNotEmpty(id)){
				AuthRole m = roleService.get(id);
				if(m!=null)olderValue =m.getName();
			}
			if(param.equals(olderValue)){//这是更新操作的判断,如果相等，说明角色名称没有修改过，也不需要校验
			
			}else{
				//List<AuthRole> roles =roleService.find(" from AuthRole r where r.name=?", param);
				List<AuthRole> roles =roleService.findByName(param);
				if(roles.size()>0){
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
	
	@RequestMapping(params="getTree")
	@ResponseBody
	public List<BaseTree>  getTree( HttpServletRequest request,String id){
	   List<AuthModule>  listModule =	moduleService.find(0,1);//获得未删除并且已经启用的模块
	   List<BaseTree> list = new ArrayList<BaseTree>();
		for(AuthModule a :listModule){
			if(a.getId().equals("1")){
				list.add(new BaseTree(a.getId(),a.getPid(),a.getName(),true,false)) ;
			}else{
				list.add(new BaseTree(a.getId(),a.getPid(),a.getName(),false,false)) ;
			}
		}
		
		return list;
		
	}
}
