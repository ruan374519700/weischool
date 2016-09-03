<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@include file="common/mytags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<t:base type="jquery,layer,tools"></t:base>
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected");
		$(this).addClass("selected");
	});
});

function logout (){
	var out = window.confirm("确定退出?");
	if (out){
		window.top.location.href='<%=basePath %>auth/login.do?loginout';
	}else {
	}
}
</script>
<style type="text/css">
	#logout:hover {
		cursor:pointer;
	}
</style>

</head>

<body style="background:url(${basePath }rs/images/topbg.gif) repeat-x;">

    <div class="topleft">
    <a href="main.html" target="_parent"><img src="${basePath }rs/images/logo.png" title="系统首页" /></a>
    </div>
        
    <ul class="nav">
    <li><a href="<%=basePath %>auth/login.do?home" target="rightFrame" class="selected"><img src="${basePath }rs/images/ico01.png" style="width: 45px;height: 45px" title="主页" /><h2>主页</h2></a></li>
    <c:forEach var="menu" items="${menu }">
    	<li><a href="${basePath }${menu.url}"  target="rightFrame"><img src="${basePath }rs/images/${menu.icon}" title="${menu.name }"  style="width: 45px;height: 45px"/><h2>${menu.name }</h2></a></li>
    </c:forEach>
    </ul>
            
    <div class="topright">    
    <ul>
    <li><a href="${basePath}/auth/user.do?tochangepsw&id=${user.id }" target="rightFrame" >修改密码</a></li>
    <li><a href="#">关于</a></li>
    <li><a id="logout" onclick="logout()" target="_parent">退出</a></li>
    </ul>
     
    <div class="user">
    <span>${user.name }</span>
    <i>公告</i>
    <a href="${basePath }admin/noticedata.do?unread" target="rightFrame">${size }</a>
    </div>    
    
    </div>

</body>
</html>