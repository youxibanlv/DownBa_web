package com.main.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.main.model.Admin;
import com.main.service.IAdminService;
import com.main.utils.MD5Util;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	private static Logger log=LoggerFactory.getLogger(AdminController.class);//日志记录
	
	@Resource
	private IAdminService adminService;
	
	/**
	 * 管理员登录
	 * 
	 * **/
	@RequestMapping(value ="/login.do",method = RequestMethod.POST)
	public String login(HttpServletRequest request,Model model){
		String account = request.getParameter("account");
		String password = MD5Util.getMd5(request.getParameter("password"));
		Admin admin = adminService.login(account, password);
		if (admin != null) {
			String nickName = "".equals(admin.getNickName())?account:admin.getNickName();
			request.getSession().setAttribute("nickName", nickName);
			return "index";
		}else {
			request.getSession().setAttribute("msg", "账号或密码错误，请重新输入");
			return "error";
		}
	}
}
