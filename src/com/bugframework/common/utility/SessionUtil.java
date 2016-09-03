package com.bugframework.common.utility;

import javax.servlet.http.HttpSession;

import com.bugframework.auth.pojo.AuthUser;

/**
 * session 工具
 * @author LZB
 *
 */
public class SessionUtil {
	
	/**
	 * 获取用户session 
	 * @param session
	 * @return
	 */
	public static AuthUser getCurUser(HttpSession session){
		AuthUser user =(AuthUser)session.getAttribute(Globals.USER_SESSION);
		return user;
	}
}
