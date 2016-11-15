package com.main.model.mobile.request;

import com.main.model.Comment;
import com.main.model.mobile.BaseRequest;

/**
 * Created by strike on 2016/11/14.
 */

public class AddCommentReq extends BaseRequest {
	public AddCommentReq() {
	}
    public RequestParam requestParams ;
    public AddCommentReq(Comment comment){
        cmdType = "appService";
        methodName = "addComment";
        requestParams = new RequestParam(comment);
    }

    public class RequestParam{
        public Comment comment;
        public RequestParam() {
		}
        public RequestParam(Comment comment){
            this.comment = comment;
        }
    }
}
