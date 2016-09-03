<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@include file="common/mytags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="${basePath }rs/"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script src="${basePath }rs/js/jquery-1.8.3.js" type="text/javascript"></script>

<script language="javascript">
	function timeCount () {
		var date = new Date().toLocaleDateString();
		var time = new Date().toLocaleTimeString();
		
		var datetime = date+" "+time;
		
		$("#time").text(datetime);
		setTimeout("timeCount()",1000);
	}
	$(function (){
		timeCount();
	});
</script>

</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    </ul>
    </div>
    
    <div class="mainindex">
    
    
    <div class="welinfo">
    <span><img src="images/sun.png" /></span>
    <b>${user.name }你好，欢迎使用信息管理系统</b>
   <%--  <a href="${basePath }auth/user.do?userdataaddorupd">修改资料</a> --%>
    </div>
    
    <div class="welinfo">
    <span><img src="${basePath }rs/images/time.png" alt="时间" /></span>
    <i><span id="time"></span></i>
    </div>
    
    <div class="xline"></div>
    <c:if test="${not empty shortcuts }">
    <ul class="iconlist">
    <c:forEach var="r" items="${shortcuts }">
    	<li onclick='openPage("${basePath }${r.url}")' style="cursor: pointer;" ><img src="${basePath }rs/images/${r.icon}" /><p><a href="${basePath }${r.url}">${ r.name}</a></p></li> 
    </c:forEach>
  </ul>
    <script >
    		function openPage(url){
    			window.location.href=url;
    		}
    </script>
    <div class="xline"></div>
   </c:if> 
    <div class="ntcinfo">
    <span><img src="${basePath }rs/images/icon14.png"   /></span>
    <b>最新公告</b>
    <ul>
    	<c:forEach var="ntcdata" items="${ntcdatas }">
		
		<li><a href="${basePath }admin/noticedata.do?check&id=${ntcdata.id }" target="rightFrame" <c:if test="${ntcdata.isRead eq 0 }">style="font-weight: bold;" </c:if> title="${ntcdata.title }">${ntcdata.title }</a>
		<span style="float: right;"><fmt:formatDate value="${ntcdata.createTime }" pattern="yyyy-MM-dd hh:mm"/></span></li>
		
		</c:forEach>
	</ul>
    </div>
    
    </div>

</body>

</html>
