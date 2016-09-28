package com.main.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.main.model.App;
import com.main.model.PageBean;
import com.main.service.IAppService;
import com.main.utils.Constance;

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
}
