package com.main.model.mobile.response;

import com.main.model.mobile.BaseResponse;
import com.main.model.mobile.request.DownloadUrlReq;

/**
 * Created by strike on 16/6/20.
 */
public class DownloadUrlRsp extends BaseResponse {
    public String resultData = "";
    public DownloadUrlRsp(DownloadUrlReq req) {
		super(req);
	}
    public DownloadUrlRsp() {
	}
}
