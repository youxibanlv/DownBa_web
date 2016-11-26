package com.main.model.mobile.request;

import com.main.model.mobile.BaseRequest;

/**
 * Created by strike on 16/6/1.
 */
public class RegisterReq extends BaseRequest {

   public RequestParam requestParams = new RequestParam();
   public RegisterReq() {
}
    public RegisterReq(String name,String password){
        cmdType = "userService";
        methodName = "register";
        requestParams = new RequestParam(name,password);
    }

    public class RequestParam{
       public  String username;
       public  String password;
      public RequestParam() {
	}
        public RequestParam(String name,String pass){
            username = name;
            password = pass;
        }
    }
}
