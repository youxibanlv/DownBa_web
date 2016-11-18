package com.main.model.mobile.response;

import com.main.model.User;
import com.main.model.mobile.BaseResponse;
import com.main.model.mobile.request.LoginReq;

/**
 * Created by strike on 16/6/3.
 */
public class LoginRsp extends BaseResponse {
    public ResultData resultData = new ResultData();
    public LoginRsp(LoginReq req) {
    	super(req);
	}
    public LoginRsp() {
	}
    public class ResultData {
    	public User user;
    }
}
