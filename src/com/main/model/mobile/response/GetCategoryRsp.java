package com.main.model.mobile.response;



import java.util.ArrayList;
import java.util.List;

import com.main.model.Category;
import com.main.model.mobile.BaseRequest;
import com.main.model.mobile.BaseResponse;

/**
 * Created by strike on 16/7/2.
 */
public class GetCategoryRsp extends BaseResponse {
    public ResultData resultData = new ResultData();
    public GetCategoryRsp() {
	}
    public GetCategoryRsp(BaseRequest request){
    	super(request);
    }
    public class ResultData {
       public List<Category> list = new ArrayList<>();
    }
}
