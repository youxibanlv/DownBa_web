package com.main.utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.main.model.PageBean;
import com.main.model.mobile.BaseResponse;

import gson.Gson;

public class HttpUtils {
	/**
	 * 删除服务器文件
	 * 
	 * @param request
	 * @param filePath
	 *             文件的地址
	 * @return boolean
	 ***/
	public static boolean delFromServer(HttpServletRequest request, String filePath) {
		if (filePath != null) {
			String path = request.getSession().getServletContext().getRealPath("userUpload");
			String type = filePath.substring(filePath.lastIndexOf("."),filePath.length());
			if (".apk".equals(type)) {
				path = path + "/apk";
			}
			String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
			File file = new File(path, fileName);
			if (file.exists()) {
				file.delete();
				return true;
			}
		}
		return false;
	}

	/**
	 * 上传文件到服务器
	 * 
	 * @param request
	 * @param file
	 *             上传的文件
	 * @return logoPath 文件的地址
	 ***/
	public static String uploadFile(HttpServletRequest request, MultipartFile file) {
		String path = request.getSession().getServletContext().getRealPath("userUpload");
		String name = null;
		String logoPath = null;
		if (file != null) {// 有上传图片
			String fileName = file.getOriginalFilename();
			String type = fileName.substring(fileName.lastIndexOf("."), fileName.length());
			if (".apk".equals(type)) {
				path = path + "/apk";
				name = fileName;
			}else{
				name = System.currentTimeMillis() + type;
			}
			File logo = new File(path, name);
			if (!logo.exists()) {
				logo.mkdirs();
			}
			try {
				file.transferTo(logo);
			} catch (Exception e) {
			}
			String contextPath = request.getContextPath();
			if (".apk".equals(type)) {
				logoPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ contextPath + "/userUpload/apk/" + name;
			}else{
				logoPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ contextPath + "/userUpload/" + name;
			}
		}
		return logoPath;
	}

	/**
	 * 获取分页对象
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页显示数
	 * @param total
	 *            总记录数
	 ***/
	public static void setPageBean(HttpServletRequest request, int pageNo, int pageSize, int total) {
		PageBean pageBean = new PageBean();
		pageBean.pageNo = pageNo;
		pageBean.pageSize = pageSize;
		pageBean.total = total;
		pageBean.totalPage = pageBean.total % pageBean.pageSize == 0 ? pageBean.total / pageBean.pageSize
				: pageBean.total / pageBean.pageSize + 1;
		request.setAttribute("pageBean", pageBean);
	}

	/**
	 * 返回json数据到前端ajax
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页显示数
	 * @param total
	 *            总记录数
	 ***/
	public static void sendToAjax(HttpServletResponse response,Map<String, Object> map) {
		Gson gson = new Gson();
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();
			writer.write(gson.toJson(map));
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public static PageBean getPageBean(int pageNo, int pagesize, int total) {
		PageBean pageBean = new PageBean();
		pageBean.pageNo = pageNo;
		pageBean.pageSize = pagesize;
		pageBean.total = total;
		pageBean.totalPage = pageBean.total % pageBean.pageSize == 0 ? pageBean.total / pageBean.pageSize
				: pageBean.total / pageBean.pageSize + 1;
		return pageBean;
	}

}
