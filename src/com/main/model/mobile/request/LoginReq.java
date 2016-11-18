package com.main.model.mobile.request;

import com.main.model.mobile.BaseRequest;

/**
 * Created by strike on 16/6/3.
 */
public class LoginReq extends BaseRequest {
	public RequestParam requestParams;

	public LoginReq() {
	}

	public LoginReq(String name, String password) {
		cmdType = "userService";
		methodName = "login";
		requestParams = new RequestParam(name, password);
	}

	public class RequestParam {
		public String username;
		public String password;

		public RequestParam() {
		}

		public RequestParam(String name, String pass) {
			username = name;
			password = pass;
		}
	}
}
