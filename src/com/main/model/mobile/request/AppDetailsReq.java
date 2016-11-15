package com.main.model.mobile.request;

import com.main.model.mobile.BaseRequest;

/**
 * Created by strike on 16/6/29.
 */
public class AppDetailsReq extends BaseRequest {
    public RequestParam requestParams ;
    public AppDetailsReq() {
	}
    public AppDetailsReq(String app_id){
        cmdType = "appService";
        methodName = "getAppDetails";
        requestParams = new RequestParam(app_id);
    }

    public class RequestParam{
    	public RequestParam() {
		}
        public String app_id;
        public RequestParam(String app_id){
            this.app_id = app_id;
        }
    }
}
