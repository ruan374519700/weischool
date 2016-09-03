<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@include file="../common/mytags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加或者修改</title>
<t:base type="jquery,tools,layer"></t:base>
</head>
<script>

	$(function() {
		$("#inset_checkall").click(function() {
			var flag = $(this).attr("checked");
			$("input[name=inset_checkbox]:checkbox").each(function() {
				if (flag == undefined) {
					$(this).removeAttr("checked");
				} else {
					$(this).attr("checked", flag);
				}
			})
		})
	});

	function save() { 
		var ids = selectIds("inset");
		var url='${basePath}auth/shortcut.do?saveintoset';
		url += "&id=" + ids+"&roleid=${roleid}";
		var msg ="";
		$.ajax({
            type:"POST",
            url:url,
            datatype: "json", 
            async:false,
            success:function(data){
            	msg= data.msg; 
            },
            //调用出错执行的函数
            error: function(){
            	msg= "网络异常";
            }         
         });
		return msg;
	}
	
 
	 
</script>
<body style="min-width: 0">
     
     <table class="filetable" >
       
    <thead>
    	<tr>
        <th style="width: 20px"><input   type="checkbox"  name="inset" id="inset_checkall"  /></th>
        <th >入口名称</th>
        </tr>    	
    </thead>
    
    <tbody style="width: 200px">
    	<c:if test="${empty obj }"><tr><td colspan="2" align="center" >暂无数据！</td></tr></c:if>
    	<c:forEach var="obj" items="${obj }">
    		<tr>
    			  <td style="width: 20px"><input   type="checkbox"  name="inset_checkbox"  <c:if test="${obj.isSelect eq 1 }">checked="checked"</c:if>  value="${obj.id }"/></td>
        		  <td>${obj.name }</td>
    		</tr>
    	</c:forEach>
    </tbody>
    
    </table>
   
 </body>
 </html>   