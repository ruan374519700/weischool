<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@include file="common/mytags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加或者修改</title>
<t:base type="jquery,ajaxform,layer,tools,valiform,select,imgupl,swfu"></t:base>
<script type="text/javascript">
var isPersonal ="${isPersonal}";
 
$(document).ready(function(e) {
    $(".select").uedSelect({
		width : 167			  
	});
});

$(function () {
	//隐藏swfu按钮
	$("#btnUpload").hide();
	$("#btnCancel").hide();
	$("#divFileProgressContainer").hide();
	$("#imgsrc").hide();
	$("#showImgName").hide();
	
});

//初始化swfu控件
	var swfu;
window.onload = function () {
	swfu = new SWFUpload({
		upload_url: '<%=basePath%>admin/file.do?upload',
		post_params: {"type" : "image"},
		use_query_string:true,
		// File Upload Settings
		file_size_limit : "2 MB",	// 文件大小控制
		file_types : "*.png;*.jpg;*.jpeg;*.gif;*.bmp",
		file_types_description : "Image Files",
		file_upload_limit : "0",
						
		file_queue_error_handler : imgFileQueueError,
		file_dialog_complete_handler : imgFileDialogComplete,//选择好文件后提交
		file_queued_handler : imgFileQueued,
		upload_progress_handler : imgUploadProgress,
		upload_error_handler : imgUploadError,
		upload_success_handler : imgUploadSuccess,
		upload_complete_handler : imgUploadComplete,
		button_placeholder_id : "spanButtonPlaceholder",
		button_width: 80,
		button_height: 30,
		button_text : "<span class='button'>请选择图片</span>",
		button_text_style : '.button { font-size: 15pt; color: #0099FF; background:url(../rs/images/buttonbg.png) no-repeat;}',
		button_text_top_padding: 5,
		button_text_left_padding: 0,
		button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
		button_cursor: SWFUpload.CURSOR.HAND,
		
		// Flash Settings
		flash_url : "<%=basePath %>admin/js/swfupload/swfupload.swf",

		custom_settings : {
			upload_target : "divFileProgressContainer"
		},
		// Debug Settings
		debug: false  //是否显示调试窗口
	});
};
function startUploadFile(){
	swfu.startUpload();
}

function callback1(responseText){
	if(isPersonal=='1'){
		layer.msg(responseText.msg, {shift: 6});
		window.location.reload();
	}else{
		callback(responseText);
	}
	
}
</script>
</head>

<body>
<c:if test="${isPersonal eq 1 }">
    <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">系统管理</a></li>
    <li><a href="#">修改个人资料</a></li>
    </ul>
    </div>
</c:if>    
    <div class="formbody">
  <c:if test="${isPersonal eq 0 }">   
    <div class="formtitle"><span>修改资料</span></div>
   </c:if> 
    <t:formvalid formid="AuthUserData" action="${basePath }auth/user.do?userdataupd"  callback="callback1(responseText);" btnsub="sb" >
    <input name="id" type="hidden"  value="${obj.id }" />
    <ul class="forminfo">
    <li>
    <input id="imgsrc" name="imageSrc" type="text" class="dfinput" readonly="true" value="${obj.imageSrc }" />
    <label>头像</label>
    	<div id="content">
				<div>
					<input id="btnUpload" type="button" value="上  传" class="btn"
						onclick="startUploadFile();" /> 
					<input id="btnCancel" type="button" value="取消所有上传" class="btn"
						onclick="cancelUpload();" disabled="disabled" />
				</div>
				<div id="divFileProgressContainer"></div>
				<input id="showImgName" type="text" class="dfinput" readonly="true" value="${obj.imageSrc }" />
				<img id="imgDiv" src="${basePath}${obj.imageSrc }" width="200" height="200" /><span id="spanButtonPlaceholder" style=""></span> 
		</div>
	
	</li>
    <li><label>用户姓名</label><input name="name" type="text" class="dfinput" value="${obj.name }" readonly="readonly"/><i></i></li>
    <li><label>QQ账号</label><input name="qqAccount" type="text" class="dfinput" value="${obj.qqAccount }" datatype="n5-11" /><i></i></li>
    <li><label>手机号码</label><input name="mobilePhone" type="text" class="dfinput" value="${obj.mobilePhone }" datatype="n3-12"/><i></i></li>
    <li><label>短号</label><input name="shortPhone" type="text" class="dfinput" value="${obj.shortPhone }" datatype="n3-7" ignore="ignore"/><i></i></li>
    <li><label>邮箱</label><input name="email" type="text" class="dfinput" value="${obj.email }" datatype="e"/><i></i></li>
    <li><label>职位</label><input name="position" type="text" class="dfinput" value="${obj.position }"/><i></i></li>
    <li><label>班级或单位</label><input name="unit" type="text" class="dfinput" value="${obj.unit }" /><i></i></li>
    <li><label>宿舍或住处</label><input name="hostel" type="text" class="dfinput" value="${obj.hostel }" /><i></i></li>
    <li><label>是否毕业</label>
      <cite><input name="isGraduation" type="radio" value="1"   datatype="*" nullmsg="请选择是否毕业" <c:if test="${obj.isGraduation==1}">checked="checked"</c:if>  />是&nbsp;&nbsp;&nbsp;&nbsp;
      <input name="isGraduation" type="radio" value="0"  <c:if test="${obj.isGraduation==0}">checked="checked"</c:if> />否</cite></li>
    <li><label>&nbsp;</label><input name="" type="button"  id="sb" class="btn" value="确认${not empty obj.id ?'更新':'保存' }"/></li>
    </ul>
    </t:formvalid>
 </div>
 </body>
 </html>   