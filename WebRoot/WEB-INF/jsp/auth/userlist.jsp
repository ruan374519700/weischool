<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common/mytags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户管理</title>
<t:base type="jquery,wdate,layer,tools"></t:base>
 
</head>


<body>
 
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">系统管理</a></li>
    <li><a href="#">用户管理</a></li>
    </ul>
    </div>
    <t:datagrid  name="userdatagrid" actionUrl="user.do?datagrid" pagination="true" queryMode="group"  checkbox="true">
    		<t:dgCol title="操作" field="opt" width="250"></t:dgCol>
    	 	<t:dgCol title="" field="id" hidden="true" ></t:dgCol>
    		<t:dgCol title="用户姓名" field="name"  width="100"  query="true"></t:dgCol>
    		<t:dgCol title="用户帐号" field="account"   width="100" query="true"></t:dgCol>
    		<!-- replace="产品管理员_产品管理员,渠道管理员_渠道管理员,销售员_销售员,财务_财务,助理_助理" -->
    		<t:dgCol title="用户类型" field="typeName"  query="true" ></t:dgCol>
    		<t:dgCol title="角色名称" field="roleName" width="100" ></t:dgCol>
    		<t:dgCol title="状态" field="isenable" replace="1_启用中,0_禁用中" query="true" align="center" width="100"></t:dgCol>
    		<t:dgCol title="创建时间" field="createTime" formatter="yyyy-MM-dd HH:mm:ss" align="center" width="200" ></t:dgCol>
    		<t:dgFunOpt funname="openWinMax('user.do?useraddorupd&id={id}','更新{name}')" title="更新"></t:dgFunOpt>
    		<t:dgConfirmOpt url="user.do?isenabled&id={id}&isenable=0" title="禁用" exp="isenable#eq#1"  message="是否禁用？"></t:dgConfirmOpt>
    		<t:dgConfirmOpt url="user.do?isenabled&id={id}&isenable=1" title="启用" exp="isenable#eq#0"  message="是否启用？"></t:dgConfirmOpt>
    		<t:dgConfirmOpt url="user.do?del&id={id}" title="删除" message="是否删除？"></t:dgConfirmOpt>
    		<t:dgConfirmOpt url="user.do?changepsw&id={id}&password=123456" title="重置密码" message="是否重置密码为【123456】？"></t:dgConfirmOpt>
    		<t:dgToolBar title="添加" icon="add" funname="openWinMax('user.do?useraddorupd','添加')"></t:dgToolBar>
    		<t:dgToolBar title="删除" icon="del" funname="bathDel('user.do?del','userdatagrid','确定批量删除？')"></t:dgToolBar>
    </t:datagrid>
    
 
</body>

</html>
