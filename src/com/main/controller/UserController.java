package com.main.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.main.model.User;
import com.main.service.IUserService;
import com.main.service.impl.UserServiceImpl;


@Controller
@RequestMapping(value ="/user")
public class UserController {
	@Autowired
	private IUserService userService;
	
	/**
	 * 获取所有用户列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAllUser")
	public String getAllUsers(HttpServletRequest request){
		List<User> list = userService.findAll();
		request.setAttribute("userList", list);
		System.out.println("=========");
		return "allUser";
	}
}
