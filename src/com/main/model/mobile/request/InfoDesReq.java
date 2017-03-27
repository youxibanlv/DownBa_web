package com.main.model.mobile.request;

import com.main.model.mobile.BaseRequest;

/**
 * Created by strike on 2017/2/19.
 */

public class InfoDesReq extends BaseRequest {

    public RequestParam requestParams;
    
    public InfoDesReq() {
	}

    public InfoDesReq(int id) {
        cmdType = "appService";
        methodName = "getInfoDes";
        requestParams = new RequestParam(id);
    }

    public class RequestParam{
        public int id;
        public RequestParam(int id){
            this.id = id;
        }
    }
}
