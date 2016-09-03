<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@include file="../common/mytags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加或者修改</title>
<t:base type="jquery,ajaxform,layer,tools,valiform,select"></t:base>
 
</head>
<body>
    <div class="formbody">
    <div class="formtitle"><span>添加用户信息</span></div>
    <t:formvalid formid="AuthUser" action="${basePath }auth/user.do?addorupd"  callback="callback(responseText);" btnsub="sb" >
    <input name="id" type="hidden"  value="${obj.id }" />
    <ul class="forminfo">
    <li><label>角色</label>
     	<t:select name="sysRole.id" id="roleid"  title="角色" option="${role }" value="${obj.sysRole.id }" isValidate="1"  ></t:select>
    </li>
    <li><label>用户姓名</label><input name="name" type="text" class="dfinput" value="${obj.name }" datatype="s1-10"    ajaxurl="${basePath }auth/user.do?isExist&id=${obj.id}"/><i></i></li>
    <li><label>帐号</label><input name="account" type="text" class="dfinput" value="${obj.account }" datatype="s1-15" ajaxurl="${basePath }auth/user.do?isExist&id=${obj.id}" /><i></i></li>
    <c:if test="${empty obj.id }">
    	<li><label>密码</label><input name="password" type="text" class="dfinput" value="${empty obj.account?'123456':obj.password }"/><i></i></li>
    </c:if>
   
    <li><label>是否启用</label>
      <cite><input name="isenable" type="radio" value="1"   datatype="*" nullmsg="请选择是否启用！" <c:if test="${obj.isenable==1}">checked="checked"</c:if>  />是&nbsp;&nbsp;&nbsp;&nbsp;<input name="isenable" type="radio" value="0"  <c:if test="${obj.isenable==0}">checked="checked"</c:if>  />否</cite></li>
    <li><label>&nbsp;</label><input name="" type="button"  id="sb" class="btn" value="确认${not empty obj.id ?'更新':'保存' }"/></li>
    </ul>
    </t:formvalid>
 </div>
 </body>
 </html>   