package com.main.controller.mobile.user;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.main.model.User;
import com.main.model.mobile.BaseResponse;
import com.main.model.mobile.HttpConstance;
import com.main.model.mobile.request.LoginReq;
import com.main.model.mobile.request.RegisterReq;
import com.main.model.mobile.request.UpdateUserReq;
import com.main.model.mobile.request.UploadUserIconReq;
import com.main.model.mobile.response.LoginRsp;
import com.main.model.mobile.response.UserRsp;
import com.main.service.IUserService;
import com.main.utils.HttpUtils;

@Controller
@RequestMapping(value="userService")
public class UserService {
	
	@Autowired
	private IUserService service;
	//上传头像
	@RequestMapping(value="uploadIcon.do")
	public void uploadIcon(@RequestParam(value="file") MultipartFile file,HttpServletRequest request,HttpServletResponse response){
		UserRsp rsp =  new UserRsp();
		if (file != null) {
			rsp.cmdType = request.getParameter("cmdType");
			rsp.methodName = request.getParameter("methodName");
			String uid = request.getParameter("user");
			User user = service.findById(Integer.parseInt(uid));
			if (user != null) {
				String icon = HttpUtils.uploadFile(request, file);
				if (icon != null) {
					user.setIcon(icon);
					rsp.result = HttpConstance.HTTP_SUCCESS;
					rsp.resultData = user; 
				}else {
					rsp.failReason="上传图片失败";
				}
			}else{
				rsp.failReason="用户id错误";
			}
		}else{
			rsp.failReason = "请求参数错误";
		}
		HttpUtils.sendRsp(response, rsp);
	}
	//更新
	@RequestMapping(value="updateUser.do")
	public void updateUser(@RequestBody UpdateUserReq req,HttpServletResponse response){
		UserRsp rsp = null;
		if (req != null) {
			rsp = new UserRsp(req);
			User user = req.requestParams.user;
			if (user!= null) {
				if (service.update(user)) {
					rsp.result=HttpConstance.HTTP_SUCCESS;
					rsp.resultData = user;
				}else{
					rsp.failReason = "更新失败！";
				}
			}else {
				rsp.failReason = "请求参数错误";
			}
			
		}else{
			rsp = new UserRsp();
			rsp.failReason = "请求参数错误";
		}
		HttpUtils.sendRsp(response, rsp);
	}
	//注册
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
	//登录
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
