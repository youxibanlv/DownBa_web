package com.main.model.mobile.request;

import com.main.model.Version;
import com.main.model.mobile.BaseRequest;

public class VersionReq extends BaseRequest {
	public VersionReq() {
	}

	public RequestParam requestParams;

	public VersionReq(Version version) {
		cmdType = "versionService";
		methodName = "checkUpdate";
		requestParams = new RequestParam(version);
	}

	public class RequestParam {
		public Version version;

		public RequestParam() {
		}

		public RequestParam(Version version) {
			this.version = version;
		}
	}
}
