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
    <div class="formtitle"><span>添加学校信息</span></div>
    <t:formvalid formid="theclass" action="${basePath }/admin/school/addorupd" beforeSubmit="validateForm()" callback="callback(responseText);" btnsub="sb" >
    <input name="id"  id="id" type="hidden"  value="${obj.id }" />
    <ul class="forminfo">
    <li><label>学校名称</label><input name="name" id="name" maxlength="9" type="text" class="dfinput" value="${obj.name }" ajaxurl="${basePath }/admin/school/isExist&oldervalue=${obj.name}" datatype="s1-10"    /></li>
   	<li><label>校长/馆长</label><input name="head_master" id="head_master" maxlength="9" type="text" class="dfinput" value="${obj.head_master }" ajaxurl="${basePath }/admin/school/isExist&oldervalue=${obj.head_master}" datatype="s1-10"    /></li>
    <li><label>联系手机号码</label><input name="m_phone" id="m_phone" maxlength="9" type="text" class="dfinput" value="${obj.m_phone }" ajaxurl="${basePath }/admin/school/isExist&oldervalue=${obj.m_phone}" datatype="s1-10"    /></li>		
    <li><label>简介</label><input name="remark" id="remark" type="text" class="dfinput" value="${obj.remark }"  datatype="*0-50"  /><i></i></li>
     <li><label>是否启用</label>
      <cite><input name="is_enable"    datatype="*" nullmsg="请选择是否启用！" type="radio" value="1"  <c:if test="${obj.is_enable==1}">checked="checked"</c:if>  />是&nbsp;&nbsp;&nbsp;&nbsp;<input name="isenable" type="radio" value="0"  <c:if test="${obj.isenable==0}">checked="checked"</c:if>  />否</cite></li>
    <li><label>&nbsp;</label><input name="" type="button"  id="sb" class="btn" value="确认${not empty obj.id ?'更新':'保存' }"/></li>
    </ul>
    </t:formvalid>
 </div>
 </body>
 </html>   