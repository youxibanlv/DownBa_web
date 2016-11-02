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
	
	public String updateRecommend(HttpServletRequest request){
		
		return "wheelPage";
	}

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
			if ( 1==recommend.getRecommend_type()) {
				return "wheelPage";
			}else if (2==recommend.getRecommend_type()) {
				return "recommend";
			}else if (3==recommend.getRecommend_type()) {
				return "suspect";
			}
		}else {
			request.setAttribute("msg", "广告id为空");
		}
		return"index";
	}
	/**
	 * 添加广告位
	 * **/
	@RequestMapping(value="addRecommend.do")
	public String  addRecommend(HttpServletRequest request,@RequestParam(value="recommend_logo",required=false) MultipartFile file) {
		String path = request.getSession().getServletContext().getRealPath("userUpload");
		String name = null;
		String logoPath = null;
		Recommend recommend = new Recommend();
		
		if (file != null) {//有上传图片
			String fileName = file.getOriginalFilename();
		    name = System.currentTimeMillis()+fileName.substring(fileName.lastIndexOf("."),fileName.length());
			File logo = new File(path,name);
			if (!logo.exists()) {
				logo.mkdirs();
			}
			try {
				file.transferTo(logo);
			} catch (Exception e) {
			}
			String contextPath = request.getContextPath();
			logoPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ contextPath + "/";
			recommend.setRecommend_logo(logoPath+"userUpload/"+name);
		}else{
			recommend.setRecommend_logo(request.getParameter("recommend_logo")==null?"":request.getParameter("recommend_logo"));
		}
		
		String recommend_type = request.getParameter("recommend_type")==null?"":request.getParameter("recommend_type");
		String recommend_title = request.getParameter("recommend_title")==null?"":request.getParameter("recommend_title");
		String appId = request.getParameter("appId")==null?"":request.getParameter("appId");
		String sort = request.getParameter("sort") == null?"":request.getParameter("sort");
		
		recommend.setRecommend_type(Integer.parseInt(recommend_type));
		recommend.setRecommend_title(recommend_title);
		recommend.setSort(NumberUtil.parseToInt(sort));
		recommend.setAppId(appId);
		recommend.setUpdate_time(TimeUtil.longToDateStr(System.currentTimeMillis(), "yyyy年MM月dd日hh:mm"));
		if (service.addRecommend(recommend)) {
			request.setAttribute("msg", "添加成功！");
			List<Recommend> list = service.getRecommendListByType(recommend.getRecommend_type());
			if (list!= null) {
				request.setAttribute("recommends", list);
			}
		}
		if (1==recommend.getRecommend_type()) {
			return "wheelPage";
		}else if (2== recommend.getRecommend_type()) {
			return "recommend";
		}else if (3 == recommend.getRecommend_type()) {
			return "suspect";
		}
		return "index";
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
		if ("1".equals(recommend_type)) {
			return "wheelPage";
		}else if ("2".equals(recommend_type)) {
			return "recommend";
		}else if ("3".equals(recommend_type)) {
			return "suspect";
		}
		return "index";
	}
}
