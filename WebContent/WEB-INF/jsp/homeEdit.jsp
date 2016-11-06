<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");
	Object msg = request.getAttribute("msg");
	if(msg != null && !"".equals(msg)){
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
							if(data.resultCode == 0){
								$("#appTable tr").remove();//删除所有行，第一行除外
								// 解析出data对应的Object数组 
								$.each(data, function(index, values) {
									$.each(values, function(index2, value) {
										$("#appTable").append("<tr><td>" + value.app_id + "</td><td>"
										+ value.app_title + "</td><td><img  width= '60' height='60' src = ' "+value.app_logo+"'/></td></tr>"); //动态添加行
										$("#appTable").show();
										$("#homeBeanTable").hide();
										$("#appName").val("");
									});
								});
								changeBg();
							}else if(data.resultCode == 1){
								alert(data.errorMsg);
								$("#appName").val("");
							}
							
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
			//编辑 homeBeanTable 
			var appid = $(this).find("td").eq(0).text();
			var appName =$(this).find("td").eq(1).text();
			var appLogo = $(this).find("td").eq(2).find("img").attr("src");
			$("#homeBeanTable").append("<tr><td>" + appid + "</td><td>"+ appName + 
					"</td><td><img  width= '40' height='40' src = ' "+appLogo+"'/></td><td onclick='del(this)'>移除</td></tr>"); //动态添加行
			$("#homeBeanTable").show();
			$("#appTable").hide();
			//设置提交的appids
			var appids = $("#appids").val();
			$("#appids").val(appids + appid+",");//设置appid
		})
	};
	function del(src){
		$(src).parent().remove();
	}
	//提交前验证输入
	function check() {
		var homeBeanType = $("#homeBeanType ").val();
		var homeBeanTitle = $("#homeBeanTitle").val();
		var appids="";
		$("#homeBeanTable  tr").each(function(){
			var id = $(this).children("td:first").text();
			appids += id +","
		});
		appids = appids.length>0?appids.substring(0,appids.length-1):"";
		if(homeBeanType ==0){
			alert("请选择类型");
		}else if(homeBeanTitle == ""){
			alert("请输入标题")
		}else if(appids == ""){
			alert ("请选择应用")
		}else{
			$.ajax({
				data:{"homeBeanType":homeBeanType,"homeBeanTitle":homeBeanTitle,"appids":appids},
				type:"POST",
				dataType:'json',
				contentType:'application/x-www-form-urlencoded; charset=UTF-8',
				url:"<%=basePath%>home/addHomeBean.do",
				error:function(){
					
				},
				success:function(data){
					/* if(data.success){
						$("#homeBeanTable  tr").remove();
						$("#homeBeanType ").val("0");
						$("#homeBeanTitle").val("");
						$("#appName").val("");
					}*/
					$.post("<%=basePath%>home/iniHomePage.do");
					alert(data.msg); 
				}
			});
		}
	};
	function showAddapp(){
		$("#showAppEdit").show();
	}
</script>
<div id="dcMain">
	<!-- 当前位置 -->
	<div id="urHere">
		下吧市场管理中心<b></b><strong>首页编辑</strong>
	</div>
	<div class="mainBox imgModule">
		<center><font style="font-size: 20px;">除了轮播图，都可以在这里设置</font></center><br/>
		<table style="width: 100%; border: 1px" class="tableBasic">
			<tr>
				<th style="width: 40%; height: 40px"><font style="font-size: 20px">添加首页内容</font></th>
				<th><font style="font-size: 20px">已有的首页列表</font></th>
			</tr>
			<tr>
				<td valign="top">
					<fieldset >
						<legend>
							<font color="red">添加首页内容</font>
						</legend>
						
						类 &nbsp; &nbsp; &nbsp;型：
						   <select name="homeBeanType" id="homeBeanType" onchange="showHomeBeanTable();">
								<option value="0">-请选择-</option>
								<option value="1">-精品推荐类型-</option>
								<option value="2">-普通推荐类型-</option>
								<option value="3">-专题类型-</option>
							</select><br/><br/>
						标 &nbsp; &nbsp; &nbsp;题：
						<input type="text" name ="homeBeanTitle" id="homeBeanTitle" placeholder="请输入标题" class="inpMain"/><br/><br/>
						<!-- 添加app控件 -->
						<div id="showAppEdit">
							app名称：<input type="text" name="appName" value="" size="20"class="inpMain" id="appName" />
							<button style="width: 50px; height: 20px" onclick="getApp()">查询</button>
							<br /> <br>
							<table id="appTable" width="100%" border="0" cellpadding="8"
								cellspacing="0" class="tableBasic" hidden="true">
							</table>
						</div>
						<!-- 已添加的app列表 -->
						<font color="red">已添加的App列表：(精品推荐建议三个，普通推荐5个)</font><br>
						<table id="homeBeanTable" width="100%" border="0" cellpadding="8"
								cellspacing="0" class="tableBasic">
						</table>	
						<br><br/>
						<input name="submit" class="btn" type="submit" value="提交" width="100%" onclick="check();">
					</fieldset>
				</td>
				<td valign="top">
					<div class="pager" style="background-color: lightgray;vertical-align: center;">
						总计 ${pageBean.total}个记录，共 ${pageBean.totalPage } 页，当前第
						${pageBean.pageNo} 页 | <a
							href="<%=basePath%>app/getAppList.do?pageNo=1">第一页</a> <a
							href="<%=basePath%>app/getAppList.do?pageNo=${pageBean.pageNo-1<1?1:pageBean.pageNo-1}">上一页</a>
						<a
							href="<%=basePath%>app/getAppList.do?pageNo=${pageBean.pageNo+1>pageBean.totalPage?pageBean.totalPage:pageBean.pageNo+1}">下一页</a>
						<a href="<%=basePath%>app/getAppList.do?pageNo=${pageBean.totalPage}">最末页</a>
					</div>
					<table style="width: 100%; border: 1px;" class="tableOnebor">
						<tr style="width: 100%;height: 30px">
							<td width="30" align="center">id</td>
							<td width="30" align="center">标题</td>
							<td width="30" align="center">广告图</td>
							<td width="150" align="center">app列表</td>
							<td width="50" align="center">更新时间</td>
							<td width="80" align="center">操作</td>
						</tr>
						<c:forEach items="${beans }" var="homeBean" varStatus="status">
						 <tr>
						 	<td width="30" align="center">${homeBean.id }</td>
							<td width="30" align="center">${homeBean.homeBeanTitle }</td>
							<td width="80" align="center" >
							<a href="${homeBean.homeBeanLogo }">
							<img width="60" height="60" src="${homeBean.homeBeanLogo }"alt="广告图"/>
							</a>
							</td>
							<td width="150" align="center">
								<c:forEach items="${homeBean.apps }" var="app" varStatus="status">
									<img alt="图标" src="${app.app_logo}" width="30px">
								</c:forEach>
							</td>
							<td width="50" align="center">${homeBean.updateTime }</td>
							<td width="80" align="center">
							<a href="<%=basePath %>recommend/delRecommend.do?recommend_id=${homeBean.id }">
								删除</a></td>
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