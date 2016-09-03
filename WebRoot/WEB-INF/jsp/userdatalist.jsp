<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/mytags.jsp" %>
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
    <li><a href="#">修改用户资料</a></li>
    </ul>
    </div>
    <t:datagrid  name="userdatagrid" actionUrl="user.do?datagrid" pagination="true" queryMode="group"  checkbox="true">
    		<t:dgCol title="操作" field="opt" width="50" align="center"></t:dgCol>
    	 	<t:dgCol title="" field="id" hidden="true" align="center"></t:dgCol>
    		<t:dgCol title="用户姓名" field="name" width="100"  query="true" align="center"></t:dgCol>
    		<t:dgCol title="职位" field="position" width="100"  query="true" align="center"></t:dgCol>
    		<t:dgCol title="QQ帐号" field="qqAccount" width="100" query="true" align="center"></t:dgCol>
    		<t:dgCol title="手机号码" field="mobilePhone" width="100" align="center"></t:dgCol>
    		<t:dgCol title="短号" field="shortPhone" width="100" query="true" align="center"></t:dgCol>
    		<t:dgCol title="邮箱" field="email" width="100" query="true" align="center"></t:dgCol>
    		<t:dgCol title="是否毕业" field="isGraduation" replace="1_是,0_否" width="100" align="center"></t:dgCol>
    		<t:dgFunOpt funname="openWinMax('user.do?userdataaddorupd&id={id}','修改{name}')" title="修改"></t:dgFunOpt>
    </t:datagrid>
    
 
</body>

</html>
