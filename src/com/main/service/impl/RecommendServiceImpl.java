package com.main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.dao.RecommendDao;
import com.main.model.Recommend;
import com.main.service.IRecommendService;
@Service
@Transactional
public class RecommendServiceImpl implements IRecommendService {
	@Autowired
	private RecommendDao dao;
	@Override
	public boolean addRecommend(Recommend recommend) {
		
		return dao.addRecommend(recommend);
	}

	@Override
	public List<Recommend> getRecommendListByType(int recommendType) {
		// TODO Auto-generated method stub
		return dao.getRecommendListByType(recommendType);
	}

	@Override
	public boolean updateRecommend(int recommendId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delRecommend(int recommend_id) {
		// TODO Auto-generated method stub
		return dao.delRecommend(recommend_id);
	}

	@Override
	public Recommend getRecommendById(int recommend_id) {
		// TODO Auto-generated method stub
		return dao.getRecommendById(recommend_id);
	}

}
