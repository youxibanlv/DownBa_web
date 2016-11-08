package com.main.model.mobile.request;

import com.main.model.mobile.BaseRequest;

/**
 * Created by strike on 16/6/6.
 */
public class HomeBeanReq extends BaseRequest {
   public HomeBeanReq(){}
   public RequestParam requestParams;

    public HomeBeanReq(int pageNo,int pageSize){
        cmdType = "appService";
        methodName = "getHomeBeans";
        requestParams = new RequestParam(pageNo,pageSize);
    }
    public class RequestParam{
       public int pageNo,pageSize;
       public RequestParam(){}
        public RequestParam(int pageNo,int pageSize){
            this.pageNo = pageNo;
            this.pageSize = pageSize;
        }
    }
}
