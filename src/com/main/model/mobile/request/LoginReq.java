package com.main.model.mobile.request;

import com.main.model.mobile.BaseRequest;

/**
 * Created by strike on 16/6/3.
 */
public class LoginReq extends BaseRequest {
    RequestParam requestParams;

    public LoginReq(String name,String password){
        cmdType = "userService";
        methodName = "login";
        requestParams = new RequestParam(name,password);
    }

    class RequestParam{
        String username;
        String password;
        public RequestParam(String name,String pass){
            username = name;
            
        }
    }
}
