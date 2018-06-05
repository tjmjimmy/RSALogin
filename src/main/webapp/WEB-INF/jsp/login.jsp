<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>RSALoginTest</title>
	<link rel="stylesheet" href="${contextPath }/testCookie" >
	<script src="${contextPath }/js/jquery.min.js" type="text/javascript"></script>
	<script src="${contextPath }/js/security.js" type="text/javascript"></script>
	<script src="${contextPath }/js/login.js" type="text/javascript"></script>
</head>
<body>
	<input type="hidden" id="hidden_modulus" value="${modulus }" />
	<input type="hidden" id="hidden_exponent" value="${exponent }" />
	<div>
		<p>登录电商运营平台</p>
        <div>
            <input name="username" id="username" type="text" placeholder="请输入用户名" />
        </div>
        <div>
            <input name="password" id="password" type="password" placeholder="请输入登录密码" />
        </div>
        <button type="button" onclick="ajaxLogin()" id="loginBtn">登录</button>
	</div>
	<!-- 這里是访问不到的，因为不能让服务器上的程序通过浏览器去访问本地文件，那是不安全的 -->
	<!-- <div><img alt="" src="file:///F:/googleDownload/f05bb1d76fa533461a8eed667aa43c17.jpg"></div> -->
</body>
<script type="text/javascript">
$(function(){
	$.ajax({
		url:"${contextPath }/testCookie",
		dataType:"json",
		type:"post",
		data : {mydata: false},
		success:function(data){
			}
		})
})
</script>
</html>