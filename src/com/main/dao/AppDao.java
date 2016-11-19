package com.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.main.model.App;

public interface AppDao {
	//根据id查详情
	String getDesc(@Param("app_id")String appId);
	//获取图片列表
	List<String> getResource(@Param("app_id")String appId);
	/***
	 * 获取所有应用的列表
	 * 
	 ***/
	List<App> getAppList(int pageno, int pagesize);

	/*
	 * 获取app总数
	 **/
	int getTotalApp(@Param("cateId")int cateId);

	/***
	 * 根据名称获取应用列表
	 * 
	 ***/
	List<App> getAppListByAppName(@Param("appName") String appname);

	/***
	 * 根据分类获取应用列表
	 * 
	 ***/
	List<App> getAppListByCateId(@Param("cateId")int cateId,@Param("pageNO")int pageNO,@Param("pageSize")int pageSize);
	/***
	 * 根查询应用或者游戏排行榜（10条数据）
	 * 
	 ***/
	String getIdStringByCateId(@Param("cateId")int cateId);
	/***
	 * 根据id获取应用列表
	 * 
	 ***/
	App getAppByAppId(@Param("app_id") String appId);

	/***
	 * 根据id字符串列表获取应用列表
	 * 
	 ***/
	List<App> getAppByAppIdStr(@Param("idString") String idString);

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
