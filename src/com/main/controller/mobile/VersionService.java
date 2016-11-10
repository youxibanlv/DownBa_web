package com.main.controller.mobile;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.main.model.Version;
import com.main.model.mobile.request.VersionReq;
import com.main.model.mobile.response.VersionRsp;
import com.main.service.IChannelService;
import com.main.service.IVersionService;
import com.main.utils.HttpUtils;

@Controller
@RequestMapping(value="versionService")
public class VersionService {
	
	@Autowired
	private IVersionService vService;
	
	@RequestMapping(value="checkUpdate.do")
	public void checkUpdate(HttpServletResponse response,@RequestBody VersionReq req){
		VersionRsp rsp = new VersionRsp();
		if (req != null) {
			Version version = req.requestParams.version;
			if (version != null) {
				Version inServer = vService.getByChannel(version.getChannel_id());
				if (inServer != null) {
					if (inServer.getVersion_code() > version.getVersion_code()) {
						rsp.resultData.version = inServer;
					}else{
						rsp.resultData.version = version;
					}
					rsp.result=0;
				} else {
					rsp.failReason = "没有查询到该渠道的版本信息";
				}
			}else{
				rsp.failReason = "请求参数错误";
			}
		}else{
			rsp.failReason = "请求参数错误";
		}
		HttpUtils.sendRsp(response, rsp);
	}
}
