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
function getChannel(pageNo,pageSize) {
	$.ajax({
		data:{"pageNo":pageNo,"pageSize":pageSize},
		type:"POST",
		dataType:'json',
		contentType:'application/x-www-form-urlencoded; charset=UTF-8',
		url:"<%=basePath%>channel/showChannels.do",
		error : function(XMLHttpRequest, textStatus,errorThrown) {
				alert("error");
			},
		success : function(data, textStatus) {
			if (data.resultCode == 0) {
				$("#channelTable tr").remove();//删除所有行，第一行除外
				// 解析出data对应的Object数组 
				$.each(data,function(index, values) {
						$.each(values,function(index2,value) {
							$("#channelTable").append("<tr><td>"+ value.channel_id + "</td><td>"+ value.channel_name + 
							  "</td><td>"+value.bak+"</td></tr>"); //动态添加行
							$("#channelDiv").show();
						});
					});
					changeBg();
				} else if (data.resultCode == 1) {
					alert(data.errorMsg);
				}

			},
		});
	}
	function changeBg() {
		$("#channelTable tr").on('mouseover', function() {
			$(this).find("td").addClass("mouse_color");
		})
		$("#channelTable tr").on('mouseout', function() {
			$(this).find("td").removeClass("mouse_color");
		})
		$("#channelTable tr").on('click',function() {
			//编辑 homeBeanTable 
			$("#channelId").val($(this).find("td").eq(0).text());
			$("#channelDiv").hide();
		})
	};
	function del(src) {
		$(src).parent().remove();
	}
	//提交前验证输入
	function check() {
		var versionName = $("#versionName ").val();
		var versionCode = $("#versionCode").val();
		var channelId = $("#channelId").val();
		var file = $("#file").val();
		var versionInfo = $("#versionInfo").val();
		
	 if (versionName == "") {
			alert("请输入版本名称")
			return false;
		} else if(versionCode == ""){
			alert("请输入版本号")
			return false;
		} else if (channelId == "") {
			alert("请输入渠道号")
			return false
		} else if (file == ""){
			alert("请选择apk包");
			return false;
		} else if(versionInfo == ""){
			alert("请输入更新内容");
			return false;
		}
	}
</script>
<div id="dcMain">
	<!-- 当前位置 -->
	<div id="urHere">
		下吧市场管理中心<b></b><strong>版本管理</strong>
	</div>
	<div class="mainBox imgModule">
		<table style="width: 100%; border: 1px" class="tableBasic">
			<tr>
				<th style="width: 40%; height: 40px"><font
					style="font-size: 20px">添加新版本</font></th>
				<th><font style="font-size: 20px">版本列表</font></th>
			</tr>
			<tr>
				<td valign="top">
					<fieldset>
						<legend>
							<font color="red">上传新版本</font>
						</legend>
						<form action="<%=basePath%>version/add.do" onsubmit="return check();" enctype="multipart/form-data" method="post">
							版本名称： 
							<input type="text" name="versionName" id="versionName" placeholder="请输入版本名称" class="inpMain" style="width: 250px" /><br />
							<br>
							版 本 号 ：
							 <input type="text" id="versionCode" id = "versionCode"  size="20" name="versionCode" class="inpMain" style="width: 250px" placeholder="只能输入数字"
							 onkeyup="if(event.keyCode !=37 && event.keyCode != 39){if (!/^[\d]+$/ig.test(this.value)){this.value='';}}"/><br>
							<br>
							渠 道 号 ： <input type="text" name="channelId" value="" size="20" placeholder="点击在弹出的列表中选取" style="width: 250px" 
								class="inpMain" id="channelId" readonly="readonly" onclick="getChannel(1,10);"/>
							<br /> <br>
							<!-- 查询渠道号 -->
						<div id="channelDiv" hidden="true">
							<table id="channelTable" width="100%" border="0" cellpadding="8"
								cellspacing="0" class="tableBasic">
							</table>
						</div>
							APK 包:
							<input type="file" id="file" name="file" class="inpFlie" accept="apk/*" placeholder="点击选择apk包" /><br>
							<br>
							更新说明：
							<textarea name="versionInfo" id="versionInfo" cols="50" rows="5" class="textArea"></textarea><br />
							<br />
							<input name="submit" class="btn" type="submit" value="提交" width="100%" class="inpMain">
						</form>
					</fieldset>
				</td>
				<td valign="top">
				<div class="pager" style="background-color: lightgray;vertical-align: center;">
						总计 ${pageBean.total}个记录，共 ${pageBean.totalPage } 页，当前第
						${pageBean.pageNo} 页 | <a
							href="<%=basePath%>version/getVersions.do?pageNo=1">第一页</a> <a
							href="<%=basePath%>subject/getVersions.do?pageNo=${pageBean.pageNo-1<1?1:pageBean.pageNo-1}">上一页</a>
						<a href="<%=basePath%>subject/getVersions.do?pageNo=${pageBean.pageNo+1>pageBean.totalPage?pageBean.totalPage:pageBean.pageNo+1}">下一页</a>
						<a href="<%=basePath%>subject/getVersions.do?pageNo=${pageBean.totalPage}">最末页</a>
					</div>
					<table style="width: 100%; border: 1px;" class="tableOnebor">
						<tr style="width: 100%; height: 30px">
							<td width="30" align="center">id</td>
							<td width="80" align="center">版本名称</td>
							<td width="50" align="center">版本号</td>
							<td width="50" align="center">渠道号</td>
							<td width="80" align="center">apk地址</td>
							<td width="80" align="center">更新说明</td>
							<td width="80" align="center">更新时间</td>
							<td width="80" align="center">操作</td>
						</tr>
						<c:forEach items="${versions }" var="version" varStatus="status">
							<tr>
								<td width="30" align="center">${version.id }</td>
								<td width="80" align="center">${version.version_name }</td>
								<td width="50" align="center">${version.version_code }</td>
								<td width="50" align="center">${version.channel_id }</td>
								<td width="80" style="max-width: 200px" align="center"><a href=" ${version.url }">
								<textarea rows="5" cols="25">${version.url }</textarea>
								</a></td>
								<td width="80" align="center">
								<textarea rows="5" cols="25">${version.version_info }</textarea>
								 </td>
								<td width="80" align="center">${version.dateTime }</td>
								<td width="80" align="center"><a
									href="<%=basePath %>version/del.do?id=${version.id }">
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