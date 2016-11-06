package com.main.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.main.model.mobile.BaseResponse;

import gson.Gson;

public class HttpUtils {

	public static void sendRsp(HttpServletResponse response,BaseResponse rsp){
		Gson gson = new Gson();
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();
			writer.write(gson.toJson(rsp));
			System.out.println("*************************");
			System.out.println(gson.toJson(rsp));
			System.out.println("*************************");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
