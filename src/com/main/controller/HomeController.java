package com.main.controller;

import java.util.List;

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
	
	@RequestMapping(value="delHomeBean.do")
	public String delHomeBean(HttpServletRequest request){
		String idstr = request.getParameter("id");
		int id = NumberUtil.parseToInt(idstr);
		if (id!= -1) {
			if (service.deleteHomeBean(id)) {
				request.setAttribute("msg", "删除成功！");
			}else{
				request.setAttribute("msg", "删除失败！");
			}
		}
		PageBean pageBean = getPageBean(1, Constance.DEFALT_PAGESIZE);
		List<HomeBean> beans = getBeans(pageBean.pageNo, pageBean.pageSize, -1);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("beans", beans);
		return "homeEdit";
	}
	//获取分页对象
	private PageBean getPageBean(int pageNO,int pageSize){
		PageBean pageBean = new PageBean();
		pageBean.pageNo = pageNO;
		pageBean.pageSize = pageSize;
		pageBean.total = service.getTotalHomeBean(-1);
		pageBean.totalPage = pageBean.total % pageBean.pageSize == 0
				?pageBean.total/pageBean.pageSize:pageBean.total/pageBean.pageSize+1;
		return pageBean;
	}
	//分页获取首页元素的集合
	private List<HomeBean> getBeans(int pageNo,int pageSize,int type){
		List<HomeBean> beans=service.getList(pageNo, pageSize, type);
		if (beans!= null && beans.size()>0) {
			for(HomeBean bean:beans){//查询出app图标
				bean.setApps(appService.getAppByAppIdStr(bean.getAppIds()));
			}
		}
		return beans;
	}
	//获取首页列表
	@RequestMapping(value="iniHomePage.do")
	public String initHomePage(HttpServletRequest request){
		String no = request.getParameter("pageNo") == null?"":request.getParameter("pageNo");
		String size = request.getParameter("pageSize") == null?"":request.getParameter("pageSize");
		PageBean pageBean = getPageBean("".equals(no)?1:Integer.parseInt(no), "".equals(size)?Constance.DEFALT_PAGESIZE:Integer.parseInt(size));
		List<HomeBean> beans = getBeans(pageBean.pageNo, pageBean.pageSize,-1);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("beans", beans);
		return "homeEdit";
	}
	//添加首页元素
	@RequestMapping(value="addHomeBean.do")
	public String addHomBean(HttpServletRequest request,HttpServletResponse response){
		String type = request.getParameter("homeBeanType");
		String title = request.getParameter("homeBeanTitle");
		String idString = request.getParameter("appids");
		if (NumberUtil.parseToInt(type)==HomeBean.TYPE_NOMAL) {
			idString = TextUtils.sbuByLength(idString, ",", 5);
		}else if (NumberUtil.parseToInt(type)==HomeBean.TYPE_RECOMMEND) {
			idString = TextUtils.sbuByLength(idString, ",", 3);
		}
		HomeBean bean = new HomeBean();
		if (type!=null && title != null && idString != null) {
			bean.setHomeBeanTitle(title);
			bean.setHomeBeanType(NumberUtil.parseToInt(type));
			bean.setAppIds(idString);
			bean.setSort(0);
			bean.setUpdateTime(TimeUtil.longToDateStr(System.currentTimeMillis(), null));
			if (service.addHomeBean(bean)) {
				request.setAttribute("msg", "添加成功");
			}else{
				request.setAttribute("msg", "添加失败,请重试");
			}
		}else{
			request.setAttribute("msg", "参数错误！");
		}
		List<HomeBean> beans = getBeans(0, 10,-1);
		PageBean pageBean = getPageBean(1, Constance.DEFALT_PAGESIZE);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("beans", beans);
		return "homeEdit";
	}
}
