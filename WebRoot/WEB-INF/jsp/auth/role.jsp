<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@include file="../common/mytags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加或者修改</title>
<t:base type="jquery,ajaxform,layer,tools,valiform"></t:base>
<script type="text/javascript">

function validateForm(){
	return true;
}
</script>
</head>

<body>
    
    <div class="formbody">
    <div class="formtitle"><span>添加角色信息</span></div>
    <t:formvalid formid="theclass" action="${basePath }auth/role.do?addorupd" beforeSubmit="validateForm()" callback="callback(responseText);" btnsub="sb" >
    <input name="id"  id="id" type="hidden"  value="${obj.id }" />
    <ul class="forminfo">
    <li><label>角色名称</label><input name="name" id="name" maxlength="9" type="text" class="dfinput" value="${obj.name }" ajaxurl="${basePath }auth/role.do?isExist&oldervalue=${obj.name}" datatype="s1-10"    /></li>
    <li><label>描述</label><input name="description" id="description" type="text" class="dfinput" value="${obj.description }"  datatype="*0-50"  /><i></i></li>
     <li><label>是否启用</label>
      <cite><input name="isenable"    datatype="*" nullmsg="请选择是否启用！" type="radio" value="1"  <c:if test="${obj.isenable==1}">checked="checked"</c:if>  />是&nbsp;&nbsp;&nbsp;&nbsp;<input name="isenable" type="radio" value="0"  <c:if test="${obj.isenable==0}">checked="checked"</c:if>  />否</cite></li>
    <li><label>&nbsp;</label><input name="" type="button"  id="sb" class="btn" value="确认${not empty obj.id ?'更新':'保存' }"/></li>
    </ul>
    </t:formvalid>
 </div>
 </body>
 </html>   