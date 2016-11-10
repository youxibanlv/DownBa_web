package com.main.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.main.model.Version;
import com.main.service.IVersionService;
import com.main.utils.Constance;
import com.main.utils.HttpUtils;
import com.main.utils.NumberUtil;
import com.main.utils.TextUtils;
import com.main.utils.TimeUtil;

@Controller
@RequestMapping(value="version")
public class VersionController {
	@Autowired
	private IVersionService service;
	
	@RequestMapping(value="getVersions.do")
	public String getVersions(HttpServletRequest request){
		int pageNo = NumberUtil.parseToInt(request.getParameter("pageNo")) == null ? 1
				: NumberUtil.parseToInt(request.getParameter("pageNo"));
		int pageSize = NumberUtil.parseToInt(request.getParameter("pageSize")) == null ? Constance.DEFALT_PAGESIZE
				: NumberUtil.parseToInt(request.getParameter("pageSize"));
		setBeans(request, pageNo, pageSize);
		HttpUtils.setPageBean(request, pageNo, pageSize, service.getTotal());
		return "version";
	}
	
	@RequestMapping(value="del.do")
	public String del(HttpServletRequest request){
		Integer id = NumberUtil.parseToInt(request.getParameter("id"));
		if (id != null) {
			Version version = service.getById(id);
			if (version !=null) {
				HttpUtils.delFromServer(request, version.getUrl());
			}
		}
		if (id!=null && service.delete(id)) {
			request.setAttribute("msg", "删除成功");
		}else{
			request.setAttribute("msg", "删除失败");
		}
		setBeans(request, 1, Constance.DEFALT_PAGESIZE);
		HttpUtils.setPageBean(request, 1, Constance.DEFALT_PAGESIZE, service.getTotal());
		return "version";
	}

	@RequestMapping(value = "add.do")
	public String add(HttpServletRequest request,@RequestParam(value="file",required=false) MultipartFile file) {
		String versionName = request.getParameter("versionName");
		String versionCode = request.getParameter("versionCode");
		String versionInfo = request.getParameter("versionInfo");
		String channelId = request.getParameter("channelId");
		String url = HttpUtils.uploadFile(request, file);
		if (TextUtils.isEmpty(versionName) || TextUtils.isEmpty(versionCode)||TextUtils.isEmpty(versionInfo)) {
			request.setAttribute("msg", "请求参数错误");
			return "version";
		}
		if (TextUtils.isEmpty(url)) {
			request.setAttribute("msg", "url错误");
			return "version";
		}
			Version verison = new Version() ;
			verison.setVersion_name(versionName);;
			verison.setVersion_code(NumberUtil.parseToInt(versionCode));;
			verison.setVersion_info(versionInfo);
			verison.setUrl(url);
			verison.setChannel_id(NumberUtil.parseToInt(channelId));
			verison.setUpdate_time(System.currentTimeMillis());
			if (service.add(verison)) {
				request.setAttribute("msg", "添加版本成功");
			} else {
				request.setAttribute("msg", "添加版本失败");
			}
		setBeans(request, 1, Constance.DEFALT_PAGESIZE);
		HttpUtils.setPageBean(request, 1, Constance.DEFALT_PAGESIZE, service.getTotal());
		return "version";
	}

	// 分页获取元素的集合
	private void setBeans(HttpServletRequest request,int pageNo, int pageSize) {
		List<Version> beans = service.getList(pageNo, pageSize);
		if (beans != null && beans.size() > 0) {
			for (Version bean : beans) {// 查询出app图标
				bean.setDateTime(TimeUtil.longToDateStr(bean.getUpdate_time(), null));
			}
			request.setAttribute("versions", beans);
		}else {
			request.setAttribute("msg", "没有查询到版本信息！");
		}
	}
}
