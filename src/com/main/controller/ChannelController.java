package com.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.main.model.Channel;
import com.main.service.IChannelService;
import com.main.utils.Constance;
import com.main.utils.HttpUtils;
import com.main.utils.NumberUtil;
import com.main.utils.TextUtils;
import com.main.utils.TimeUtil;

@Controller
@RequestMapping(value = "channel")
public class ChannelController {
	@Autowired
	private IChannelService service;
	
	@RequestMapping(value="showChannels.do")
	public void showChannels(HttpServletRequest request,HttpServletResponse response){
		int pageNo = NumberUtil.parseToInt(request.getParameter("pageNo")) == null ? 1: NumberUtil.parseToInt(request.getParameter("pageNo"));
		int pageSize = NumberUtil.parseToInt(request.getParameter("pageSize")) == null ? Constance.DEFALT_PAGESIZE: NumberUtil.parseToInt(request.getParameter("pageSize"));
		List<Channel> channels = service.getList(pageNo, pageSize);
		Map<String, Object> map = new HashMap<>();
		if (channels!=null && channels.size()>0) {
			map.put("resultCode", 0);
			map.put("data", channels);
		}else{
			map.put("resultCode", 1);
			map.put("errorMsg", "没有查询到相关应用，请重新输入");
		}
		
		HttpUtils.sendToAjax(response, map);
	}
	
	@RequestMapping(value="del.do")
	public String del(HttpServletRequest request){
		Integer id = NumberUtil.parseToInt(request.getParameter("id"));
		if (id!=null && service.delete(id)) {
			request.setAttribute("msg", "删除成功");
		}else{
			request.setAttribute("msg", "删除失败");
		}
		setBeans(request, 1, Constance.DEFALT_PAGESIZE);
		HttpUtils.setPageBean(request, 1, Constance.DEFALT_PAGESIZE, service.getTotal());
		return "channel";
	}
	@RequestMapping(value = "getChannels.do")
	public String getChannels(HttpServletRequest request) {
		int pageNo = NumberUtil.parseToInt(request.getParameter("pageNo")) == null ? 1
				: NumberUtil.parseToInt(request.getParameter("pageNo"));
		int pageSize = NumberUtil.parseToInt(request.getParameter("pageSize")) == null ? Constance.DEFALT_PAGESIZE
				: NumberUtil.parseToInt(request.getParameter("pageSize"));
		setBeans(request, pageNo, pageSize);
		HttpUtils.setPageBean(request, pageNo, pageSize, service.getTotal());
		return "channel";
	}

	@RequestMapping(value = "add.do")
	public String add(HttpServletRequest request) {
		String channelName = request.getParameter("channelName");
		String channelDes = request.getParameter("channelDes");
		String channelId = request.getParameter("channelId");
		if (TextUtils.isEmpty(channelName) || TextUtils.isEmpty(channelDes)||TextUtils.isEmpty(channelId)) {
			request.setAttribute("msg", "请求参数错误");
			return "channel";
		}
		Channel channel = service.getBeanById(channelId);
		if (channel == null) {
			channel = new Channel();
			channel.setBak(channelDes);
			channel.setChannel_id(channelId);
			channel.setChannel_name(channelName);
			channel.setUpdate_time(System.currentTimeMillis());
			if (service.add(channel)) {
				request.setAttribute("msg", "添加渠道成功");
			} else {
				request.setAttribute("msg", "添加渠道失败");
			}
		}else{
			request.setAttribute("msg", "渠道号已存在");
		}
		setBeans(request, 1, Constance.DEFALT_PAGESIZE);
		HttpUtils.setPageBean(request, 1, Constance.DEFALT_PAGESIZE, service.getTotal());
		return "channel";
	}

	// 分页获取元素的集合
	private void setBeans(HttpServletRequest request,int pageNo, int pageSize) {
		List<Channel> beans = service.getList(pageNo, pageSize);
		if (beans != null && beans.size() > 0) {
			for (Channel bean : beans) {// 查询出app图标
				bean.setDateTime(TimeUtil.longToDateStr(bean.getUpdate_time(), null));
			}
			request.setAttribute("channels", beans);
		}else {
			request.setAttribute("msg", "没有查询到渠道信息！");
		}
	}
}
