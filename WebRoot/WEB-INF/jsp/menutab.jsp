<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@include file="common/mytags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加或者修改</title>
<script type="text/javascript" src="<%=basePath %>rs/js/jquery-1.8.3.js"></script>
<link rel="stylesheet" href="<%=basePath %>rs/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
<script type="text/javascript" src="<%=basePath %>rs/js/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<style>
	body {
	background-color: white;
	margin:0; padding:0;
	text-align: center;
	}
	div, p, table, th, td {
		list-style:none;
		margin:0; padding:0;
		color:#333; font-size:12px;
		font-family:dotum, Verdana, Arial, Helvetica, AppleGothic, sans-serif;
	}
	#testIframe {margin-left: 10px;}
	 
</style>
<script type="text/javascript">
$(function(){
	$("#testIframe").css("height",$(document).height()-10);
})
var zTree;
var demoIframe;

var setting = {
	 
	view: {
		dblClickExpand: false,
		showLine: true,
		selectedMulti: false
	},
	data: {
		simpleData: {
			enable:true,
			idKey: "id",
			pIdKey: "pId",
			rootPId: ""
		}
	},
	callback: {
		beforeClick: function(treeId, treeNode) {
				demoIframe.attr("src","<%=basePath%>admin/menu.do?menulist&pid="+treeNode.id);
				return true;
		}
	}
};

$(document).ready(function(){
	loadTree();
	demoIframe = $("#testIframe");
	var zTree = $.fn.zTree.getZTreeObj("tree");
	zTree.selectNode(zTree.getNodeByParam("id", 1));
});

function loadTree(){
	$.ajax({
        type:"POST",
        url:'<%=basePath%>admin/menu.do?getTree',
        datatype: "json", 
        async:false,
        success:function(data){
        	var t = $("#tree");
        	t = $.fn.zTree.init(t, setting, data);
        },
        //调用出错执行的函数
        error: function(){
        	alert("网络异常");
        }         
     });
}

function loadReady() {
	var bodyH = demoIframe.contents().find("body").get(0).scrollHeight,
	htmlH = demoIframe.contents().find("html").get(0).scrollHeight,
	maxH = Math.max(bodyH, htmlH),
	minH = Math.min(bodyH, htmlH),
	h = demoIframe.height() >= maxH ? minH:maxH ;
	if (h < 530) h = 530;
	demoIframe.height(h);
}
 
</script>
</head>

<body>
    <table border=0 style="width:99%"  align=left>
	<tr>
		<td width=200px align=left valign=top style="BORDER-RIGHT: #999999 1px dashed">
			<ul id="tree" class="ztree" style="width:200px; overflow:auto;"></ul>
		</td>
		<td align=left valign=top><iframe ID="testIframe" Name="testIframe" FRAMEBORDER=0    width="100%"   SRC="<%=basePath%>admin/menu.do?menulist&pid=0"></iframe></td>
	</tr>
</table>
 </body>
 </html>   