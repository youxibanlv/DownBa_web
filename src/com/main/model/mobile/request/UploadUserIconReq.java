package com.main.model.mobile.request;

import com.main.model.User;
import com.main.model.mobile.BaseRequest;

/**
 * Created by strike on 16/7/14.
 */
public class UploadUserIconReq extends BaseRequest {

	public RequestParam requestParams;

	public UploadUserIconReq() {
	}

	public UploadUserIconReq(String filePath, User user) {
		cmdType = "userService";
		methodName = "uploadIcon";
		requestParams = new RequestParam(filePath, user);
	}

	public class RequestParam {
		public String filePath;
		public User user;

		public RequestParam() {
		}

		public RequestParam(String filePath, User user) {
			this.filePath = filePath;
			this.user = user;
		}
	}
}
