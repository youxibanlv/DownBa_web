package com.main.model.mobile.response;

import com.main.model.Version;
import com.main.model.mobile.BaseResponse;

public class VersionRsp extends BaseResponse {
	public ResultData resultData = new ResultData();

	public class ResultData {
		public Version version;
	}
}
