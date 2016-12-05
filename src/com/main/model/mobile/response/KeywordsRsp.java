package com.main.model.mobile.response;



import java.util.List;

import com.main.model.Keyword;
import com.main.model.mobile.BaseResponse;
import com.main.model.mobile.request.KeywordsReq;

/**
 * Created by strike on 16/6/12.
 */
public class KeywordsRsp extends BaseResponse {
    public ResultData resultData = new ResultData();
    public KeywordsRsp(KeywordsReq req) {
    	super(req);
	}
    public KeywordsRsp() {
	}
    public List<Keyword> getKeywords(){
        return resultData.keywords;
    }

    public class ResultData {
       public List<Keyword> keywords;
    }
}
