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
	public List<Recommend> getRecommendList(int recommendType) {
		// TODO Auto-generated method stub
		return dao.getRecommendList(recommendType);
	}

	@Override
	public boolean updateRecommend(int recommendId) {
		// TODO Auto-generated method stub
		return false;
	}

}
