package com.main.service;

import java.util.List;

import com.main.model.Recommend;

public interface IRecommendService {

	/**
	 * 根据id查询广告
	 * **/
	Recommend getRecommendById(int recommend_id);
	/**
	 * 删除广告位
	 * **/
	boolean delRecommend(int recommend_id);
	/**
	 * 添加广告
	 * */
	boolean addRecommend(Recommend recommend);
	/**
	 * 根据广告类型查询广告
	 * */
	List<Recommend> getRecommendListByType(int recommendType);
	/**
	 * 更新广告
	 * */
	boolean updateRecommend(int recommendId);
}
