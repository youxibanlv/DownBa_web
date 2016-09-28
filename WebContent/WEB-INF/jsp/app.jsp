<%@page import="com.main.utils.VerifyUtils"%>
<%@page import="com.main.model.PageBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="nav.jsp"%>
<div id="dcMain">
	<!-- 当前位置 -->
	<div id="urHere">
		下吧市场管理中心<b>></b><strong>app列表</strong>
	</div>
	<div class="mainBox"
		style="height: auto !important; height: 550px; min-height: 550px;">
		<div class="filter">
			<form action="product.php" method="post">
				<select name="cat_id">
					<option value="0">未分类</option>
					<option value="1">电子数码</option>
					<option value="4">- 智能手机</option>
					<option value="5">- 平板电脑</option>
					<option value="2">家居百货</option>
					<option value="3">母婴用品</option>
				</select> <input name="keyword" type="text" class="inpMain" value=""
					size="20" /> <input name="submit" class="btnGray" type="submit"
					value="筛选" />
			</form>
			<span> <a class="btnGray" href="product.php?rec=re_thumb">更新商品缩略图</a>
				<a class="btnGray" href="product.php?rec=sort">开始筛选首页商品</a>
			</span>
		</div>
		<div id="list">
			<form name="action" method="post" action="product.php?rec=action">
				<table width="100%" border="0" cellpadding="8" cellspacing="0"
					class="tableBasic">
					<tr>
						<th width="22" align="center"><input name='chkall'
							type='checkbox' id='chkall' onclick='selectcheckbox(this.form)'
							value='check'></th>
						<th width="40" align="center">app_id</th>
						<th width="80" align="center">排序</th>
						<th width="160" align="center">图标</th>
						<th width="160" align="center">名称</th>
						<th width="80" align="center">分类</th>
						<th width="80" align="center">版本</th>
						<th width="80" align="center">访问量</th>
						<th width="80" align="center">下载量</th>
						<th width="80" align="center">大小</th>
						<th width="80" align="center">评分</th>
						<th width="80" align="center">操作</th>
					</tr>
				 <c:forEach items="${apps }" var="app" varStatus="status">
					 <tr>
							<td align="center"><input type="checkbox" name="checkbox[]"value="1" /></td>
							<td width="40" align="center">${app.app_id}</td>
							<td width="80" align="center">${app.app_order}</td>
							<td width="80" align="center">
								<img width="60" height="60" src="${app.app_logo}">
							</td>
							<td width="160" align="center">${app.app_title}</td>
							<td width="80" align="center">${app.last_cate_id}</td>
							<td width="80" align="center">${app.app_version}</td>
							<td width="80" align="center">${app.app_visitors}</td>
							<td width="80" align="center">${app.app_down}</td>
							<td width="80" align="center">${app.app_size}</td>
							<td width="80" align="center">${app.app_grade}</td>
							<td align="center"><a href="product.php?rec=edit&id=1">编辑</a>
							| <a href="product.php?rec=del&id=1">删除</a></td>
						</tr>
				 </c:forEach>
				</table>
				<div class="action">
					<select name="action" onchange="douAction()">
						<option value="0">请选择...</option>
						<option value="del_all">删除</option>
						<option value="category_move">移动分类至</option>
					</select> <select name="new_cat_id" style="display: none">
						<option value="0">未分类</option>
						<option value="1">电子数码</option>
						<option value="4">- 智能手机</option>
						<option value="5">- 平板电脑</option>
						<option value="2">家居百货</option>
						<option value="3">母婴用品</option>
					</select> <input name="submit" class="btn" type="submit" value="执行" />
				</div>
			</form>
		</div>
		<div class="clear"></div>
		<div class="pager">
			总计 ${pageBean.total}个记录，共 ${pageBean.totalPage } 页，当前第
			${pageBean.pageNo} 页 | <a
				href="<%=basePath%>app/getAppList.do?pageNo=1">第一页</a> <a
				href="<%=basePath%>app/getAppList.do?pageNo=${pageBean.pageNo-1<1?1:pageBean.pageNo-1}">上一页</a>
			<a
				href="<%=basePath%>app/getAppList.do?pageNo=${pageBean.pageNo+1>pageBean.totalPage?pageBean.totalPage:pageBean.pageNo+1}">下一页</a>
			<a href="<%=basePath%>app/getAppList.do?pageNo=${pageBean.totalPage}">最末页</a>
		</div>
	</div>
</div>
<div class="clear"></div>
<div id="dcFooter">
	<div id="footer">
		<div class="line"></div>
	</div>
</div>
<!-- dcFooter 结束 -->
<div class="clear"></div>
<script type="text/javascript">
	onload = function() {
		document.forms['action'].reset();
	}

	function douAction() {
		var frm = document.forms['action'];
		frm.elements['new_cat_id'].style.display = frm.elements['action'].value == 'category_move' ? ''
				: 'none';
	}
</script>
</body>
</html>
