package com.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.main.model.App;

public interface AppDao {
	/***
	 * 获取所有应用的列表
	 * 
	 ***/
	List<App> getAppList(int pageno, int pagesize);

	/*
	 * 获取app总数
	 **/
	int getTotalApp();

	/***
	 * 根据名称获取应用列表
	 * 
	 ***/
	List<App> getAppListByAppName(@Param("appName") String appname);

	/***
	 * 根据分类获取应用列表
	 * 
	 ***/
	List<App> getAppListByCateId(int cateId);

	/***
	 * 根据id获取应用列表
	 * 
	 ***/
	App getAppByAppId(@Param("app_id") int appId);

	/***
	 * 根据id字符串列表获取应用列表
	 * 
	 ***/
	List<App> getAppByAppIdStr(@Param("idString") String isString);

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
