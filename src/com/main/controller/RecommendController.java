package com.main.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.main.model.Recommend;
import com.main.service.IRecommendService;

@Controller
@RequestMapping(value ="recommend")
public class RecommendController {
	@Autowired
	private IRecommendService service;

	/**
	 * 添加广告位
	 * **/
	@RequestMapping(value="addRecommend.do")
	public String  addRecommend(HttpServletRequest request) {
		Recommend recommend = new Recommend();
		String recommend_type = request.getParameter("recommend_type")==null?"":request.getParameter("recommend_type");
		String recommend_title = request.getParameter("recommend_title")==null?"":request.getParameter("recommend_title");
		String recommend_logo = request.getParameter("recommend_logo")==null?"":request.getParameter("recommend_logo");
		String appId = request.getParameter("appId")==null?"":request.getParameter("appId");
		recommend.setRecommend_type(Integer.parseInt(recommend_type));
		recommend.setRecommend_logo(recommend_logo);
		recommend.setRecommend_title(recommend_title);
		recommend.setAppId(appId);
		service.addRecommend(recommend);
		return "";
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
			List<Recommend> list = service.getRecommendList(Integer.parseInt(recommend_type));
			if (list!= null) {
				request.setAttribute("recommends", list);
			}
		}
		return "wheelPage";
	}
}
