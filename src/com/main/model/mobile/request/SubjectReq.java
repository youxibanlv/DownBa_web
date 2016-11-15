package com.main.model.mobile.request;

import com.main.model.mobile.BaseRequest;

/**
 * Created by strike on 16/8/2.
 */
public class SubjectReq extends BaseRequest {

    public RequestParam requestParams;
    public SubjectReq() {
	}
    public SubjectReq(int pageNo,int pageSize) {
        cmdType = "appService";
        methodName = "getSubject";
        requestParams = new RequestParam(pageNo,pageSize);
    }
    public class RequestParam{
    	public int pageNo;
        public int pageSize;
        public RequestParam() {
		}
        public RequestParam(int pageNo,int pageSize){
            this.pageNo = pageNo;
            this.pageSize = pageSize;
        }
    }
}
