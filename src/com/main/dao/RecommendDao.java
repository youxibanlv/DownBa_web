package com.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.main.model.Recommend;

public interface RecommendDao {
	/**
	 * 添加广告
	 * */
	boolean addRecommend(Recommend recommend);
	/**
	 * 根据广告类型查询广告
	 * */
	List<Recommend> getRecommendListByType(@Param("recommend_type")int recommendType);
	/**
	 * 更新广告
	 * */
	boolean updateRecommend(int recommendId);
	/**
	 * 删除广告位
	 * **/
	boolean delRecommend(@Param("recommend_id")int recommend_id);
	/**
	 * 根据id查询广告位
	 * **/
	Recommend getRecommendById(@Param("recommend_id")int recommend_id);
}
