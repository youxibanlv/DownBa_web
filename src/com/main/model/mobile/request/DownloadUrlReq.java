package com.main.model.mobile.request;

import com.main.model.mobile.BaseRequest;

/**
 * Created by strike on 16/6/20.
 */
public class DownloadUrlReq extends BaseRequest {

    public RequestParam requestParams;
    public DownloadUrlReq() {
	}
    public DownloadUrlReq(String app_id,String version,String uid){
        cmdType = "appService";
        methodName = "getUrlById";
        requestParams = new RequestParam(app_id,version,uid);
    }

   public  class RequestParam{
       public String app_id;
       public String app_version;
       public String uid;
       public RequestParam() {
	}
        public RequestParam(String name,String version,String uid){
            this.app_id = name;
            this.app_version = version;
            this.uid = uid;
        }
    }
}
