package com.main.model.mobile;

/**
 * Created by strike on 16/5/31.
 */
public class BaseResponse {
    public int result =1;
    public String failReason;
    public String cmdType;
    public String methodName;
    
	public BaseResponse() {
		
	}
    public BaseResponse(BaseRequest req){
    	this.cmdType = req.cmdType;
    	this.methodName = req.methodName;
    }
}
