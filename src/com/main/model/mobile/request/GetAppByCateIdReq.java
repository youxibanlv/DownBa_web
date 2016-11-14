package com.main.model.mobile.request;

import com.main.model.mobile.BaseRequest;

/**
 * Created by strike on 16/7/3.
 */
public class GetAppByCateIdReq extends BaseRequest {
	public GetAppByCateIdReq(){}
	public RequestParam requestParams;
    public GetAppByCateIdReq(int cate_id,int orderType,int pageNo,int pageSize){
        cmdType = "appService";
        methodName = "getAppListByCate";
        requestParams = new RequestParam(cate_id,orderType,pageNo,pageSize);
    }
    public class RequestParam{
        public int cate_id;
        public int orderType;
        public int pageNo;
        public int pageSize;
        public RequestParam(){}
        public RequestParam(int cate_id,int orderType,int pageNo,int pageSize){
            this.cate_id = cate_id;
            this.orderType = orderType;
            this.pageNo = pageNo;
            this.pageSize = pageSize;
        }
    }
}
