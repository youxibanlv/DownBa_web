package com.main.model.mobile.request;

import com.main.model.mobile.BaseRequest;

/**
 * Created by strike on 16/8/4.
 */
public class InfoReq extends BaseRequest {
    public RequestParam requestParams;
    public InfoReq() {
	}
    public InfoReq(int pageNo,int pageSize, int cate_id) {
        cmdType = "appService";
        methodName = "getInfoList";
        requestParams = new RequestParam(pageNo,pageSize,cate_id);
    }
    public class RequestParam{
        public int pageNo;
        public int pageSize;
        public int cate_id;
        public RequestParam() {
		}
        public RequestParam(int pageNo,int pageSize, int cate_id){
            this.pageNo = pageNo;
            this.pageSize = pageSize;
            this.cate_id = cate_id;
        }
    }
}
