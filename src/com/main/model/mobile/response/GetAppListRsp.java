package com.main.model.mobile.response;

import java.util.ArrayList;
import java.util.List;

import com.main.model.App;
import com.main.model.PageBean;
import com.main.model.mobile.BaseRequest;
import com.main.model.mobile.BaseResponse;

/**
 * Created by strike on 16/6/13.
 */
public class GetAppListRsp extends BaseResponse {
	public ResultData resultData = new ResultData();

	public GetAppListRsp(BaseRequest request) {
		super(request);
	}

	public GetAppListRsp() {
	}

	public class ResultData {
		public List<App> appList = new ArrayList<>();
		public PageBean pageBean = new PageBean();
	}
}
