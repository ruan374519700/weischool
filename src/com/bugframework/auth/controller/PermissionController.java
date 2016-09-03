package com.bugframework.auth.controller;

import java.util.ArrayList;
import java.util.List;
 
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bugframework.auth.pojo.AuthModule;
import com.bugframework.auth.pojo.AuthPermisstion;
import com.bugframework.auth.pojo.AuthRole;
import com.bugframework.auth.service.ModuleService;
import com.bugframework.auth.service.PermissionService;
import com.bugframework.auth.service.RoleService;
import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.BaseTree;
import com.bugframework.common.utility.DataUtils;
import com.bugframework.common.utility.Globals;

@Controller
@RequestMapping("/auth/permission.do")
public class PermissionController {
 
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(params="permission")
	public ModelAndView  permission(String roleid,HttpServletRequest request){
		request.setAttribute("roleid", roleid);
		return new ModelAndView("/auth/permission");
	}
	
	@RequestMapping(params="getTree")
	@ResponseBody
	public List<BaseTree>  getTree( HttpServletRequest request,String id){
	 //  List<AuthModule>  listModule =	moduleService.find("from AuthModule a where a.delFlag =? and a.isenable =?  order by a.orderby asc,a.createTime desc",0,1); 
	   List<AuthModule>  listModule =	moduleService.find(0,1);//获得未删除并且已经启用的模块
	   List<AuthPermisstion> permi = permissionService.findPermissionByRole(id);
	   
	   List<BaseTree> list = new ArrayList<BaseTree>();
		for(AuthModule a :listModule){
			boolean checked =false;
			outer:for(int i =0;i<permi.size();i++){
				if(permi.get(i).getModule().getId().equals(a.getId())){
					checked =true;
					permi.remove(i);
					break outer;
				}
			}
			if(a.getId().equals("1")){
				list.add(new BaseTree(a.getId(),a.getPid(),a.getName(),true,checked)) ;
			}else{
				list.add(new BaseTree(a.getId(),a.getPid(),a.getName(),false,checked)) ;
			}
		}
		
		return list;
		
	}
	@RequestMapping(params="saveTree")
	@ResponseBody
	public AjaxJson saveTree(String roleid,String id,HttpServletRequest request){
		AjaxJson j=new AjaxJson();
		try {
			if(DataUtils.isStrNotEmpty(roleid)){
				List<AuthModule> modules =moduleService.get(id.split(","));
				AuthRole role = roleService.get(roleid);
				List<AuthPermisstion> permisstions =new ArrayList<AuthPermisstion>();
				for(AuthModule a :modules){
					AuthPermisstion permission = new AuthPermisstion();
					permission.setModule(a);
					permission.setRole(role);
					permisstions.add(permission);
				}
				permissionService.delete("role.id", roleid);
				permissionService.add(permisstions);
				j.setMsg(Globals.ADD_SUCCESS);
				j.setSuccess(true);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	return j;	
	}
}
