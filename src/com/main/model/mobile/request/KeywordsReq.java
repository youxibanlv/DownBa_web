package com.main.model.mobile.request;

import com.main.model.mobile.BaseRequest;

/**
 * Created by strike on 16/6/12.
 */
public class KeywordsReq extends BaseRequest {

    public RequestParam requestParams;
    public KeywordsReq() {
	}
    public KeywordsReq(String key,int size){
        cmdType = "appService";
        methodName = "getKeywords";
        requestParams = new RequestParam(key,size);
    }

    public class RequestParam{
        public String key;
        public int size;
        public RequestParam() {
		}
        public RequestParam(String keyword,int keySize){
            key = keyword;
            size = keySize;
        }
    }
}
