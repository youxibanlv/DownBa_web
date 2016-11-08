package com.main.utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.main.model.PageBean;
import com.main.model.mobile.BaseResponse;

import gson.Gson;

public class HttpUtils {
	/**
	 * 删除服务器文件
	 * @param request 
	 * @param filePath  文件的地址
	 * @return boolean  
	 ***/
	public static boolean delFromServer(HttpServletRequest request,String filePath){
		if (filePath!=null) {
			String fileName = filePath.substring(filePath.lastIndexOf("/")+1,filePath.length());
			String path = request.getSession().getServletContext().getRealPath("userUpload");
			File file = new File(path,fileName);
			if (file.exists()) {
				file.delete();
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 上传文件到服务器
	 * @param request 
	 * @param file  上传的文件
	 * @return logoPath 文件的地址
	 ***/
	public static String uploadFile(HttpServletRequest request, MultipartFile file){
		String path = request.getSession().getServletContext().getRealPath("userUpload");
		String name = null;
		String logoPath = null;
		if (file != null) {//有上传图片
			String fileName = file.getOriginalFilename();
		    name = System.currentTimeMillis()+fileName.substring(fileName.lastIndexOf("."),fileName.length());
			File logo = new File(path,name);
			if (!logo.exists()) {
				logo.mkdirs();
			}
			try {
				file.transferTo(logo);
			} catch (Exception e) {
			}
			String contextPath = request.getContextPath();
			logoPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ contextPath + "/userUpload/" + name;
		}
		return logoPath;
	}
	/**
	 * 获取分页对象
	 * @param pageNo 页码
	 * @param pageSize 每页显示数
	 * @param total 总记录数
	 ***/
	public static PageBean getPageBean(int pageNo,int pageSize,int total){
		PageBean pageBean = new PageBean();
		pageBean.pageNo = pageNo;
		pageBean.pageSize = pageSize;
		pageBean.total = total;
		pageBean.totalPage = pageBean.total % pageBean.pageSize == 0
				?pageBean.total/pageBean.pageSize:pageBean.total/pageBean.pageSize+1;
		return pageBean;
	}
	/**
	 * 返回数据给app端
	 * 
	 ***/
	public static void sendRsp(HttpServletResponse response, BaseResponse rsp) {
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
