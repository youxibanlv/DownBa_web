package com.main.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.main.model.App;

public interface IAppService {
	
	List<App> getAppByKeyword(String key,int pageNo,int pageSize);
	int getTotalByKey(String key);
	String getDownloadUrl(String appID);
	//获取app详情
	App getAppDetails(String appid);
	/***
	 * 获取所有应用的列表
	 * 
	 ***/
	List<App> getAppList(int pageNo, int pageSize);

	/**
	 * 获取app总数
	 * 
	 **/
	int getTotalApp(int cateId);

	/***
	 * 根据名称获取应用列表
	 * 
	 ***/
	List<App> getAppListByAppName(String appame);

	/***
	 * 根据分类获取应用列表
	 * 
	 ***/
	List<App> getAppListByCateId(int orderType,int cateId,int pageNO,int pageSize);

	/***
	 * 根据id获取应用列表
	 * 
	 ***/
	App getAppByAppId(String appId);

	List<App> getAppByAppIdStr(String isString);

	/***
	 * 添加应用
	 * 
	 ***/
	boolean addApp(App app);

	/***
	 * 删除应用列表
	 * 
	 ***/
	boolean deleteApp(int appId);

	/***
	 * 更新应用
	 * 
	 ***/
	boolean updateApp(App app);
}
