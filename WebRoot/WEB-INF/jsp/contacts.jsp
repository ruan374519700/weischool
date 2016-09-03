<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@include file="common/mytags.jsp" %>
  <%
 StringBuffer uploadUrl = new StringBuffer("http://");
 uploadUrl.append(request.getHeader("Host"));
 uploadUrl.append(request.getContextPath());
 uploadUrl.append("/FileUploadController");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加或者修改</title>
<t:base type="jquery,ajaxform,layer,tools,valiform,select,imgupl,swfu"></t:base>
<script type="text/javascript">

function validateForm(){
	return true;
}

$(document).ready(function(e) {
    $(".select").uedSelect({
		width : 167			  
	});
});


</script>
</head>

<body>
 
    
    <div class="formbody">
     
    <div class="formtitle"><span>查看通讯录</span></div>
    <input name="id" type="hidden"  value="${obj.id }" />
    <ul class="forminfo">
    <li><label>头像</label><img id="imgDiv" src="${basePath}${obj.imageSrc }" width="200" height="200" /></li>
    <li><label>用户姓名</label><input name="name" type="text" class="dfinput" value="${obj.name }" readonly="readonly"/><i></i></li>
    <li><label>QQ账号</label><input name="qqAccount" type="text" class="dfinput" value="${obj.qqAccount }" readonly="readonly"/><i></i></li>
    <li><label>手机号码</label><input name="mobilePhone" type="text" class="dfinput" value="${obj.mobilePhone }" readonly="readonly"/><i></i></li>
    <li><label>短号</label><input name="shortPhone" type="text" class="dfinput" value="${obj.shortPhone }" readonly="readonly"/><i></i></li>
    <li><label>邮箱</label><input name="email" type="text" class="dfinput" value="${obj.email }" readonly="readonly"/><i></i></li>
    <li><label>职位</label><input name="position" type="text" class="dfinput" value="${obj.position }" readonly="readonly"/><i></i></li>
    <li><label>班级或单位</label><input name="unit" type="text" class="dfinput" value="${obj.unit }"  readonly="readonly"/><i></i></li>
    <li><label>宿舍或住处</label><input name="hostel" type="text" class="dfinput" value="${obj.hostel }" readonly="readonly"/><i></i></li>
    <li><label>是否毕业</label>
      <cite><input name="isGraduation" type="radio" value="1"  <c:if test="${obj.isGraduation==1}">checked="checked"</c:if> disabled="disabled"/>是&nbsp;&nbsp;&nbsp;&nbsp;
      <input name="isGraduation" type="radio" value="0" <c:if test="${obj.isGraduation==0}">checked="checked"</c:if> disabled="disabled"/>否</cite></li>
    </ul>
 </div>
 </body>
 </html>   