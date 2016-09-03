<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@include file="../common/mytags.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>列表页面</title>
<t:base type="jquery,wdate,layer,tools"></t:base>
 <script type="text/javascript">
 function openPermissionWin(url,title,width,height){
		  var layerindex =  layer.open({
			    type: 2,
			    scrollbar: false,
			    title:title,
			    content: url,
			    area: [width+'px', height+'px'],
			    maxmin: false,
			    btn: ['保存','关闭'],
			    yes: function(index, layero){
			       var tip = window.frames["layui-layer-iframe"+layerindex].submitTree();
			        layer.msg(tip, {shift: 6});
			        layer.close(layerindex);
			    },
			    cancel: function(index){
			        //按钮【按钮二】的回调
			    } 
			});
			
	}
 
 function openShortcutWin(url,title,width,height){
	  var layerindex =  layer.open({
		    type: 2,
		    scrollbar: false,
		    title:title,
		    content: url,
		    area: [width+'px', height+'px'],
		    maxmin: false,
		    btn: ['保存','关闭'],
		    yes: function(index, layero){
		    	layer.confirm("确定保存？", {
				    btn: ['是','否'], //按钮
				    shade: false //不显示遮罩
				}, function(){
					var tip = window.frames["layui-layer-iframe"+layerindex].save();
			        layer.msg(tip, {shift: 6});
			        layer.close(layerindex);
				}, function(){
					layer.msg("决定好再操作", {shift: 6});
				});
		       
		    },
		    cancel: function(index){
		        //按钮【按钮二】的回调
		    } 
		});
		
}
 
 
 </script>
</head>
<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">系统管理</a></li>
    <li><a href="#">角色管理</a></li>
    </ul>
    </div>
    <t:datagrid  name="restfuldatagrid" actionUrl="${basePath }/admin/restful/4" pagination="true" queryMode="group"  checkbox="true">
    		<t:dgCol title="操作" field="opt" width="300"></t:dgCol>
    	 	<t:dgCol title="" field="id" hidden="true" ></t:dgCol>
    		<t:dgCol title="名称" field="name"  query="true"></t:dgCol>
    		<t:dgCol title="创建时间" field="ct" formatter="yyyy-MM-dd HH:mm:ss" align="center" width="250"  ></t:dgCol>
    		<t:dgFunOpt funname="openWinMax('role.do?roleaddorupd&id={id}','更新角色')" title="更新"></t:dgFunOpt>
    		<t:dgConfirmOpt url="role.do?del&id={id}" title="删除" message="是否删除？"></t:dgConfirmOpt>
    		<t:dgToolBar title="添加" icon="add" funname="openWinMax('role.do?roleaddorupd','添加角色')"></t:dgToolBar>
    		<t:dgToolBar title="批量删除" icon="del" funname="bathDel('role.do?del','roledatagrid','确定批量删除？')"></t:dgToolBar>
    </t:datagrid>
    
 
</body>

</html>
