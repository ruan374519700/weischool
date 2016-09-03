package com.weischool.controller.restful;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bugframework.auth.pojo.AuthRole;
import com.bugframework.auth.pojo.AuthUser;
import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.DataUtils;
import com.bugframework.common.utility.Globals;
import com.bugframework.common.utility.HqlGenerateUtil;
import com.bugframework.common.utility.ValidForm;
import com.weischool.pojo.restful.ScInfo;
import com.weischool.service.restful.SchoolService;

/**
 * @author 煎鱼
 * @since 2016-8-21
 * Description
 */
@Controller
@RequestMapping("/admin/school")
public class SchoolController {
	@Autowired
	private SchoolService schoolService;
	 
	/**
	 * 进入列表界面 1.地址会解析成 http://地址:端口/工程名称/restful/1。如果是带参数的话
	 * http://地址:端口/工程名称/restful/1?参数1=xx&参数2=xx<br/>
	 * 2.用GET的方式请求<br/>
	 * 3.ModelAndView 有一些返回attribute方法 不用我们写 request.setAttribute
	 * 
	 * @return 返回 restful文件夹下面 list.jsp界面
	 */
	@RequestMapping(value = "/1", method = RequestMethod.GET)
	public ModelAndView list() {
		return new ModelAndView("/school/list", "returnValue", "返回成功");
	}
	
	@RequestMapping(value="/2",method =RequestMethod.GET)
	public ModelAndView toAdd() {
		return new ModelAndView("/school/school", "returnValue", "进入添加界面");
	}
	
	@RequestMapping(value="/3",method =RequestMethod.GET)
	public ModelAndView toEdit() {
		return new ModelAndView("/school/school", "returnValue", "返回成功");
	}
	
	@RequestMapping(value="/4",method =RequestMethod.GET)
	@ResponseBody
	public AjaxJson  datagrid(HttpServletRequest request,ScInfo scInfo,DataGrid<ScInfo> datagrid){
		schoolService.datagrid(scInfo,datagrid,request);
		return HqlGenerateUtil.datagrid(datagrid);
	}
	
	@RequestMapping(params = "addorupd")
	@ResponseBody
	public AjaxJson addorupd(ScInfo scInfo, HttpServletRequest request,HttpSession  session) {
		AjaxJson j  =new AjaxJson();
		if(!DataUtils.isStrEmpty(scInfo.getId())){//更新操作
			ScInfo s = schoolService.getById(scInfo.getId());
			s.setUpdateBy(scInfo.getId());
			s.setName(scInfo.getName());
			s.setHead_master(scInfo.getHead_master());
			s.setM_phone(scInfo.getM_phone());
			s.setT_phone(scInfo.getT_phone());
			s.setRemark(scInfo.getRemark());
			s.setIsenable(scInfo.getIsenable());
			schoolService.update(s);
			j.setMsg(Globals.UPD_SUCCESS);
			j.setSuccess(true);
		}else{
			scInfo.setCreateBy(scInfo.getId());
			scInfo.setCreateTime(new Date());
			schoolService.add(scInfo);
			j.setMsg(Globals.ADD_SUCCESS);
			j.setSuccess(true);
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
				ScInfo m = schoolService.getById(id);
				if(m!=null)olderValue =m.getName();
			}
			if(param.equals(olderValue)){//这是更新操作的判断,如果相等，说明角色名称没有修改过，也不需要校验
			
			}else{
				List<ScInfo> school =schoolService.findByName(param);
				if(school.size()>0){
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
	
}
