<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="nav.jsp"%>
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
					alert("error = "+ textStatus);
				},
				success : function(data, textStatus) {
					$("#appTable tr:gt(0)").remove();//删除所有行，第一行除外
					// 解析出data对应的Object数组 
					$.each(data, function(index, values) {
						$.each(values, function(index2, value) {
							$("#appTable").append("<tr><td>" + value.app_id+"</td><td>" +value.app_title+"</td></tr>"); //动态添加行
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
			$("#appTable").hide();//隐藏表格
		})
	};
	function wheelPageCheck(){
		var appid = $("#appid").val();
		var img = $("#show_img").val();
		var order = $("#sort").val();
		alert("appid = "+ appid+",img="+img+" order="+order)
		if(appid ==""){
			alert("请先选择应用");
			return false;
		}
		if(img == ""){
			alert("请先选择图片");
			return false;
		}
	};
</script>
<div id="dcMain">
	<!-- 当前位置 -->
	<div id="urHere">
		下吧市场管理中心<b>></b><strong>首页轮播图设置</strong>
	</div>
	<div class="mainBox imgModule">
		<h3 align="center">首页轮播图设置</h3>
		<!-- 侧轮播图列表 -->
		<div style="width: 100%; height: 100%">
			<fieldset style="width: 30%; height: 100%">
				<legend>
					<font color="red">添加轮播图</font>
				</legend>
				app名称：<input type="text" name="appName" value="" size="20" class="inpMain" id="appName" />
				<button style="width: 50px; height: 20px" onclick="getApp()">查询</button>
				<br />
				<br>
				<form action="<%=basePath%>recommend/addRecommend.do" method="post" enctype="multipart/form-data" onsubmit="return wheelPageCheck();">
					<input type="hidden" id="appid" name="appid">
					<table id="appTable" width="100%" border="0" cellpadding="8"cellspacing="0" class="tableBasic" hidden="true">
						<tr><th>appId</th><th>名称</th>
						</tr>
					</table>
					广告图片:<input type="file" id="show_img" class="inpFlie"accept="image/*" placeholder="建议使用png格式" /><br>
					<br> 
					排序：<input type="text" id="sort" value="5" size="20"
						class="inpMain" maxlength="1" pattern="[0-9]" /><br>
					<br>
						<input name="submit" class="btn" type="submit" value="提交"width="100%">
				</form>
			</fieldset>
		</div>
		<!-- 右侧轮播图列表 -->


		<!-- <table width="100%" border="0" cellpadding="8" cellspacing="0" class="tableBasic">
	    <tr>
	       <th>添加轮播图</th>
	       <th>轮播图列表</th>
	     </tr>
	     <tr>
	      <td width="350" valign="top">
	       <form action="show.php?rec=insert" method="post" enctype="multipart/form-data">
	        <table width="100%" border="0" cellpadding="8" cellspacing="0" class="tableOnebor">
	         <tr>
	          <td><b>app名称</b>
				<input type="text" name="show_name" value="" size="20" class="inpMain" id="appName"/>
				<button style="width: 50px ;height: 20px" onclick="getApp()">查询</button>
	          </td>
	         </tr>
	         <tr>
	          <td><b>幻灯图片</b>
	           <input type="file" name="show_img" class="inpFlie" />          </td>
	         </tr>
	         <tr>
	          <td><b>链接地址</b>
	           <input type="text" name="show_link" value="" size="40" class="inpMain" />
	          </td>
	         </tr>
	         <tr>
	          <td><b>排序</b>
	<input type="text" name="sort" value="50" size="20" class="inpMain" />
	          </td>
	         </tr>
	         <tr>
	          <td>
	                      <input type="hidden" name="token" value="79db104d" />
	           <input name="submit" class="btn" type="submit" value="提交" />
	          </td>
	         </tr>
	        </table>
	      </td>
	      <td valign="top">
	       <table width="100%" border="0" cellpadding="8" cellspacing="0" class="tableOnebor">
	        <tr>
	         <td width="100">幻灯名称</td>
	         <td></td>
	         <td width="50" align="center">排序</td>
	         <td width="80" align="center">操作</td>
	        </tr>
	                <tr>
	         <td><a href="http://www.weiqing.com/data/slide/20130514acunau.jpg" target="_blank"><img src="http://www.weiqing.com/data/slide/thumb/20130514acunau_thumb.jpg" width="100" /></a></td>
	         <td>广告图片01</td>
	         <td align="center">1</td>
	         <td align="center"><a href="editshow.html?id=1">编辑</a> | <a href="delshow.htmlid=1">删除</a></td>
	        </tr>
	                <tr>
	         <td><a href="http://www.weiqing.com/data/slide/20130514rjzqdt.jpg" target="_blank"><img src="http://www.weiqing.com/data/slide/thumb/20130514rjzqdt_thumb.jpg" width="100" /></a></td>
	         <td>广告图片02</td>
	         <td align="center">2</td>
	         <td align="center"><a href="editshow.html?id=2">编辑</a> | <a href="delshow.htmlid=2">删除</a></td>
	        </tr>
	                <tr>
	         <td><a href="http://www.weiqing.com/data/slide/20130514xxsctt.jpg" target="_blank"><img src="http://www.weiqing.com/data/slide/thumb/20130514xxsctt_thumb.jpg" width="100" /></a></td>
	         <td>广告图片03</td>
	         <td align="center">3</td>
	         <td align="center"><a href="editshow.html?id=3">编辑</a> | <a href="delshow.htmlid=3">删除</a></td>
	        </tr>
	                <tr>
	         <td><a href="http://www.weiqing.com/data/slide/20130523hiqafl.jpg" target="_blank"><img src="http://www.weiqing.com/data/slide/thumb/20130523hiqafl_thumb.jpg" width="100" /></a></td>
	         <td>广告图片04</td>
	         <td align="center">4</td>
	         <td align="center"><a href="editshow.html?id=4">编辑</a> | <a href="delshow.htmlid=4">删除</a></td>
	        </tr>
	               </table>
	      </td>
	     </tr>
	    </table> -->
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