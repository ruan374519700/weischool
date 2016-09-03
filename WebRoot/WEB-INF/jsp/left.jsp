<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@include file="common/mytags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${basePath }rs/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${basePath }rs/js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换 许增飞修改后的导航切换
	$(".leftli").children("a").click(function(){//二级菜单点击事件
		$(".leftli").removeClass("active");//去掉二级菜单的高亮
		$(".menuson_leaf li.active").removeClass("active");//去掉三级菜单的高亮
		if($(this).parent().children("i").children("ul").length==0){//判断是否存在子菜单
	 	   $(this).parent().addClass("active");//当前菜单不存在子菜单，当前菜单高亮
		}else{//以下是不存在子菜单的情况
		  if($(this).parent().children("i").children("ul").css("display")=="block"){//三级菜单是否打开情况
			//	$(this).parent().children("cite").css("background","url((${basePath }admin/images/list.gif) no-repeat"); //上一级的菜单按钮改变成关闭状态按钮
				$(this).parent().children("cite").attr("style","background:url((${basePath }rs/images/list.gif) no-repeat"); //上一级的菜单按钮改变成关闭状态按钮
				$(this).parent().children("i").children("ul").css("display","none");//三级菜单隐藏掉，也既是关闭掉
			}else{
			//	$(this).parent().children("cite").css("background","url(${basePath }admin/images/clist.png) no-repeat;");//上一级的菜单按钮改变成打开状态按钮
				$(this).parent().children("cite").attr("style","background:url(${basePath }rs/images/clist.png) no-repeat;");//上一级的菜单按钮改变成打开状态按钮
				$(this).parent().children("i").children("ul").css("display","block");//三级菜单显示出来，也既是打开
			}	
		}
	});
	$(".menuson_leaf li").click(function(){//三级级菜单点击事件
 		$(".leftli").removeClass("active");//去掉二级菜单的高亮
		$(".menuson_leaf li.active").removeClass("active");//去掉三级菜单的高亮
		 $(this).addClass("active");//当前的三级高亮
		 
	});

	$('.title').click(function(){//一级级菜单点击事件
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
})	
</script>

<style>
.menuson_leaf{display:none}
.menuson_leaf li cite{display:block; float:left; margin-left:40px; background:url(${basePath }rs/images/list.gif) no-repeat; width:16px; height:16px; margin-top:7px;}
</style>
</head>

<body style="background:#f0f9fd;">
	<div class="lefttop"><span></span>导航菜单</div>
    
    <dl class="leftmenu">
    
    <c:forEach var="m" items="${menu }">
     <c:if test="${m.floor eq 1 }">
	    <dd><div class="title"><span><img src="${basePath }rs/images/${m.icon }"  style="width:16px;height: 16px"/></span>${m.name }</div>
	    <ul class="menuson">
	        <c:forEach var="m1" items="${menu1 }">
	        <c:if test="${m1.pid eq m.id }">
		        <li class="leftli"><cite></cite><a   <c:choose><c:when test="${not empty m1.url }">href="<%=basePath%>${m1.url }" target="rightFrame"</c:when><c:otherwise>href="#"</c:otherwise></c:choose> >${m1.name }</a><i>
		        	  <c:forEach var="m2" items="${menu2 }">
		        	  	<c:if test="${m2.pid eq m1.id }">
				        	 <ul class="menuson_leaf">
								<li><cite></cite><a  <c:choose><c:when test="${not empty m2.url }">href="<%=basePath%>${m2.url }" target="rightFrame"</c:when><c:otherwise>href="#"</c:otherwise></c:choose>  >${m2.name }</a><i></i></li>
					         </ul>
				         </c:if>
			         </c:forEach>
		        </i></li>
	        </c:if>
	        </c:forEach>
	    </ul>    
	    </dd>  
    </c:if>
  </c:forEach>  
  
      
    
    </dl>
    
</body>
</html>
