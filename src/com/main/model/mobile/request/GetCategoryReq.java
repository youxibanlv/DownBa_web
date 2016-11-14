package com.main.model.mobile.request;

import com.main.model.mobile.BaseRequest;

/**
 * Created by strike on 16/7/2.
 */
public class GetCategoryReq extends BaseRequest {

	public GetCategoryReq(){}
    public RequestParam requestParams;

    public GetCategoryReq(int parentId){
        cmdType = "appService";
        methodName = "getCateByParentId";
        requestParams = new RequestParam(parentId);
    }

   public  class RequestParam{
	   public RequestParam(){}
        public int parentId;
        public RequestParam(int parentId){
            this.parentId = parentId;
        }
    }
}
