package com.main.model.mobile.response;

import com.main.model.mobile.BaseResponse;
import com.main.model.mobile.request.InfoDesReq;

/**
 * Created by strike on 2017/2/19.
 */

public class InfoDesRsp extends BaseResponse {

    public ResultData resultData;
    
    public InfoDesRsp(InfoDesReq req) {
		cmdType = req.cmdType;
		methodName = req.methodName;
	}
    public InfoDesRsp() {
	}
    public class ResultData {
        public String infoBody = "";
    }
}
