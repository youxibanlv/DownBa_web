package com.main.model.mobile.request;

import com.main.model.mobile.BaseRequest;

/**
 * Created by strike on 16/8/3.
 */
public class GetAppsByIdStringReq extends BaseRequest {

    public RequestParam requestParams = new RequestParam();
    public GetAppsByIdStringReq() {
	}
    public GetAppsByIdStringReq(String idList){
        cmdType = "appService";
        methodName = "getAppsByIdString";
        requestParams = new RequestParam(idList);
    }

    public class RequestParam{
        public String ids;
        public RequestParam() {
		}
        public RequestParam(String idList){
            this.ids = idList;
        }
    }
}
