package com.main.model.mobile.response;


import java.util.ArrayList;
import java.util.List;

import com.main.model.PageBean;
import com.main.model.Subject;
import com.main.model.mobile.BaseResponse;
import com.main.model.mobile.request.SubjectReq;

/**
 * Created by strike on 16/8/2.
 */
public class SubjectRsp extends BaseResponse {
    public ResultData resultData = new ResultData();
    public SubjectRsp() {
	}
    public SubjectRsp(SubjectReq req) {
		super(req);
	}
    public class ResultData {
        public List<Subject> subjects = new ArrayList<>();
        public PageBean pageBean = new PageBean();
    }
}
