<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>下吧市场管理中心</title>
<meta name="Copyright" content="Douco Design." />
<link href="<%=basePath %>css/public.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/common.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath %>js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/global.js"></script>
</head>
<body>
<div id="dcWrap">
	<div id="dcHead">
		<div id="head">
			<div class="nav">
				<ul class="navRight">
					<li class="M noLeft"><a href="JavaScript:void(0);">你好！${nickName}</a>
						<div class="drop mUser">
							<a href="manager.php?rec=edit&id=1">编辑我的个人资料</a>
						</div></li>
					<li class="noRight"><a href="login.jsp">退出</a></li>
				</ul>
			</div>
		</div>
	</div>
<!-- head结束 -->
<div id="dcLeft">
	<div id="menu">
		<ul class="top">
			<li><a href="<%=basePath%>home/iniHomePage.do"><i class="home"></i><em>管理首页</em></a></li>
		</ul>
		<ul>
			<li><a href="<%=basePath%>recommend/getRecommendList.do?recommend_type=1"><i class="show"></i><em>首页轮播图设置</em></a></li>
			<li><a href="<%=basePath%>recommend/getRecommendList.do?recommend_type=2"><i class="page"></i><em>精品推荐设置</em></a></li>
			<li><a href="<%=basePath%>recommend/getRecommendList.do?recommend_type=3"><i class="system"></i><em>猜你喜欢设置</em></a></li>
			<!-- <li><a href="nav.html"><i class="nav"></i><em>自定义导航栏</em></a></li> -->
		</ul>
		<ul>
			<li><a href="product_category.html"><i class="productCat"></i><em>App分类</em></a></li>
			<li><a
				href="<%=basePath%>app/getAppList.do"><i
					class="product"></i><em>App列表</em></a></li>
		</ul>
		<ul>
			<li><a href="article_category.html"><i class="articleCat"></i><em>资讯分类</em></a></li>
			<li><a href="article.html"><i class="article"></i><em>资讯列表</em></a></li>
		</ul>
		<ul class="bot">
			<!-- <li><a href="backup.html"><i class="backup"></i><em>数据备份</em></a></li>
					<li><a href="mobile.html"><i class="mobile"></i><em>手机版</em></a></li> -->
			<li><a href="theme.html"><i class="theme"></i><em>普通用户管理</em></a></li>
			<li><a href="manager.html"><i class="manager"></i><em>网站管理员</em></a></li>
			<li><a href="manager.php?rec=manager_log"><i
					class="managerLog"></i><em>操作记录</em></a></li>
		</ul>
	</div>
</div>
<!-- 左侧导航结束 -->