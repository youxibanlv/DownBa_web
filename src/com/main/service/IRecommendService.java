package com.main.service;

import java.util.List;

import com.main.model.Recommend;

public interface IRecommendService {
	/**
	 * 添加广告
	 * */
	boolean addRecommend(Recommend recommend);
	/**
	 * 根据广告类型查询广告
	 * */
	List<Recommend> getRecommendList(int recommendType);
	/**
	 * 更新广告
	 * */
	boolean updateRecommend(int recommendId);
}
