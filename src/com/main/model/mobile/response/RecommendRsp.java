package com.main.model.mobile.response;

import java.util.ArrayList;
import java.util.List;

import com.main.model.Recommend;
import com.main.model.mobile.BaseRequest;
import com.main.model.mobile.BaseResponse;

/**
 * Created by strike on 16/6/6.
 */
public class RecommendRsp extends BaseResponse {
	public RecommendRsp() {
	}
    public RecommendRsp(BaseRequest req) {
		super(req);
	}
	public ResultData resultData = new ResultData();

    public List<Recommend> getAppList() {
        return resultData.recommends;
    }
    public class ResultData {
       public List<Recommend> recommends = new ArrayList<>();
    }
	
}
