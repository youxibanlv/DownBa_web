package com.main.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.main.model.App;
import com.main.model.PageBean;
import com.main.service.IAppService;
import com.main.utils.Constance;

import gson.Gson;


@Controller
@RequestMapping(value ="app")
public class AppController {
	private static Logger log=LoggerFactory.getLogger(AdminController.class);//日志记录
	@Autowired
	private IAppService appService;
	
	/**
	 * 分页获取app列表
	 * **/
	@RequestMapping(value = "getAppList.do")
	public String getAppList(HttpServletRequest request) {
		String no = request.getParameter("pageNo") == null?"":request.getParameter("pageNo");
		String size = request.getParameter("pageSize") == null?"":request.getParameter("pageSize");
		PageBean pageBean = new PageBean();
		pageBean.pageNo ="".equals(no)?1:Integer.parseInt(no);
		pageBean.pageSize ="".equals(size)?Constance.DEFALT_PAGESIZE:Integer.parseInt(size);
		List<App> apps = appService.getAppList((pageBean.pageNo-1)*pageBean.pageSize, pageBean.pageSize);
		if (apps !=null && apps.size()>0) {
			pageBean.total = getTotalApps();
			pageBean.totalPage = pageBean.total % pageBean.pageSize == 0
					?pageBean.total/pageBean.pageSize:pageBean.total/pageBean.pageSize+1;
			System.out.println("分页信息："+pageBean.toString());
			request.setAttribute("pageBean", pageBean);
			request.setAttribute("apps", apps);
		}
		return "app";
	}
	
	public int  getTotalApps() {
		return appService.getTotalApp();
	}
	@RequestMapping(value="getAppByName.do")
	public void getAppByName(HttpServletRequest request,HttpServletResponse response){
		String appName = request.getParameter("appName");
		List<App> apps = appService.getAppListByAppName(appName);
		Map<String, Object> map = new HashMap<>();
		if (apps!= null && apps.size()>0) {
			map.put("success", true);
			map.put("data", apps);
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
}
