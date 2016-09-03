package com.weischool.controller.restful;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.HqlGenerateUtil;
import com.weischool.pojo.restful.RestfulInfo;
import com.weischool.service.restful.RestfulService;

/**
 * 
 * <p>
 * Title:RestulController
 * </p>
 * <p>
 * Description: restful例子
 * </p>
 * 
 * @author 许增飞
 * @date 上午10:37:11
 */
@Controller
@RequestMapping("/admin/restful")
public class RestulController {
	@Autowired
	private RestfulService restfulService;
	 
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
		return new ModelAndView("/restful/list", "returnValue", "返回成功");
	}
	@RequestMapping(value="/2",method =RequestMethod.GET)
	public ModelAndView toAdd() {
		return new ModelAndView("/restful/add", "returnValue", "进入添加界面");
	}
	@RequestMapping(value="/3",method =RequestMethod.GET)
	public ModelAndView toEdit() {
		return new ModelAndView("/restful/add", "returnValue", "返回成功");
	}
	@RequestMapping(value="/4",method =RequestMethod.GET)
	@ResponseBody
	public AjaxJson  datagrid(HttpServletRequest request,RestfulInfo restfulInfo,DataGrid<RestfulInfo> datagrid){
		restfulService.datagrid(restfulInfo,datagrid,request);
		return HqlGenerateUtil.datagrid(datagrid);
	}
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
	}
}
