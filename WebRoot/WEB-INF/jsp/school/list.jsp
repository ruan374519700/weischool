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
    <li><a href="#">学校管理</a></li>
    </ul>
    </div>
    <t:datagrid  name="schooldatagrid" actionUrl="${basePath }/admin/school/4" pagination="true" queryMode="group"  checkbox="true">
    		<t:dgCol title="操作" field="opt" width="300"></t:dgCol>
    	 	<t:dgCol title="" field="id" hidden="true" ></t:dgCol>
    		<t:dgCol title="学校名称" field="name"  query="true"></t:dgCol>
    		<t:dgCol title="校长/馆长" field="head_master"  query="true"></t:dgCol>
    		<t:dgCol title="联系手机号码" field="m_phone"  query="false"></t:dgCol>
    		<t:dgCol title="简介" field="remark"  query="false"></t:dgCol>
    		<t:dgCol title="状态" field="is_enable" replace="1_启用中,0_禁用中" query="true" align="center" width="100"></t:dgCol>
    		<t:dgFunOpt funname="openWinMax('${basePath }/admin/school/3&id={id}','更新学校')" title="更新"></t:dgFunOpt>
    		<t:dgConfirmOpt url="school.do?del&id={id}" title="删除" message="是否删除？"></t:dgConfirmOpt>
    		<t:dgToolBar title="添加" icon="add" funname="openWinMax('${basePath }admin/school/2','添加学校')"></t:dgToolBar>
    		<t:dgToolBar title="批量删除" icon="del" funname="bathDel('school.do?del','schooldatagrid','确定批量删除？')"></t:dgToolBar>
    </t:datagrid>
    
 
</body>

</html>
