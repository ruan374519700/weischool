<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@include file="../common/mytags.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>模块管理主页面</title>
<t:base type="jquery,wdate,layer,tools"></t:base>
   <frameset rows="40,*" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="<%=basePath %>auth/module.do?top" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" title="topFrame" />
  <frameset cols="*" frameborder="no" border="0" framespacing="0">
    <frame src="<%=basePath %>auth/module.do?tab" frameborder="no"   name="rightFrame" id="rightFrame" title="rightFrame" />
  </frameset>
</frameset>
</head>
<noframes>
<body>
</body>
</noframes>
</html>
