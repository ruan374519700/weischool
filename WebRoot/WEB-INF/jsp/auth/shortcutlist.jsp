<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common/mytags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户管理</title>
<t:base type="jquery,wdate,layer,tools"></t:base>
 
</head>
<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">系统管理</a></li>
    <li><a href="#">桌面快捷</a></li>
    </ul>
    </div>
    <t:datagrid  name="shortcutdatadatagrid" actionUrl="${basePath }auth/shortcut.do?datagrid" pagination="true" queryMode="group"  checkbox="true">
    		<t:dgCol title="操作" field="opt" width="250"></t:dgCol>
    	 	<t:dgCol title="" field="id" hidden="true" ></t:dgCol>
    		<t:dgCol title="快捷名称" field="name"  query="true"></t:dgCol>
    		<t:dgCol title="图片名称" field="icon"  align="center"></t:dgCol>
    		<t:dgCol title="入口地址" field="url" query="true"   ></t:dgCol>
    		<t:dgCol title="排序号" field="orderby"   width="100"></t:dgCol>
    		<t:dgFunOpt funname="orderUpOrDown('{id}','up')"   title="上移" ></t:dgFunOpt>
 			<t:dgFunOpt funname="orderUpOrDown('{id}','down')"   title="下移" ></t:dgFunOpt>
    		<t:dgFunOpt funname="openWinMax('${basePath }/auth/shortcut.do?addorupdate&id={id}','更新{name}')" title="更新"></t:dgFunOpt>
    		<t:dgConfirmOpt url="shortcut.do?del&id={id}" title="删除" message="是否删除？"></t:dgConfirmOpt>
    		<t:dgToolBar title="添加" icon="add" funname="openWinMax('${basePath }/auth/shortcut.do?addorupdate','添加桌面快捷')"></t:dgToolBar>
    		<t:dgToolBar title="删除" icon="del" funname="bathDel('${basePath }/auth/shortcut.do?del','shortcutdatadatagrid','确定批量删除？')"></t:dgToolBar>
    </t:datagrid>
    <script type="text/javascript">
  	function orderUpOrDown(id,type){
		$.ajax({
			data:"post",
			dataType:"json",
			url:"<%=basePath %>auth/shortcut.do?orderUpOrDown&id="+id+"&type="+type,
			success : function(response) {
				if(response.success)
					roadDataGrid();
				else 
					layer.msg(response.msg, {shift: 6});
			}
		});
	}
  </script>
</body>
</html>

