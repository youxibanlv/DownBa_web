package com.main.model.mobile.request;

import com.main.model.User;
import com.main.model.mobile.BaseRequest;

/**
 * Created by strike on 16/7/7.
 */
public class UpdateUserReq extends BaseRequest {
	public RequestParam requestParams;

	public UpdateUserReq() {
	}

	public UpdateUserReq(User user) {
		cmdType = "userService";
		methodName = "updateUser";
		requestParams = new RequestParam(user);
	}

	public class RequestParam {
		public User user;

		public RequestParam() {
		}

		public RequestParam(User user) {
			this.user = user;
		}
	}
}
