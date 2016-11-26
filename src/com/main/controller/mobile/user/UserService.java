package com.main.controller.mobile.user;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.main.model.User;
import com.main.model.mobile.BaseResponse;
import com.main.model.mobile.HttpConstance;
import com.main.model.mobile.request.LoginReq;
import com.main.model.mobile.request.RegisterReq;
import com.main.model.mobile.response.LoginRsp;
import com.main.service.IUserService;
import com.main.utils.HttpUtils;
import com.sun.xml.internal.bind.v2.TODO;

@Controller
@RequestMapping(value="userService")
public class UserService {
	
	@Autowired
	private IUserService service;
	
	@RequestMapping(value = "register.do")
	public void register(@RequestBody RegisterReq req,HttpServletResponse response){
		BaseResponse rsp = null;
		if (req != null) {
			rsp = new BaseResponse(req);
			User user = service.getUserByName(req.requestParams.username);
			if (user != null) {
				rsp.failReason = "用户名已存在";
			}else{
				user = new User();
				user.setUsername(req.requestParams.username);
				user.setPassword(req.requestParams.password);
				user.setRegist_time(System.currentTimeMillis());
				if (service.save(user)) {
					rsp.result = 0;
				}else{
					//处理数据库异常
				}
			}
		}else{
			rsp = new BaseResponse();
			rsp.failReason = "请求参数错误";
		}
		HttpUtils.sendRsp(response, rsp);
	}
	
	@RequestMapping(value="login.do")
	public void login(@RequestBody LoginReq req,HttpServletResponse response){
		LoginRsp rsp;
		if (req != null) {
			rsp = new LoginRsp(req);
			User user = service.getUserByName(req.requestParams.username);
			if (user != null && user.getPassword().equals(req.requestParams.password)) {
				rsp.result = HttpConstance.HTTP_SUCCESS;
				user.setToken(HttpUtils.generateToken(user));
				service.update(user);
				rsp.resultData.user = user;
			}else{
				if (user == null) {
					rsp.failReason = "账号不存在";
				}else if (!user.getPassword().equals(req.requestParams.password)) {
					rsp.failReason = "密码错误";
				}
			}
			
		}else {
			rsp = new LoginRsp();
			rsp.failReason="请求参数错误";
		}
		HttpUtils.sendRsp(response, rsp);
	}
}
