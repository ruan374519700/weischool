<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common/mytags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户管理</title>
<t:base type="jquery,wdate,layer,tools"></t:base>
  <script type="text/javascript">
  var isFirstLoad =0;
  	function loadTree(){
  		if(isFirstLoad>0){
  			parent.loadTree();
  		}
  		isFirstLoad=1;
  	}
  </script>
</head>

<body>
    <t:datagrid  name="moduledatagrid" actionUrl="${basePath }auth/module.do?datagrid&parentid=${pid }" onLoadSuccess="loadTree();" pagination="true" queryMode="group"  checkbox="true">
    		<t:dgCol title="操作" field="opt" width="150"></t:dgCol>
    	 	<t:dgCol title="" field="id" hidden="true" ></t:dgCol>
    		<t:dgCol title="模块名称" field="name"  query="true" width="250"></t:dgCol>
    		<t:dgCol title="菜单图标" field="icon"  query="false" width="100"></t:dgCol>
    		<t:dgCol title="地址" field="url" ></t:dgCol>
    		<t:dgCol title="排序号" field="orderby"   width="100"></t:dgCol>
    		<t:dgCol title="状态" field="isenable" replace="1_启用中,0_禁用中" query="true" align="center"  width="100" ></t:dgCol>
    		<t:dgFunOpt funname="openWinMax('${basePath }auth/module.do?module&id={id}&pid=${pid }','更新{name}')" title="更新"></t:dgFunOpt>
    		<t:dgConfirmOpt url="module.do?isenabled&id={id}&isenable=0" title="禁用" exp="isenable#eq#1"  message="是否禁用？"></t:dgConfirmOpt>
    		<t:dgConfirmOpt url="module.do?isenabled&id={id}&isenable=1" title="启用" exp="isenable#eq#0"  message="是否启用？"></t:dgConfirmOpt>
    		<t:dgConfirmOpt url="module.do?del&id={id}" title="删除" message="是否删除？"></t:dgConfirmOpt>
    		<t:dgToolBar title="添加" icon="add" funname="openWinMax('${basePath }auth/module.do?module&pid=${pid }','添加')"></t:dgToolBar>
    		<t:dgToolBar title="删除" icon="del" funname="bathDel('${basePath }auth/module.do?del','moduledatagrid','确定批量删除？')"></t:dgToolBar>
    </t:datagrid>
    
 
</body>

</html>
