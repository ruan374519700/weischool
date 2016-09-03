<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@include file="../common/mytags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加或者修改</title>
<script type="text/javascript" src="<%=basePath %>rs/js/jquery-1.8.3.js"></script>
<link rel="stylesheet" href="<%=basePath %>rs/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
<script type="text/javascript" src="<%=basePath %>rs/js/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="<%=basePath %>rs/js/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
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
 
var zTree;
var  msg=""; 

var setting = {
		check: {
			enable: false
		},
	view: {
		dblClickExpand: true,
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
			 $.ajax({
			        type:"POST",
			        url:'<%=basePath%>auth/module.do?getmodule&id='+treeNode.id,
			        datatype: "json", 
			        async:false,
			        success:function(data){
			        	if(data.success){
			        	 
			        		msg = data.obj.name+"[-]"+data.obj.icon+"[-]"+data.obj.url; 
			        	}
			        },
			        //调用出错执行的函数
			        error: function(){
			        	msg="网络异常";
			        }         
			     });
				return true;
		}
	}
};
setting.check.chkboxType = { "Y" : "ps", "N" : "ps" };

$(document).ready(function(){
	loadTree();
	 
	var zTree = $.fn.zTree.getZTreeObj("tree");
	zTree.selectNode(zTree.getNodeByParam("id", 1));
});

function loadTree(){
	$.ajax({
        type:"POST",
        url:'<%=basePath%>auth/module.do?getTree',
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

function getTree(){
    return msg;
} 


</script>
</head>

<body>
    <table border=0  align=left>
	<tr>
		<td  align=left valign=top  >
			<ul id="tree" class="ztree" style="width:100%; overflow:auto;"></ul>
		</td>
	</tr>
</table>
 </body>
 </html>   