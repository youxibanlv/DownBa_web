package com.main.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.main.model.App;
import com.main.model.PageBean;
import com.main.model.Subject;
import com.main.service.IAppService;
import com.main.service.ISubjectService;
import com.main.utils.Constance;
import com.main.utils.HttpUtils;
import com.main.utils.NumberUtil;
import com.main.utils.TimeUtil;

import gson.Gson;

@Controller
@RequestMapping(value="subject")
public class SubjectController {
	@Autowired
	private ISubjectService service;
	@Autowired
	private IAppService appService;
	
	@RequestMapping(value="getBeanByName.do")
	public void getBeanByName(HttpServletRequest request,HttpServletResponse response){
		String title = request.getParameter("appName");
		List<Subject> apps = service.getListByTitle(title);
		Map<String, Object> map = new HashMap<>();
		if (apps!= null && apps.size()>0) {
			for(Subject app:apps){
				app.setLogo(app.getLogo());
			}
			map.put("resultCode", 0);
			map.put("data", apps);
		}else{
			map.put("resultCode", 1);
			map.put("errorMsg", "没有查询到相关专题，请重新输入");
		}
		Gson gson = new Gson();
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();
			writer.write(gson.toJson(map));
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="del.do")
	public String del(HttpServletRequest request){
		Integer id = NumberUtil.parseToInt(request.getParameter("id"));
		if (id!=null) {
			Subject subject = service.getBeanById(id);
			if (subject != null) {
				HttpUtils.delFromServer(request, subject.getLogo());
			}
			if (service.delete(id)) {
				request.setAttribute("msg", "删除成功");
			}else{
				request.setAttribute("msg", "删除失败");
			}
		}
		setBeans(request, 1, Constance.DEFALT_PAGESIZE);
		return "subject";
	}
	
	@RequestMapping(value="getList.do")
	public String getList(HttpServletRequest request){
		int pageNo = NumberUtil.parseToInt(request.getParameter("pageNo")) == null?1:NumberUtil.parseToInt(request.getParameter("pageNo"));
		int pageSize = NumberUtil.parseToInt(request.getParameter("pageSize"))==null?Constance.DEFALT_PAGESIZE:NumberUtil.parseToInt(request.getParameter("pageSize"));
		setBeans(request, pageNo, pageSize);
		return "subject";
	}
	
	@RequestMapping(value="add.do")
	public String add(HttpServletRequest request,@RequestParam(value="subjectLogo",required=false) MultipartFile file){
		String title = request.getParameter("subjectTitle");
		String appids = request.getParameter("appids");
		String sort = request.getParameter("sort");
		String subjectDes = request.getParameter("subjectDes");
		
		String logo = HttpUtils.uploadFile(request, file);
		Subject subject = new Subject();
		if (logo!= null) {
			subject.setLogo(logo);
		}
		if (title!=null) {
			subject.setTitle(title);
		}
		if (appids != null) {
			subject.setIdString(appids);
		}
		if (sort !=null) {
			subject.setSort(NumberUtil.parseToInt(sort));
		}
		if (subjectDes != null) {
			subject.setDes(subjectDes);
		}
		subject.setUpdateTime(System.currentTimeMillis());
		if (service.add(subject)) {
			request.setAttribute("msg", "添加成功");
			
		}else{
			request.setAttribute("msg", "添加失败");
		}
		setBeans(request, 1, Constance.DEFALT_PAGESIZE);
		return "subject";
	}
	
	//分页获取首页元素的集合
	private void setBeans(HttpServletRequest request,int pageNo,int pageSize){
		List<Subject> beans=service.getList(pageNo, pageSize);
		if (beans!= null && beans.size()>0) {
			for(Subject bean:beans){//查询出app图标
				bean.setDateTime(TimeUtil.longToDateStr(bean.getUpdateTime(), null));
				bean.setApps(appService.getAppByAppIdStr(bean.getIdString()));
			}
			request.setAttribute("subjects", beans);
		}else{
			request.setAttribute("msg", "没有查询到专题信息！");
		}
		HttpUtils.setPageBean(request, 1, Constance.DEFALT_PAGESIZE, service.getTotal());
	}
}
