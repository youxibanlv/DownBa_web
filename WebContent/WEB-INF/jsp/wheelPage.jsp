<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");
	Object msg = request.getAttribute("msg");
	if(msg != null ){
		out.println("<script type ='text/javascript'>alert('"+msg.toString()+"')</script> ");
	}
%>
<%@include file="nav.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	function getApp() {
		if($("#appName").val() == ""){
			alert("请输入app名称");
		}else{
			$.ajax({
				data:{"appName":$("#appName").val()},
				type:"POST",
				dataType:'json',
				contentType:'application/x-www-form-urlencoded; charset=UTF-8',
				url:"<%=basePath%>app/getAppByName.do",
						error : function(XMLHttpRequest, textStatus,
								errorThrown) {
							alert("error = " + textStatus);
						},
						success : function(data, textStatus) {
							$("#appTable tr:gt(0)").remove();//删除所有行，第一行除外
							// 解析出data对应的Object数组 
							$.each(data, function(index, values) {
								$.each(values, function(index2, value) {
									$("#appTable").append(
											"<tr><td>" + value.app_id
													+ "</td><td>"
													+ value.app_title
													+ "</td></tr>"); //动态添加行
									$("#appTable").show();
								});
							});
							changeBg();
						},
					});
		}
	}
	function changeBg() {
		$("#appTable tr").on('mouseover', function() {
			$(this).find("td").addClass("mouse_color");
		})
		$("#appTable tr").on('mouseout', function() {
			$(this).find("td").removeClass("mouse_color");
		})
		$("#appTable tr").on('click', function() {
			$("#appid").val($(this).find("td").eq(0).text());//设置appid
			$("#appName").val($(this).find("td").eq(1).text());//设置输入app名
			$("#recommendTitle").val($(this).find("td").eq(1).text());//广告标题，默认app名称
			$("#appTable").hide();//隐藏表格
		})
	};
	function wheelPageCheck() {
		var appid = $("#appid").val();
		var img = $("#show_img").val();
		var title = $("#recommendTitle").val();
		var order = $("#sort").val();
		if (appid == "") {
			alert("请先选择应用");
			return false;
		}
		if (img == "") {
			alert("请先选择图片");
			return false;
		} else {
			var imgs = img.substring(img.lastIndexOf("."), img.length)
					.toUpperCase();
			if (imgs != ".BMP" && imgs != ".PNG" && imgs != ".JPG"
					&& imgs != ".JPEG") {
				alert("只能选择 bmp，png，jpg，jpeg格式的图片");
				return false;
			}
		}

	};
</script>
<div id="dcMain">
	<!-- 当前位置 -->
	<div id="urHere">
		下吧市场管理中心<b></b><strong>首页轮播图设置</strong>
	</div>
	<div class="mainBox imgModule">
		<h3 align="center">首页轮播图设置</h3>
		<table style="width: 100%; border: 1px" class="tableBasic">
			<tr>
				<th style="width: 40%; height: 40px"><font style="font-size: 20px">添加轮播图</font></th>
				<th><font style="font-size: 20px">轮播图列表</font></th>
			</tr>
			<tr>
				<td valign="top">
					<fieldset>
						<legend>
							<font color="red">添加轮播图</font>
						</legend>
						app名称：<input type="text" name="appName" value="" size="20"class="inpMain" id="appName" />
						<button style="width: 50px; height: 20px" onclick="getApp()">查询</button>
						<br /> <br>
						<form action=" <%=basePath %>recommend/addRecommend.do"
							method="post" enctype="multipart/form-data"
							onsubmit="return wheelPageCheck();">
							<input type="hidden" id="appid" name="appId"> <input
								type="hidden" id="recommend_type" name="recommend_type"
								value="1"> <input type="hidden" id="recommendTitle"
								name="recommend_title">
							<table id="appTable" width="100%" border="0" cellpadding="8"
								cellspacing="0" class="tableBasic" hidden="true">
								<tr>
									<th>appId</th>
									<th>名称</th>
								</tr>
							</table>
							广告图片:<input type="file" id="show_img" name="recommend_logo"
								class="inpFlie" accept="image/*" placeholder="建议使用png格式" /><br>
							<br> 排序：<input type="text" id="sort" value="5" size="20" name="sort"
								class="inpMain" maxlength="1" pattern="[0-9]" /><br> <br>
							<input name="submit" class="btn" type="submit" value="提交"
								width="100%">
						</form>
					</fieldset>
				</td>
				<td valign="top">
					<table style="width: 100%; border: 1px;" class="tableOnebor">
						<tr style="width: 100%;height: 30px">
							<td width="30" align="center">推荐id</td>
							<td width="30" align="center">应用名称</td>
							<td width="80" align="center">缩略图</td>
							<td width="50" align="center">更新时间</td>
							<td width="50" align="center">排序</td>
							<td width="80" align="center">操作</td>
						</tr>
						<c:forEach items="${recommends }" var="recommend" varStatus="status">
						 <tr>
						 	<td width="30" align="center">${recommend.recommend_id }</td>
							<td width="30" align="center">${recommend.recommend_title }</td>
							<td width="80" align="center" >
							<a href="${recommend.recommend_logo }">
							<img width="60" height="60" src="${recommend.recommend_logo }"/>
							</a>
							</td>
							<td width="50" align="center">${recommend.update_time }</td>
							<td width="50" align="center">${recommend.sort }</td>
							<td width="80" align="center"><a href="product.php?rec=edit&id=1">编辑</a>
							| <a href="<%=basePath %>recommend/delRecommend.do?recommend_id=${recommend.recommend_id }">删除</a></td>
						</tr>
						</c:forEach>
					</table>
				</td>
			</tr>

		</table>
	</div>
</div>
<div class="clear"></div>
<div id="dcFooter">
	<div id="footer"></div>
	<!-- dcFooter 结束 -->
	<div class="clear"></div>
</div>
</body>
</html>