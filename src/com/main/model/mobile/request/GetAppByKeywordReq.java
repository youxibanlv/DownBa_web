package com.main.model.mobile.request;

import com.main.model.mobile.BaseRequest;

/**
 * Created by strike on 16/6/13.
 */
public class GetAppByKeywordReq extends BaseRequest {

	public RequestParam requestParams;

	public GetAppByKeywordReq() {
		
	}

	public GetAppByKeywordReq(String key, int pageNo, int pageSize) {
		cmdType = "appService";
		methodName = "getAppByKeyword";
		requestParams = new RequestParam(key, pageNo, pageSize);
	}

	public class RequestParam {
		public String keyword;
		public int pageNo;
		public int pageSize;

		public RequestParam() {
		}

		public RequestParam(String key, int pageNo, int pageSize) {
			keyword = key;
			this.pageNo = pageNo;
			this.pageSize = pageSize;
		}
	}
}
