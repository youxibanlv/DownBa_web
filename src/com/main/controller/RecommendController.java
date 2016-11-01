package com.main.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.main.model.Recommend;
import com.main.service.IRecommendService;
import com.main.utils.NumberUtil;
import com.main.utils.TimeUtil;

@Controller
@RequestMapping(value ="recommend")
public class RecommendController {
	@Autowired
	private IRecommendService service;


	/**
	 * 删除广告位
	 * **/
	@RequestMapping(value="delRecommend.do")
	public String delRecommend(HttpServletRequest request){
		int id = NumberUtil.parseToInt(request.getParameter("recommend_id"));
		if (id != -1) {
			Recommend recommend = service.getRecommendById(id);
			String logoPath = recommend.getRecommend_logo();
			String fileName= null; //http://localhost:8080/DownBa_web/userUpload/1477970193551.png
			if (logoPath!=null) {
				fileName = logoPath.substring(logoPath.lastIndexOf("/")+1,logoPath.length());
				String path = request.getSession().getServletContext().getRealPath("userUpload");
				File file = new File(path,fileName);
				if (file.exists()) {
					file.delete();
				}
			}
			if (service.delRecommend(id)) {
				request.setAttribute("msg", "删除成功！");
			}else{
				request.setAttribute("msg", "删除失败！");
			}
			List<Recommend> list = service.getRecommendListByType(recommend.getRecommend_type());
			if (list!= null) {
				request.setAttribute("recommends", list);
			}
		}else {
			request.setAttribute("msg", "广告id为空");
		}
		return"wheelPage";
	}
	/**
	 * 添加广告位
	 * **/
	@RequestMapping(value="addRecommend.do")
	public String  addRecommend(HttpServletRequest request,@RequestParam(value="recommend_logo") MultipartFile file) {
		String path = request.getSession().getServletContext().getRealPath("userUpload");
		String fileName = file.getOriginalFilename();
		String name = System.currentTimeMillis()+fileName.substring(fileName.lastIndexOf("."),fileName.length());
		File logo = new File(path,name);
		if (!logo.exists()) {
			logo.mkdirs();
		}
		try {
			file.transferTo(logo);
		} catch (Exception e) {
		}
		
		String recommend_type = request.getParameter("recommend_type")==null?"":request.getParameter("recommend_type");
		String recommend_title = request.getParameter("recommend_title")==null?"":request.getParameter("recommend_title");
		String appId = request.getParameter("appId")==null?"":request.getParameter("appId");
		String sort = request.getParameter("sort") == null?"":request.getParameter("sort");
		String contextPath = request.getContextPath();
		String logoPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ contextPath + "/";
		
		Recommend recommend = new Recommend();
		recommend.setRecommend_type(Integer.parseInt(recommend_type));
		recommend.setRecommend_logo(logoPath+"userUpload/"+name);
		recommend.setRecommend_title(recommend_title);
		recommend.setSort(NumberUtil.parseToInt(sort));
		recommend.setAppId(appId);
		recommend.setUpdate_time(TimeUtil.longToDateStr(System.currentTimeMillis(), "yyyy年MM月dd日hh:mm"));
		if (service.addRecommend(recommend)) {
			request.setAttribute("msg", "更新成功！");
			List<Recommend> list = service.getRecommendListByType(recommend.getRecommend_type());
			if (list!= null) {
				request.setAttribute("recommends", list);
			}
		}
		return "wheelPage";
	}
	/**
	 * 根据类型查询广告位
	 * **/
	@RequestMapping(value="getRecommendList.do")
	public String getRecommend(HttpServletRequest request){
		String recommend_type = request.getParameter("recommend_type");
		if (recommend_type == null || "".equals(recommend_type)) {
			request.setAttribute("msg", "广告类型不能为空");
			return "error";
		}else{
			List<Recommend> list = service.getRecommendListByType(Integer.parseInt(recommend_type));
			if (list!= null) {
				request.setAttribute("recommends", list);
			}
		}
		return "wheelPage";
	}
}
