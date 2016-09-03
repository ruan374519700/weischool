<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@include file="../common/mytags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加或者修改</title>
<t:base type="jquery,ajaxform,layer,tools,valiform"></t:base>
 
</head>

<body>
    
    <div class="formbody">
    <div class="formtitle"><span>添加模块信息</span></div>
    <t:formvalid formid="theclass" action="${basePath }auth/module.do?addorupd"  callback="callback(responseText);" btnsub="sb" >
    <input name="pid"  id="pid" type="hidden"  value="${pid }" />
    <input name="id"  id="id" type="hidden"  value="${obj.id }" />
    <ul class="forminfo">
    <li><label>模块名称</label><input name="name" id="name" maxlength="9" type="text" class="dfinput" value="${obj.name }" ajaxurl="${basePath }auth/module.do?isExist&id=${obj.id}&pid=${pid}" datatype="s1-10"    /></li>
    <li><label>地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址</label><input name="url" id="url"  type="text" class="dfinput" value="${obj.url }"   datatype="*0-100"    /></li>
    <c:if test="${pid eq '0'}">
    <li><label>图&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;标</label><input name="icon" id="icon"  type="text" class="dfinput" value="${obj.icon }"   datatype="*0-100"    /></li>
    </c:if>
    <li><label>排&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;序</label><input name="orderby" maxlength="9" type="text" class="dfinput" value="${obj.orderby }" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" /><i>排序只能输入0-9位的数字</i></li>
    <li><label>是否启用</label>
      <cite><input name="isenable"    datatype="*" nullmsg="请选择是否启用！" type="radio" value="1"  <c:if test="${obj.isenable==1}">checked="checked"</c:if>  />是&nbsp;&nbsp;&nbsp;&nbsp;<input name="isenable" type="radio" value="0"  <c:if test="${obj.isenable==0}">checked="checked"</c:if>  />否</cite></li>
    <li><label>&nbsp;</label><input name="" type="button"  id="sb" class="btn" value="确认${not empty obj.id ?'更新':'保存' }"/></li>
    </ul>
    </t:formvalid>
 </div>
 </body>
 </html>   