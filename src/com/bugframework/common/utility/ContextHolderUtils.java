package com.bugframework.common.utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bugframework.auth.pojo.AuthUser;

/**
 * 
 * @author 许增飞
 *
 */
public class ContextHolderUtils
{
/**
 * 获得HttpServletRequest	
 * @return
 */
  public static HttpServletRequest getRequest()
  {
    HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    return request;
  }
/** 
 * 获得 HttpSession
 * @return
 */
  public static HttpSession getSession()
  {
    HttpSession session = getRequest().getSession();
    return session;
  }
  /**
   * 获得session中的系统用户
   * @return SysUser
   */
  public static AuthUser getUserSession(){
	  
	  HttpSession session = ContextHolderUtils.getSession();
	  if(session!=null){
		  AuthUser user = (AuthUser)session.getAttribute(Globals.USER_SESSION);
		  return user;
	  }else{
		  return null;
	  }
  }
  /**
   * 获得IP
   * @return
   */
  public static String getIpAddr() {  
	    String ip = getRequest().getHeader("x-forwarded-for");  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = getRequest().getHeader("Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = getRequest().getHeader("WL-Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = getRequest().getRemoteAddr();  
	    }  
	    return ip;  
	}  
}