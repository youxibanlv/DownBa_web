package com.main.model.mobile.response;

import com.main.model.User;
import com.main.model.mobile.BaseResponse;
import com.main.model.mobile.request.UpdateUserReq;
import com.main.model.mobile.request.UploadUserIconReq;

/**
 * Created by strike on 16/7/7.
 */
public class UserRsp extends BaseResponse {
	public User resultData;

	public UserRsp(UpdateUserReq req) {
		super(req);
	}

	public UserRsp(UploadUserIconReq req) {
		super(req);
	}

	public UserRsp() {
	}
}
