package com.main.model.mobile.response;

import com.main.model.App;
import com.main.model.mobile.BaseResponse;
import com.main.model.mobile.request.AppDetailsReq;

/**
 * Created by strike on 16/6/29.
 */
public class AppDetailsRsp extends BaseResponse {
	public AppDetailsRsp(AppDetailsReq req) {
		super(req);
	}
	public AppDetailsRsp() {
	}
    public ResultData resultData = new ResultData();

    public App getApp(){
        return resultData.app;
    }
    public class ResultData {
        public App app = new App();
    }
}
