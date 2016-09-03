package com.bugframework.auth.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
 
import com.bugframework.auth.pojo.AuthRole;
import com.bugframework.auth.pojo.AuthUser;
import com.bugframework.auth.service.RoleService;
import com.bugframework.auth.service.UserService;
import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ContextHolderUtils;
import com.bugframework.common.utility.DataUtils;
import com.bugframework.common.utility.Globals;
import com.bugframework.common.utility.HqlGenerateUtil;
import com.bugframework.common.utility.ValidForm;

@Controller
@RequestMapping("/auth/user.do")
public class UserController {
	 
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;

	@RequestMapping(params = "userlist")
	public ModelAndView userlist(HttpServletRequest request) {
		return new ModelAndView("/auth/userlist");
	}

	@RequestMapping(params = "userdatalist")
	public ModelAndView userdatalist(HttpServletRequest request) {
		return new ModelAndView("/userdatalist");
	}

	@RequestMapping(params = "contactslist")
	public ModelAndView contactslist(HttpServletRequest request) {
		return new ModelAndView("/contactslist");
	}

	@RequestMapping(params = "datagrid")
	@ResponseBody
	public AjaxJson datagrid(HttpServletRequest request, AuthUser user,
			DataGrid<AuthUser> datagrid) {
		userService.datagrid(user, datagrid, request);
		return HqlGenerateUtil.datagrid(datagrid);
	}

	/**
	 * 進入添加或者修改頁面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "useraddorupd")
	public String useraddorupd(String id, Model model) {
		if (!DataUtils.isStrEmpty(id)) {
			AuthUser obj = userService.get(id);
			model.addAttribute("obj", obj);
		}

		List<AuthRole> role = roleService.getRoleList();
	
		StringBuffer  buffer = new StringBuffer() ;
		if(DataUtils.isListNotEmpty(role)){
			int i =0;
			for(AuthRole r:role){
				if(i==0){
					buffer.append(r.getName()+"_"+r.getId());
				}else{
					buffer.append(","+r.getName()+"_"+r.getId());
				}
				i++;
			}
		}
		model.addAttribute("role", buffer.toString());
		return "/auth/user";
	}

	@RequestMapping(params = "addorupd")
	@ResponseBody
	public AjaxJson addorupd(AuthUser sysUser, String roleid,
			HttpServletRequest request, HttpSession session) {
		AjaxJson j = new AjaxJson();
		AuthUser user = (AuthUser) session.getAttribute(Globals.USER_SESSION);
		AuthRole sysRole = roleService.get(sysUser.getSysRole().getId());
		if (!DataUtils.isStrEmpty(sysUser.getId())) {// 更新操作
			AuthUser s = this.userService.get(sysUser.getId());
			s.setUpdateBy(user.getId());
			s.setUpdateTime(new Date());
			s.setIsenable(sysUser.getIsenable());
			s.setAccount(sysUser.getAccount());
			s.setSysRole(sysRole);
			s.setName(sysUser.getName());
			this.userService.update(s);
			j.setMsg(Globals.UPD_SUCCESS);
			j.setSuccess(true);
		} else {
			sysUser.setCreateBy(user.getId());
			sysUser.setCreateTime(new Date());
			sysUser.setSysRole(sysRole);
			userService.add(sysUser);
			j.setMsg(Globals.ADD_SUCCESS);
			j.setSuccess(true);
		}
		return j;
	}

	@RequestMapping(params = "userdataupd")
	@ResponseBody
	public AjaxJson userdataupd(AuthUser sysUser, HttpServletRequest request,
			HttpSession session) {
		AjaxJson j = new AjaxJson();
		String userid = sysUser.getId();
		if (!DataUtils.isStrEmpty(userid)) {// 更新操作
			AuthUser s = userService.get(sysUser.getId());
			AuthUser user = ContextHolderUtils.getUserSession();
			s.setUpdateBy(user.getId());
			s.setUpdateTime(new Date());
			userService.update(s);
			j.setMsg(Globals.UPD_SUCCESS);
			j.setSuccess(true);
		} else {
			j.setSuccess(false);
		}
		return j;
	}

	@RequestMapping(params = "userdataaddorupd")
	public ModelAndView userdataaddorupd(String id, HttpServletRequest request) {
		short isPersonal = 0;// 修改个人资料还是在列表中修改个人资料，0--在列表中修改个人资料 1--修改自己
		if (DataUtils.isStrEmpty(id)) {// 如果为空的话，说明是修改自己的个人资料，否则是在列表中修改个人资料
			AuthUser user = ContextHolderUtils.getUserSession();
			id = user.getId();
			isPersonal = 1;
		}
		AuthUser obj = userService.get(id);
		request.setAttribute("obj", obj);
		request.setAttribute("isPersonal", isPersonal);
		return new ModelAndView("/userdata");
	}

	@RequestMapping(params = "checkcontacts")
	public ModelAndView checkcontacts(String id, HttpServletRequest request) {
		if (!DataUtils.isStrEmpty(id)) {
			AuthUser obj = userService.get(id);
			request.setAttribute("obj", obj);
		}
		return new ModelAndView("/contacts");
	}

	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(AuthUser user, HttpServletRequest request,
			HttpSession session) {
		AjaxJson j = new AjaxJson();
		if (!DataUtils.isStrEmpty(user.getId())) {
			if (user.getId().indexOf(",") > -1) {
				userService.deleteAlllogic(user.getId());
			} else {
				userService.deletelogic(user.getId());
			}
			j.setMsg(Globals.DEL_SUCCESS);
			j.setSuccess(true);
		}
		return j;
	}

	@RequestMapping(params = "isenabled")
	@ResponseBody
	public AjaxJson isenabled(AuthUser user, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (!DataUtils.isStrEmpty(user.getId())) {
			AuthUser s = userService.get(user.getId());
			s.setIsenable(user.getIsenable());
			userService.update(s);
			if (user.getIsenable() == 1) {
				j.setMsg(Globals.ENABLE_SUCCESS);
			} else {
				j.setMsg(Globals.DISABLE_SUCCESS);
			}
			j.setSuccess(true);
		}
		return j;
	}

	@RequestMapping(params = "tochangepsw")
	public ModelAndView tochangepsw(HttpServletRequest request, String id) {
		if (DataUtils.isStrNotEmpty(id)) {
			AuthUser user = userService.get(id);
			request.setAttribute("user", user);
		}
		return new ModelAndView("/changepsw");
	}

	@RequestMapping(params = "changepsw")
	@ResponseBody
	public AjaxJson changepsw(String password, HttpServletRequest request,
			HttpSession session) {
		AjaxJson j = new AjaxJson();
		String id = request.getParameter("id");
		if (DataUtils.isStrEmpty(id)) {
			AuthUser user = (AuthUser) session
					.getAttribute(Globals.USER_SESSION);
			id = user.getId();
		}
		if (!DataUtils.isStrEmpty(password)) {
			AuthUser u = userService.get(id);
			u.setPassword(password);
			userService.update(u);
			j.setMsg(Globals.UPD_SUCCESS);
			j.setSuccess(true);
		}
		return j;
	}

	@RequestMapping(params = "isExist")
	@ResponseBody
	public ValidForm isExist(HttpServletRequest request, String id) {
		ValidForm v = new ValidForm();
		try {
			String param = request.getParameter("param");// 获得该字段的值,新输入的值。这是vailform
															// ajaxurl自带的提交参数
			String name = request.getParameter("name");// 获得该字段的名称。这是vailform
														// ajaxurl自带的提交参数
			String olderValue = null;// 旧的角色
			if (DataUtils.isStrNotEmpty(id)) {
				AuthUser m = userService.get(id);
				if (m != null) {
					if (name.equals("name")) {
						olderValue = m.getName();
					} else {
						olderValue = m.getAccount();
					}
				}
			}

			if (param.equals(olderValue)) {// 这是更新操作的判断,如果相等，说明名称没有修改过，也不需要校验

			} else {
				/*List<AuthUser> obj = userService.find(" from AuthUser r where r."
						+ name + "=? and delFlag=0", param);
				;*/
				List<AuthUser> obj = userService.getUser(name,param);
				if (obj.size() > 0) {
					v.setStatus("n");
					v.setInfo("【" + param + "】已经存在!");
				}
			}
		} catch (Exception e) {
			v.setStatus("n");
			v.setInfo("数据库异常");
			e.printStackTrace();
		}
		return v;
	}
}
