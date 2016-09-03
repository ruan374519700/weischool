package com.bugframework.common.utility;

import javax.servlet.http.HttpSession;

import com.bugframework.common.pojo.SessionInfo;


public class Globals {
	/**
	 * 用户Session 的名称全局
	 */
	public final static String USER_SESSION="sysuser";
	public final static String LEFT_MENU1_SESSION="left_menu1";
	public final static String LEFT_MENU2_SESSION="left_menu2";
	public final static String LEFT_MENU3_SESSION="left_menu3";
	public final static String ADD_SUCCESS="添加成功！";
	public final static String UPD_SUCCESS="更新成功！";
	public final static String DEL_SUCCESS="删除成功！";
	public final static String ENABLE_SUCCESS="启用成功！";
	public final static String DISABLE_SUCCESS="禁用成功！";
	 
	/**
	 * 获得用户Session
	 * @param session
	 * @return
	 */
	public static SessionInfo getUserSession(HttpSession session){
		SessionInfo sessionInfo = (SessionInfo)session.getAttribute(Globals.USER_SESSION);
		return sessionInfo;
	}
}
