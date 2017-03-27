<%@page import="com.main.utils.TimeUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" >
<title>Insert title here</title>
<style type="text/css">
img {
	margin: 0 auto;
	width: 100%;
	vertical-align: middle;
}
</style>
</head>
<body>
	<center><h3>${info.info_title }</h3></center>
	<p>
		<span style="font-size: 11px">来源： 下吧市场|下吧网|</span>
		<span style="font-size: 11px">时间：${info.info_update_time }</span>
	</p>
	${info.info_body }
</body>
</html>