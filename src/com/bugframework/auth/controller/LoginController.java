package com.bugframework.auth.controller;
 
 
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
 
import com.bugframework.auth.pojo.AuthModule;
import com.bugframework.auth.pojo.AuthPermisstion;
import com.bugframework.auth.pojo.AuthRoleShortcut;
import com.bugframework.auth.pojo.AuthShortcut;
import com.bugframework.auth.pojo.AuthUser;
import com.bugframework.auth.service.ModuleService;
import com.bugframework.auth.service.PermissionService;
import com.bugframework.auth.service.ShortcutService;
import com.bugframework.auth.service.UserService;
import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.utility.ContextHolderUtils;
import com.bugframework.common.utility.DataUtils;
import com.bugframework.common.utility.Globals;
import com.bugframework.common.utility.RSAUtils;
import com.google.code.kaptcha.Constants;

 


@Controller
@RequestMapping("/auth/login.do")
public class LoginController {
	@Autowired
	private UserService userService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private ShortcutService shortcutService;
	
	/**
	 * 把公钥和随机数传给login.jsp
	 * @param request
	 */
	@RequestMapping(params = "checkKey")
	@ResponseBody
	public AjaxJson checkKey(HttpServletRequest request){
			AjaxJson j = new AjaxJson();
			RSAPublicKey publicKey = RSAUtils.getDefaultPublicKey();//拿到公钥,公钥有两个参数modulus和exponent
		    String modulus =  new String(Hex.encodeHex(publicKey.getModulus().toByteArray()));
		    String exponent= new String(Hex.encodeHex(publicKey.getPublicExponent().toByteArray()));
		    Map<String, Object> map = new HashMap<String, Object>();
		    map.put("modulus", modulus);
		    map.put("exponent", exponent);
		    map.put("time", new Date().getTime());
		    j.setSuccess(true);
		    j.setAttributes(map);
		    return j;
		    
	}
	
	
	@RequestMapping(params = "checkuser")
	@ResponseBody
	public AjaxJson checkuser(String longkey,  HttpServletRequest req) {
		AjaxJson j  =new AjaxJson();
		String code = (String) ContextHolderUtils.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		try {
			if(!DataUtils.isStrEmpty(longkey)){
				String hruser = RSAUtils.decryptStringByJs(longkey) ;
				String[] longkeyArray = hruser.split(",");
			/*	if(code.equalsIgnoreCase(longkeyArray[0])){//验证码
*/			if(true){//验证码	
				long nowTime =new Date().getTime();
					long prevTime =Long.parseLong(longkeyArray[1]);
					long time =nowTime-prevTime;
				//	if(time>60000){//时间超过一秒就不给通过
						AuthUser user = new AuthUser();
						user.setAccount(java.net.URLDecoder.decode(longkeyArray[2] ,"UTF-8"));
						user.setPassword(java.net.URLDecoder.decode(longkeyArray[3],"UTF-8"));
						AuthUser sysuser = userService.findUser(user);
						if(sysuser!=null){
							sysuser.setLoginIp(ContextHolderUtils.getIpAddr());
							userService.update(sysuser);
							ContextHolderUtils.getSession().setMaxInactiveInterval(60 * 60);
							ContextHolderUtils.getSession().setAttribute(Globals.USER_SESSION, sysuser);
							
							List<AuthModule> menu =new ArrayList<AuthModule>();
							List<AuthModule> menu1 =new ArrayList<AuthModule>();
							List<AuthModule> menu2 =new ArrayList<AuthModule>();
							if(sysuser.getSysRole().getIsAdmin()==1){
								List<AuthModule> modules= this.moduleService.find(0,1);
								if(!modules.isEmpty()){
									for(AuthModule p:modules){
										if(p.getFloor()==1){
											menu.add(p);
										}
										if(p.getFloor()==2){
											menu1.add(p);
										}
										if(p.getFloor()==3){
											menu2.add(p);
										}
									}
								}
							}else{
								List<AuthPermisstion> permission =this.permissionService.getleftMenu(sysuser.getSysRole().getId());
								if(!permission.isEmpty()){
									for(AuthPermisstion p:permission){
										if(p.getModule().getFloor()==1){
											menu.add(p.getModule());
										}
										if(p.getModule().getFloor()==2){
											menu1.add(p.getModule());
										}
										if(p.getModule().getFloor()==3){
											menu2.add(p.getModule());
										}
									}
							}
							
							}
							ContextHolderUtils.getSession().setAttribute(Globals.LEFT_MENU1_SESSION, menu);
							ContextHolderUtils.getSession().setAttribute(Globals.LEFT_MENU2_SESSION, menu1);
							ContextHolderUtils.getSession().setAttribute(Globals.LEFT_MENU3_SESSION, menu2);
							j.setMsg("操作成功！");
							j.setObj(1);
							j.setSuccess(true);
						}else{
							j.setMsg("帐号或密码输入错误！");
							j.setObj(0);
						}
				}else{
					j.setMsg("验证码输入错误！");
					j.setObj(-2);
				}
				
			}
		} catch (Exception e) {
			j.setMsg("服务器异常，请联系管理员！");
			e.printStackTrace();
			j.setObj(0);
		}
		 ContextHolderUtils.getSession().removeAttribute(Constants.KAPTCHA_SESSION_KEY);
		return j;
	}
	@RequestMapping(params="login")
	public String  login(HttpServletRequest request){
		String url="login";
		AuthUser user =ContextHolderUtils.getUserSession();
		if(user!=null){
			url = "redirect:/admin";
		}
		return url;
	}
	@RequestMapping(params="home")
	public ModelAndView  home(HttpServletRequest request){
		AuthUser user =ContextHolderUtils.getUserSession();
		request.setAttribute("user", user);
		List<AuthShortcut>  shortcuts = new ArrayList<AuthShortcut>();
		if(!"1".equals(user.getSysRole().getId())){
			List<AuthRoleShortcut> authRoleShortcuts =this.shortcutService.getshortcutByrole(user.getSysRole().getId());
			for(AuthRoleShortcut a:authRoleShortcuts){
				shortcuts.add(a.getShortcut());
			}
		}else{
			//shortcuts= this.shortcutDao.find(" from AuthShortcut a where a.isenable=? order by a.orderby asc", 1); 
			shortcuts= this.shortcutService.find(1); 
		}
		
		request.setAttribute("shortcuts", shortcuts);
		return new ModelAndView("home");
	}
	
	@RequestMapping(params="left")
	public ModelAndView left(HttpServletRequest request){
		
		request.setAttribute("menu",  ContextHolderUtils.getSession().getAttribute(Globals.LEFT_MENU1_SESSION));
		request.setAttribute("menu1", ContextHolderUtils.getSession().getAttribute(Globals.LEFT_MENU2_SESSION));
		request.setAttribute("menu2", ContextHolderUtils.getSession().getAttribute(Globals.LEFT_MENU3_SESSION));
		
		return new ModelAndView("left");
	}
	
	@RequestMapping(params="top")
	public ModelAndView top(HttpServletRequest request){
		int size = 0;
		request.setAttribute("menu",  ContextHolderUtils.getSession().getAttribute(Globals.LEFT_MENU1_SESSION));
		request.setAttribute("user", ContextHolderUtils.getUserSession());
		request.setAttribute("size", size);
		return new ModelAndView("top");
	}
	
	/**
	 * 退出登录
	 * @return
	 */
	@RequestMapping(params="loginout")
	public ModelAndView  loginout(HttpServletRequest request){
		AuthUser user =ContextHolderUtils.getUserSession();
		if(user!=null){
			ContextHolderUtils.getSession().removeAttribute(Globals.USER_SESSION);
			ContextHolderUtils.getSession().removeAttribute(Globals.LEFT_MENU1_SESSION);
			ContextHolderUtils.getSession().removeAttribute(Globals.LEFT_MENU2_SESSION);
			ContextHolderUtils.getSession().removeAttribute(Globals.LEFT_MENU3_SESSION);
		}
		return new ModelAndView("login");
	}
 	
}
