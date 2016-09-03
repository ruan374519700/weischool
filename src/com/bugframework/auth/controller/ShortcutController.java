package com.bugframework.auth.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bugframework.auth.pojo.AuthRole;
import com.bugframework.auth.pojo.AuthRoleShortcut;
import com.bugframework.auth.pojo.AuthShortcut;
import com.bugframework.auth.service.RoleService;
import com.bugframework.auth.service.RoleShortcutService;
import com.bugframework.auth.service.ShortcutService;
import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.DataUtils;
import com.bugframework.common.utility.Globals;
import com.bugframework.common.utility.HqlGenerateUtil;
import com.bugframework.common.utility.ValidForm;

@Controller
@RequestMapping("/auth/shortcut")
public class ShortcutController {

	@Autowired
	private ShortcutService shortcutService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private RoleShortcutService roleShortcutService;

	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("/auth/shortcutlist");
	}

	@RequestMapping(params = "datagrid")
	@ResponseBody
	public AjaxJson datagrid(HttpServletRequest request,
			AuthShortcut authShortcut, DataGrid<AuthShortcut> datagrid) {
		shortcutService.datagrid(authShortcut, datagrid, request);
		return HqlGenerateUtil.datagrid(datagrid);
	}

	/**
	 * 进入添加或者修改界面
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(AuthShortcut authShortcut,
			HttpServletRequest req) {
		if (DataUtils.isStrNotEmpty(authShortcut.getId())) {
			authShortcut = shortcutService.get(authShortcut.getId());
			req.setAttribute("obj", authShortcut);
		}
		return new ModelAndView("/auth/shortcut");
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(AuthShortcut authShortcut, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			if (DataUtils.isStrNotEmpty(authShortcut.getId())) {
				this.shortcutService.delete(authShortcut.getId());
			}
			j.setMsg(Globals.DEL_SUCCESS);
			j.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}

	/**
	 * 添加修改数据
	 * 
	 * @param dictionaryData
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(AuthShortcut authShortcut, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {

			if (DataUtils.isStrNotEmpty(authShortcut.getId())) {

				this.shortcutService.update(authShortcut);
				j.setMsg(Globals.UPD_SUCCESS);
			} else {
				authShortcut
						.setOrderby(this.shortcutService.getMaxOrderby() + 1);
				authShortcut.setIsenable(1);
				shortcutService.add(authShortcut);
				j.setMsg(Globals.ADD_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}

	/**
	 * 
	 * 
	 * @param dictionaryData
	 * @return
	 */
	@RequestMapping(params = "isenabled")
	@ResponseBody
	public AjaxJson isenabled(AuthShortcut authShortcut,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			if (!DataUtils.isStrEmpty(authShortcut.getId())) {
				AuthShortcut s = this.shortcutService.get(authShortcut.getId());
				s.setIsenable(authShortcut.getIsenable());
				shortcutService.update(s);
				if (authShortcut.getIsenable() == 1)
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

	/**
	 * 判断 字典名称 的唯一性
	 **/
	@RequestMapping(params = "isExist")
	@ResponseBody
	public ValidForm isExist(HttpServletRequest request, String id) {
		ValidForm v = new ValidForm();
		try {
			String param = request.getParameter("param");// 获得该字段的值,新输入的值
			String olderValue = null;// 旧的角色
			if (DataUtils.isStrNotEmpty(id)) {
				AuthShortcut authShortcut = this.shortcutService.get(id);
				if (authShortcut != null)
					olderValue = authShortcut.getName();
			}
			if (param.equals(olderValue)) {// 这是更新操作的判断,如果相等，说明角色名称没有修改过，也不需要校验

			} else {
				// List<AuthShortcut> authShortcuts
				// =shortcutService.find(" from AuthShortcut r where r.name=?",
				// param);
				List<AuthShortcut> authShortcuts = shortcutService.find(param);
				if (authShortcuts.size() > 0) {
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

	@RequestMapping(params = "orderUpOrDown")
	@ResponseBody
	public AjaxJson orderUpOrDown(HttpServletRequest request,
			HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		String id = request.getParameter("id");// 数据项id
		String type = request.getParameter("type");
		 this.shortcutService.orderUpOrDown(id ,type,j);
		return j;
	}

	@RequestMapping(params = "intoset")
	public ModelAndView intoset(HttpServletRequest request, String roleid) {
		List<AuthRoleShortcut> shortcuts = this.shortcutService
				.getshortcutByrole(roleid);
		List<AuthShortcut> authShortcuts = this.shortcutService.find(1);
		for (AuthRoleShortcut a : shortcuts) {
			outer: for (AuthShortcut a1 : authShortcuts) {
				if (a1.getId().equals(a.getShortcut().getId())) {
					a1.setIsSelect(1);
					shortcuts.remove(a1);
					break outer;
				}
			}
		}
		request.setAttribute("obj", authShortcuts);
		request.setAttribute("roleid", roleid);
		return new ModelAndView("/auth/shortcutset");
	}

	@RequestMapping(params = "saveintoset")
	@ResponseBody
	public AjaxJson saveintoset(HttpServletRequest request, String id,
			String roleid) {
		AjaxJson j = new AjaxJson();
		if (DataUtils.isStrNotEmpty(id) && DataUtils.isStrNotEmpty(roleid)) {
			try {
				List<AuthShortcut> shortcuts = this.shortcutService
						.findShortcutByIds(id);
				AuthRole role = this.roleService.get(roleid);
				List<AuthRoleShortcut> authRoleShortcuts = new ArrayList<AuthRoleShortcut>();
				for (AuthShortcut a : shortcuts) {
					AuthRoleShortcut shortcut = new AuthRoleShortcut();
					shortcut.setRole(role);
					shortcut.setShortcut(a);
					authRoleShortcuts.add(shortcut);
				}
				this.roleShortcutService
						.deleteAll(
								" delete AuthRoleShortcut a where a.role.id=? ",
								roleid);// 清除旧的数据
				this.roleShortcutService.batchAdd(authRoleShortcuts);
				j.setSuccess(true);
				j.setMsg("分配成功！");
			} catch (Exception e) {
				j.setSuccess(false);
				j.setMsg("数据有问题！");
				e.printStackTrace();
			}
		}
		return j;
	}
}
