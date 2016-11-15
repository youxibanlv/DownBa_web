package com.main.model.mobile.response;

import java.util.ArrayList;
import java.util.List;

import com.main.model.Comment;
import com.main.model.mobile.BaseResponse;
import com.main.model.mobile.request.AddCommentReq;

public class AddCommentRsp extends BaseResponse {
	public ResultData resultData = new ResultData();

	public AddCommentRsp(AddCommentReq req) {
		super(req);
	}

	public AddCommentRsp() {
	}

	public class ResultData {
		public List<Comment> list = new ArrayList<>();
	}
}
