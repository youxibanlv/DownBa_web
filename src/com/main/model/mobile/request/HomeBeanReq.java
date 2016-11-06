package com.main.model.mobile.request;

import com.main.model.mobile.BaseRequest;

/**
 * Created by strike on 16/6/6.
 */
public class HomeBeanReq extends BaseRequest {
   public HomeBeanReq(){}
   public RequestParam requestParams;

    public HomeBeanReq(int pageNo,int pageSize,int homeBeanType){
        cmdType = "appService";
        methodName = "getHomeBeans";
        requestParams = new RequestParam(pageNo,pageSize,homeBeanType);
    }
    public class RequestParam{
       public int pageNo,pageSize,homeBeanType;
       public RequestParam(){}
        public RequestParam(int pageNo,int pageSize,int homeBeanType){
            this.pageNo = pageNo;
            this.pageSize = pageSize;
            this.homeBeanType = homeBeanType;
        }
    }
}
