package com.main.controller.mobile.user;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.main.model.User;
import com.main.model.mobile.HttpConstance;
import com.main.model.mobile.request.LoginReq;
import com.main.model.mobile.response.LoginRsp;
import com.main.service.IUserService;
import com.main.utils.HttpUtils;

@Controller
@RequestMapping(value="userService")
public class UserService {
	
	@Autowired
	private IUserService service;
	
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
