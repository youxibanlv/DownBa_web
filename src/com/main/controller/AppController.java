package com.main.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.main.model.App;
import com.main.service.IAppService;

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
		String no = request.getParameter("pageno") == null?"":request.getParameter("pageno");
		String size = request.getParameter("pagesize") == null?"":request.getParameter("pagesize");
		int pageno = 0, pagesize =10;
		if (!"".equals(no)) {
			pageno = Integer.parseInt(no);
		}
		if (!"".equals(size)) {
			pagesize = Integer.parseInt(size);
		}
		List<App> apps = appService.getAppList(pageno, pagesize);
		if (apps !=null && apps.size()>0) {
			request.setAttribute("apps", apps);
		}
		return "item_list";
	}
}
