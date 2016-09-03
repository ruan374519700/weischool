package com.bugframework.common.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.HandlerInterceptor;

import com.bugframework.auth.pojo.AuthUser;
import com.bugframework.common.utility.ContextHolderUtils;
import com.bugframework.common.utility.Globals;
import com.bugframework.common.utility.ResourceUtil;


public class Interceptor implements HandlerInterceptor{
	private List<String> excludeUrls;

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}
	 @Override
	 public void afterCompletion(HttpServletRequest request,
	 		HttpServletResponse response, Object handler, Exception ex)
	 		throws Exception {
	 }
	  
	  @Override
	 	public void postHandle(HttpServletRequest request,
	 			HttpServletResponse response, Object handler,
	 			ModelAndView modelAndView) throws Exception {
	 	}
	  @Override
	  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
	 		String requestUri = request.getRequestURI();//  /包名/请求路径
	 		String contextPath = request.getContextPath();//  /包名
	 		String url = requestUri.substring(contextPath.length());  //    /请求路径
	 		String requestPath = ResourceUtil.getRequestPath(request);// 用户访问的资源地址     请求路径？参数
	 		HttpSession session = ContextHolderUtils.getSession();       
	 		AuthUser sessioninfo = (AuthUser) session.getAttribute(Globals.USER_SESSION);
	 		if (excludeUrls.contains(requestPath)||excludeUrls.contains(url+"?*")) {
	 			return true;
	 		} 
	 		else {
	 			if (sessioninfo!=null){
	 				return true;
	 			} else {
	 				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextPath+"/";
	 				 response.sendRedirect(basePath+"auth/login.do?login");
	 				return false;
	 			}

	 		}

	 	} 

}
