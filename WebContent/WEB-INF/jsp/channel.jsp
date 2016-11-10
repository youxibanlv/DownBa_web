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
	function del(src) {
		$(src).parent().remove();
	}
	//提交前验证输入
	function check() {
		var channelName = $("#channelName ").val();
		var channelDes = $("#channelDes").val();
		var channelId = $("#channelId").val();
		 if (channelName == "") {
			alert("请输入渠道名称")
			return false;
		} else if(channelDes == ""){
			alert("请输入渠道描述")
			return false;
		}else if(channelId == ""){
			alert("请输入渠道号")
			return false;
		}
	}
</script>
<div id="dcMain">
	<!-- 当前位置 -->
	<div id="urHere">
		下吧市场管理中心<b></b><strong>渠道管理</strong>
	</div>
	<div class="mainBox imgModule">
		<table style="width: 100%; border: 1px" class="tableBasic">
			<tr>
				<th style="width: 40%; height: 40px"><font
					style="font-size: 20px">添加渠道</font></th>
				<th><font style="font-size: 20px">渠道列表</font></th>
			</tr>
			<tr>
				<td valign="top">
					<fieldset>
						<legend>
							<font color="red">添加渠道号</font>
						</legend>
						<form action="<%=basePath%>channel/add.do" onsubmit="return check();" enctype="multipart/form-data" method="post">
							渠道名称： 
							<input type="text" name="channelName" id="channelName" placeholder="输入渠道名称" class="inpMain" style="width: 255px" /><br />
							<br>
							渠 道 号 ： 
							<input type="text" name="channelId" id="channelId" placeholder="输入渠道号,只能输入数字" class="inpMain"
							style="width: 255px" onkeyup="if(event.keyCode !=37 && event.keyCode != 39){if (!/^[\d]+$/ig.test(this.value)){this.value='';}}"/><br />
							<br>
							渠道说明：
							<textarea name="channelDes" id="channelDes" cols="50" rows="5" class="textArea"></textarea><br />
							<br />
							<input name="submit" class="btn" type="submit" value="提交" width="100%" class="inpMain">
						</form>
						
					</fieldset>
				</td>
				<td valign="top">
				<div class="pager" style="background-color: lightgray;vertical-align: center;">
						总计 ${pageBean.total}个记录，共 ${pageBean.totalPage } 页，当前第
						${pageBean.pageNo} 页 | <a
							href="<%=basePath%>channel/getChannels.do?pageNo=1">第一页</a> <a
							href="<%=basePath%>channel/getChannels.do?pageNo=${pageBean.pageNo-1<1?1:pageBean.pageNo-1}">上一页</a>
						<a href="<%=basePath%>channel/getChannels.do?pageNo=${pageBean.pageNo+1>pageBean.totalPage?pageBean.totalPage:pageBean.pageNo+1}">下一页</a>
						<a href="<%=basePath%>channel/getChannels.do?pageNo=${pageBean.totalPage}">最末页</a>
					</div>
					<table style="width: 100%; border: 1px;" class="tableOnebor">
						<tr style="width: 100%; height: 30px">
							<td width="30" align="center">渠道号</td>
							<td width="80" align="center">渠道名称</td>
							<td width="100" align="center">渠道说明</td>
							<td width="30" align="center">下载量</td>
							<td width="50" align="center">更新时间</td>
							<td width="80" align="center">操作</td>
						</tr>
						<c:forEach items="${channels }" var="channel" varStatus="status">
							<tr>
								<td width="30" align="center">${channel.channel_id }</td>
								<td width="80" align="center">${channel.channel_name }</td>
								<td width="100" align="center">${channel.bak }</td>
								<td width="50" align="center">${channel.downloads }</td>
								<td width="50" align="center">${channel.dateTime }</td>
								<td width="80" align="center"><a
									href="<%=basePath %>channel/del.do?id=${channel.channel_id }">
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