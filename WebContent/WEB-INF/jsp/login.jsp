<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>管理员登录</title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/index.css">
<script src="<%=basePath %>js/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">
	function check(){
		if($("#account").val()==""||$("#password").val() == ""){
			alert("账号密码不能为空");
			return false;
		}
	}
</script>
</head>
<body style="background:#000;">
<div class="png login_logo"><img src="<%=basePath %>images/logo_03.png" ></div>
<div class="login">
<form action="admin/login.do" method="post" onsubmit="return check();">
<div class="login_1"><input name="account" id="account" type="text"></div>
<div class="login_2"><input name="password" id="password" type="password"></div>
<div class="login_4 png"><input type="submit" value="登 录"/></div>
</form>
</div>
</body>
</html>