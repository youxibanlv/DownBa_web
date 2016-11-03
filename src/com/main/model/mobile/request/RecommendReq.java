package com.main.model.mobile.request;

import com.main.model.mobile.BaseRequest;

/**
 * Created by strike on 16/6/6.
 */
public class RecommendReq extends BaseRequest {
	public RecommendReq() {
		// TODO Auto-generated constructor stub
	}
    public RequestParam requestParams;

    public RecommendReq(String recommend_type){
        cmdType = "appService";
        methodName = "getRecommend";
        requestParams = new RequestParam(recommend_type);
    }
     public class RequestParam{
    	public RequestParam() {
			// TODO Auto-generated constructor stub
		}
       public  String recommend_type;
        public RequestParam(String recommend_type){
            this.recommend_type = recommend_type;
        }
    }
}
