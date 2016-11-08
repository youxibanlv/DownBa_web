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
			error : function(XMLHttpRequest, textStatus,errorThrown) {
					alert("error = " + textStatus);
				},
			success : function(data, textStatus) {
				if (data.resultCode == 0) {
					$("#appTable tr").remove();//删除所有行，第一行除外
					// 解析出data对应的Object数组 
					$.each(data,function(index, values) {
							$.each(values,function(index2,value) {
								$("#appTable").append("<tr><td>"+ value.app_id + "</td><td>"+ value.app_title + 
								  "</td><td><img  width= '60' height='60' src = ' "+value.app_logo+"'/></td></tr>"); //动态添加行
								$("#appTable").show();
							    $("#subjectTable").hide();
								$("#appName").val("");
							});
						});
						changeBg();
					} else if (data.resultCode == 1) {
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
		$("#appTable tr").on('click',function() {
			//编辑 homeBeanTable 
			var appid = $(this).find("td").eq(0).text();
			var appName = $(this).find("td").eq(1).text();
			var appLogo = $(this).find("td").eq(2).find("img")
					.attr("src");
			$("#subjectTable").append("<tr><td>"+ appid+ "</td><td>"+ appName
				+ "</td><td><img  width= '40' height='40' src = ' "+appLogo+
				"'/></td><td onclick='del(this)'>移除</td></tr>"); //动态添加行
			$("#subjectTable").show();
			$("#appTable").hide();
		})
	};
	function del(src) {
		$(src).parent().remove();
	}
	//提交前验证输入
	function check() {
		var subjectTitle = $("#subjectTitle ").val();
		var subjectLogo = $("#subjectLogo").val();
		var subjectDes = $("#subjectDes").val();
		var appids = "";
		$("#subjectTable  tr").each(function() {
			var id = $(this).children("td:first").text();
			appids += id + ","
		});
		appids = appids.length > 0 ? appids.substring(0, appids.length - 1)
				: "";
		 if (subjectTitle == "") {
			alert("请输入标题")
			return false;
		} else if(subjectLogo == ""){
			alert("请选择图片")
			return false;
		} else if (appids == "") {
			alert("请选择应用")
			return false
		} else if (subjectDes == ""){
			alert("请输入描述");
			return false;
		} else {
			$("#appids").val("");
			$("#appids").val(appids);
		}
	}
	function showAddapp() {
		$("#showAppEdit").show();
	}
</script>
<div id="dcMain">
	<!-- 当前位置 -->
	<div id="urHere">
		下吧市场管理中心<b></b><strong>专题设置</strong>
	</div>
	<div class="mainBox imgModule">
		<table style="width: 100%; border: 1px" class="tableBasic">
			<tr>
				<th style="width: 40%; height: 40px"><font
					style="font-size: 20px">添加专题</font></th>
				<th><font style="font-size: 20px">专题列表</font></th>
			</tr>
			<tr>
				<td valign="top">
					<fieldset>
						<legend>
							<font color="red">添加专题</font>
						</legend>
						<form action="<%=basePath%>subject/add.do" onsubmit="return check();" enctype="multipart/form-data" method="post">
							标 &nbsp; &nbsp; &nbsp;题： 
							<input type="text" name="subjectTitle" id="subjectTitle" placeholder="请输入标题" class="inpMain" /><br />
							描 &nbsp; &nbsp; &nbsp;述： 
							<textarea name="subjectDes" id="subjectDes" cols="50" rows="5" class="textArea"></textarea><br />
							<br />
							 <input type="hidden" name="appids" id="appids">
							广告图片:
							<input type="file" id="subjectLogo" name="subjectLogo" class="inpFlie" accept="image/*" placeholder="建议使用png格式" /><br>
							<br>
							 排 &nbsp; &nbsp; &nbsp;序：
							 <input type="text" id="sort" value="1" size="20" name="sort" class="inpMain" maxlength="1" pattern="[0-9]" /><br>
							<br>
							<!-- 已添加的app列表 -->
							<font color="red">已选择的App列表</font><br>
							<table id="subjectTable" width="100%" border="0" cellpadding="8"
								cellspacing="0" class="tableBasic">
							</table><br>
							<input name="submit" class="btn" type="submit" value="提交" width="100%" class="inpMain">
						</form>
						<!-- 添加app控件 -->
						<div id="showAppEdit">
							app名称：<input type="text" name="appName" value="" size="20"
								class="inpMain" id="appName" />
							<button style="width: 50px; height: 20px" onclick="getApp();">查询</button>
							<br /> <br>
							<table id="appTable" width="100%" border="0" cellpadding="8"
								cellspacing="0" class="tableBasic" hidden="true">
							</table>
						</div>
					</fieldset>
				</td>
				<td valign="top">
				<div class="pager" style="background-color: lightgray;vertical-align: center;">
						总计 ${pageBean.total}个记录，共 ${pageBean.totalPage } 页，当前第
						${pageBean.pageNo} 页 | <a
							href="<%=basePath%>subject/getList.do?pageNo=1">第一页</a> <a
							href="<%=basePath%>subject/getList.do?pageNo=${pageBean.pageNo-1<1?1:pageBean.pageNo-1}">上一页</a>
						<a href="<%=basePath%>subject/getList.do?pageNo=${pageBean.pageNo+1>pageBean.totalPage?pageBean.totalPage:pageBean.pageNo+1}">下一页</a>
						<a href="<%=basePath%>subject/getList.do?pageNo=${pageBean.totalPage}">最末页</a>
					</div>
					<table style="width: 100%; border: 1px;" class="tableOnebor">
						<tr style="width: 100%; height: 30px">
							<td width="30" align="center">id</td>
							<td width="80" align="center">专题名称</td>
							<td width="100" align="center">广告图</td>
							<td width="150" align="center">app列表</td>
							<td width="50" align="center">更新时间</td>
							<td width="50" align="center">排序</td>
							<td width="80" align="center">操作</td>
						</tr>
						<c:forEach items="${subjects }" var="subject" varStatus="status">
							<tr>
								<td width="30" align="center">${subject.id }</td>
								<td width="30" align="center">${subject.title }</td>
								<td width="80" align="center"><a
									href="${subject.logo }"> <img width="100"
										height="60" src="${subject.logo }" />
								</a></td>
								<td width="150" align="center">
								<c:forEach items="${subject.apps }" var="app" varStatus="status">
									<img alt="图标" src="${app.app_logo}" width="30px">
								</c:forEach>
							</td>
								<td width="50" align="center">${subject.dateTime }</td>
								<td width="50" align="center">${subject.sort }</td>
								<td width="80" align="center"><a
									href="<%=basePath %>subject/del.do?id=${subject.id }">
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