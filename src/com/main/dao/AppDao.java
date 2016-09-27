package com.main.dao;

import java.util.List;

import com.main.model.App;

public interface AppDao {
	/***
	 * 获取所有应用的列表
	 * 
	 * ***/
	List<App> getAppList(int pageno,int pagesize);
	/***
	 * 根据名称获取应用列表
	 * 
	 * ***/
	List<App> getAppListByAppName(String appName);
	/***
	 * 根据分类获取应用列表
	 * 
	 * ***/
	List<App> getAppListByCateId(int cateId);
	/***
	 * 根据id获取应用列表
	 * 
	 * ***/
	App getAppByAppId(int appId);
	
	/***
	 * 添加应用
	 * 
	 * ***/
	boolean addApp(App app);
	/***
	 * 删除应用列表
	 * 
	 * ***/
	boolean deleteApp(int appId);
	/***
	 * 更新应用
	 * 
	 * ***/
	boolean updateApp(App app);
}
