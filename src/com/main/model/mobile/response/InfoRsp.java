package com.main.model.mobile.response;



import java.util.ArrayList;
import java.util.List;

import com.main.model.Info;
import com.main.model.PageBean;
import com.main.model.mobile.BaseResponse;
import com.main.model.mobile.request.InfoReq;

/**
 * Created by strike on 16/8/4.
 */
public class InfoRsp extends BaseResponse {
    public ResultData resultData = new ResultData();

    public InfoRsp(InfoReq req) {
    	super(req);
	}
    public InfoRsp() {
	}

    public class ResultData {
    	public PageBean pageBean = new PageBean();
        public List<Info> infoList = new ArrayList<>();
    }
}
