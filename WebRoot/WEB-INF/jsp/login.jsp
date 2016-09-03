<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@include file="common/mytags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>欢迎登录后台管理系统</title>
<link href="<%=basePath %>rs/css/style.css" rel="stylesheet" type="text/css" />
 
<t:base type="jquery,layer,tools"></t:base>
<script src="<%=basePath %>rs/js/cloud.js" type="text/javascript"></script>
  <script type="text/javascript" src="<%=basePath %>rs/js/security.js"></script>
<script language="javascript">
var encryptCacheData;
//回车登录
$(document).keydown(function(e){
	if(e.keyCode == 13) {
		Login();
	}
});

function Login(){
	getKey();
	$.ajax({
		url:'<%=basePath %>auth/login.do?checkuser',
		dataType:'json',
		data:{longkey:encryptCacheData},
		type:'post',
		cache : false,
		async:false,
		success:function(data){
			layer.msg(data.msg, {shift: 6});
			if(data.success==true){
				checkBox();
				window.location.href="<%=basePath%>auth/login.do?login";
			}else{
				 if(data.obj==0){
					 changeCode();
					 $("#account").select();
				 }
				 if(data.obj==-1){
					 changeCode();
					 window.location.reload();
				 }
				 if(data.obj==-2){
					 changeCode();			
					 $("#validateCode").select();
				 }
			}	
		},
		error:function(){
			layer.msg('网络异常！', {shift: 6});
		}
		
	});	
}

function getKey(){
	$.ajax({
		url:'<%=basePath %>auth/login.do?checkKey&time='+ Math.floor(Math.random()*100),
		datatype:'json',
		type:"post",
		async:false,
		success:function(data){
			if(data.success==true){
				// 页面里，Javascript对明文进行加密：
				var key = RSAUtils.getKeyPair(data.attributes.exponent, '', data.attributes.modulus);
				var acc =$("#account").val();
				var pasw=$("#password").val();
				var code = $("#validateCode").val();
				var time =  data.attributes.time;
				if(acc.length>0&&pasw.length>0){
				  encryptCacheData = RSAUtils.encryptedString(key,code+","+time+","+encodeURIComponent(acc)+","+encodeURIComponent(pasw));	
			     } 
			}else{
				layer.msg(data.msg, {shift: 6});
		 
			}
    	}
	});
}

 function test(){
	 
	 $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
		$(window).resize(function(){  
	    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	    });
 } 
 
 function checkBox()	{
	 var box = document.getElementById("rmbPwd");
		if (box.checked) {
			setCookie("account",$("#account").val());
			setCookie("password",$("#password").val());
			//监听复选框
		} else {
			setCookie("account","");
			setCookie("password","");
		}
 }
 
 function setCookie (name, value) {
	 var Days = 14; 
	 var exp = new Date(); 
	 exp.setTime(exp.getTime() + Days*24*60*60*1000); 
	 document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
 }
 
 function getCookie (name){
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg))
	    return unescape(arr[2]); 
	else 
	    return null;
 }
 
 function setActPsw (){
	 var account = getCookie("account");
	 var password = getCookie("password");
	 
	 $("#account").val(account);
	 $("#password").val(password);
	 
 }
 
 $(function(){        
	    $('#kaptchaImage').click(function () {//生成验证码  
	     $(this).hide().attr('src', '<%=basePath%>auth/captcha.do?kaptcha&time' + Math.floor(Math.random()*100) ).fadeIn();  
	     event.cancelBubble=true;  
	    }).css("height","40px");  
	    setActPsw();
});   
 function changeCode() {  
	    $('#kaptchaImage').hide().attr('src', '<%=basePath%>auth/captcha.do?kaptcha&time' + Math.floor(Math.random()*100) ).fadeIn();  
	    $("#validateCode").val("");
	    event.cancelBubble=true;  
	}  
</script> 

</head>

<body onload="test()" style="background-color:#1c77ac; background-image:url(<%=basePath %>rs/images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎登录广州倪科商贸有限公司结算管理系统</span>    
    <ul>
    <li><a href="#">回首页</a></li>
    <li><a href="#">添加到收藏夹</a></li>
    <li><a href="#">关于</a></li>
    </ul>    
    </div>
    
    <div class="loginbody">
    
    <span class="systemlogo"></span> 
       
    <div class="loginbox">
    <form id="loginForm"  class='ajform'>
    <ul>
    <!--<li><input type="text" id="account" name="account" class="loginuser" value="798112095" onfocus="if(this.value=='请输入帐号')this.value=''" onclick="if(this.value=='请输入帐号')this.value=''"  /></li>
    <li><input type="password" id="password" name="password" class="loginpwd"   value="123456" /></li>
     <li><input type="text"   id="validateCode" name="validateCode" maxlength="4"   onclick="if(this.value=='请输入验证码')this.value=''"  style="vertical-align: middle;width:100px; text-indent:8px;height:40px; line-height:40px;   font-size:14px; color:#90a2bc;border:1px solid #90a2bc" value="请输入验证码" /> -->
   <li><input type="text" id="account" name="account" class="loginuser" value="798112095"   /></li>
    <li><input type="password" id="password" name="password" class="loginpwd"   value="123456" /></li>
   <li><input type="text"   id="validateCode" name="validateCode" maxlength="4"   onclick="if(this.value=='请输入验证码')this.value=''"  style="vertical-align: middle;width:100px; text-indent:8px;height:40px; line-height:40px;   font-size:14px; color:#90a2bc;border:1px solid #90a2bc" value="1234" />
    <img  src="<%=basePath%>auth/captcha.do?kaptcha" id="kaptchaImage"  style="vertical-align: middle;" /></li>
    <li><input type="button" class="loginbtn" value="登录"  onclick="Login()"   />
    <label><input id="rmbPwd" name="" type="checkbox" value="" />记住帐号密码</label>
    </ul>
    </form>
    
    </div>
    
    </div>
    
    
    
    <div class="loginbm">版权所有  2016   广州倪科商贸有限公司</div>
	
    
</body>

</html>

