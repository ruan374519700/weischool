<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@include file="../common/mytags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加或者修改</title>
<t:base type="jquery,ajaxform,layer,tools,valiform"></t:base>
 <script type="text/javascript">
 function openWin(url,title,width,height){
	  var layerindex =  layer.open({
		    type: 2,
		    scrollbar: false,
		    title:title,
		    content: url,
		    area: [width+'px', height+'px'],
		    maxmin: false,
		    btn: ['保存','关闭'],
		    yes: function(index, layero){
		    	var msg = window.frames["layui-layer-iframe"+layerindex].getTree();
		     	if(msg!=""){
		     		var msgArray =msg.split("[-]");
		     		if(msgArray[0]!='null'){
		     			$("#name").val(msgArray[0]);
		     		}
		     		if(msgArray[1]!='null'){
		     			$("#icon").val(msgArray[1]);
		     		}
		     		if(msgArray[2]!='null'){
		     			$("#url").val(msgArray[2]);
		     		}
		     	}
		        layer.close(layerindex);
		       
		    },
		    cancel: function(index){
		        //按钮【按钮二】的回调
		    } 
		});
		
}

 </script>
</head>

<body>
    
    <div class="formbody">
    <div class="formtitle"><span>桌面快捷设置</span></div>
    <t:formvalid formid="theclass" action="${basePath }auth/shortcut.do?save"  callback="callback(responseText);" btnsub="sb" >
     <input name="id"  id="id" type="hidden"  value="${obj.id }" />
   <c:if test="${not empty obj.createBy }">
    <input name="createBy"  id="createBy" type="hidden"  value="${obj.createBy }" />
    </c:if>
    <c:if test="${not empty obj.createTime }">
    <input name="createTime"  id="createTime" type="hidden"  value="${obj.createTime }" />
    </c:if>
    <c:if test="${not empty obj.updateBy }">
    <input name="updateBy"  id="updateBy" type="hidden"  value="${obj.updateBy }" />
    </c:if>
    <c:if test="${not empty obj.updateTime }">
    <input name="updateTime"  id="updateTime" type="hidden"  value="${obj.updateTime }" />
    </c:if>
     <c:if test="${not empty obj.delFlag }">
    <input name="delFlag"  id="delFlag" type="hidden"  value="${obj.delFlag }" />
    </c:if>
    <c:if test="${not empty obj.delTime }">
    <input name="delTime"  id="delTime" type="hidden"  value="${obj.delTime }" />
    </c:if>
    <c:if test="${not empty obj.orderby }">
    <input name="orderby"  id="orderby" type="hidden"  value="${obj.orderby }" />
     </c:if>
    <c:if test="${not empty obj.isenable }">
    <input name="isenable"  id="isenable" type="hidden"  value="${obj.isenable }" />
    </c:if>
   
    <ul class="forminfo">
    <li><label>快捷名称</label><input name="name" id="name" maxlength="9" type="text" class="dfinput" value="${obj.name }" ajaxurl="${basePath }auth/shortcut.do?isExist&id=${obj.id}" datatype="s1-10"    /><i onclick="openWin('${basePath}auth/module.do?moduleTreeSelect','添加模块',300,500)">添加</i></i></li>
    <li><label>图标名称</label><input name="icon" id="icon"  type="text" class="dfinput" value="${obj.icon }"   datatype="*1-100" nullmsg="请输入图标名称！"  errormsg="请输入1-100位字符图标名称！"  /></li>
    <li><label>入口地址</label><input name="url" id="url"  type="text" class="dfinput" value="${obj.url }"    datatype="*1-200"  nullmsg="请输入入口地址！" maxlength="200"   errormsg="请输入1-200的入口地址！"/></li>
    <li><label>&nbsp;</label><input name="" type="button"  id="sb" class="btn" value="确认${not empty obj.id ?'更新':'保存' }"/></li>
    </ul>
    </t:formvalid>
 </div>
 </body>
 </html>   