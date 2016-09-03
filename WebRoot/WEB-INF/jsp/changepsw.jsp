<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@include file="common/mytags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<t:base type="jquery,valiform,ajaxform,layer,tools"></t:base>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />    
<title>修改密码</title>
<script type="text/javascript">
function validateForm(){
	layer.msg('提前校验');
	return true;
}
function result(data){
	layer.msg(data.msg);
}
</script>
</head>
  
  <body>
  	<div class="formbody">
    <div class="formtitle"><span>修改资料</span></div>
    <t:formvalid formid="changepsw" action="${basePath }auth/user.do?changepsw" beforeSubmit="validateForm()" callback="result(responseText);" btnsub="sb" >
    <input name="id" type="hidden"  value="${user.id }" />
    <ul class="forminfo">
   	<li><label>密码</label><input class="dfinput" type="text" name="password" datatype="/^\w{6,10}$/i" nullmsg="密码不可为空！" errormsg="密码只能输入字母(不区分大小写)或数字,长度为6到10位。"/><i>密码只能输入长度为6到10位的字母(不区分大小写)或数字</i></li>
	<li><label>确认密码</label><input class="dfinput" type="text" name="repassword" datatype="/\w{6,10}/i" recheck="password" nullmsg="确认密码不可为空！" errormsg="您两次输入的密码不一致!"/></li>
    <li><label>&nbsp;</label><input name="" type="button"  id="sb" class="btn" value="确认${not empty user.id ?'更新':'保存' }"/></li>
    </ul>
    </t:formvalid>
 </div>
	
  </body>
</html>
