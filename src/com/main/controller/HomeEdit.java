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

import com.main.model.HomeBean;
import com.main.model.PageBean;
import com.main.service.IAppService;
import com.main.service.IHomeBeanService;
import com.main.utils.Constance;
import com.main.utils.NumberUtil;
import com.main.utils.TextUtils;
import com.main.utils.TimeUtil;

import gson.Gson;

@Controller
@RequestMapping(value="home")
public class HomeEdit {
	private static Logger log=LoggerFactory.getLogger(HomeEdit.class);//日志记录
	@Autowired
	private IHomeBeanService service;
	@Autowired
	private IAppService appService;
	
	private Gson gson = new Gson();
	
	@RequestMapping(value="iniHomePage.do")
	public String initHomePage(HttpServletRequest request){
		String no = request.getParameter("pageNo") == null?"":request.getParameter("pageNo");
		String size = request.getParameter("pageSize") == null?"":request.getParameter("pageSize");
		Map<String, Object> map = new HashMap<>();
		PageBean pageBean = new PageBean();
		pageBean.pageNo ="".equals(no)?1:Integer.parseInt(no);
		pageBean.pageSize ="".equals(size)?Constance.DEFALT_PAGESIZE:Integer.parseInt(size);
		List<HomeBean> beans = service.getList(0, 10,-1);
		if (beans != null && beans.size()>0) {
			for(HomeBean bean:beans){//查询出app图标
				bean.setApps(appService.getAppByAppIdStr(bean.getAppIds()));
			}
		}
		map.put("beans", beans);
		 pageBean.total = service.getTotalHomeBean(-1);
		pageBean.totalPage = pageBean.total % pageBean.pageSize == 0
				?pageBean.total/pageBean.pageSize:pageBean.total/pageBean.pageSize+1;
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("beans", beans);
		return "homeEdit";
	}
	@RequestMapping(value="addHomeBean.do")
	public void addHomBean(HttpServletRequest request,HttpServletResponse response){
		String type = request.getParameter("homeBeanType");
		String title = request.getParameter("homeBeanTitle");
		String idString = request.getParameter("appids");
		if (NumberUtil.parseToInt(type)==HomeBean.TYPE_NOMAL) {
			idString = TextUtils.sbuByLength(idString, ",", 5);
		}else if (NumberUtil.parseToInt(type)==HomeBean.TYPE_RECOMMEND) {
			idString = TextUtils.sbuByLength(idString, ",", 3);
		}
		HomeBean bean = new HomeBean();
		Map<String, Object> map = new HashMap<>();
		if (type!=null && title != null && idString != null) {
			bean.setHomeBeanTitle(title);
			bean.setHomeBeanType(NumberUtil.parseToInt(type));
			bean.setAppIds(idString);
			bean.setUpdateTime(TimeUtil.longToDateStr(System.currentTimeMillis(), null));
			if (service.addHomeBean(bean)) {
				map.put("success", true);
				map.put("msg", "添加成功");
			}else{
				map.put("success", false);
				map.put("msg", "添加失败,请重试");
			}
		}else{
			map.put("success", false);
			map.put("msg", "参数错误！");
		}
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
