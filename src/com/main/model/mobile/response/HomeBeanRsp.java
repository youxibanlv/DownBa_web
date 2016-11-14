package com.main.model.mobile.response;

import java.util.ArrayList;
import java.util.List;

import com.main.model.HomeBean;
import com.main.model.PageBean;
import com.main.model.mobile.BaseRequest;
import com.main.model.mobile.BaseResponse;

public class HomeBeanRsp extends BaseResponse{

	public ResultData resultData = new ResultData();
	
	public HomeBeanRsp(BaseRequest request) {
		super(request);
	}
	public HomeBeanRsp() {
	}

    public List<HomeBean> getBeans() {
        return resultData.homeBeans;
    }
    public class ResultData {
       public List<HomeBean> homeBeans = new ArrayList<>();
       public PageBean pageBean ;
    }
	
}
