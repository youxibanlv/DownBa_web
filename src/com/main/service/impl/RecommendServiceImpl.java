package com.main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.dao.AppDao;
import com.main.dao.RecommendDao;
import com.main.model.App;
import com.main.model.Recommend;
import com.main.service.IRecommendService;
import com.main.utils.Constance;

@Service
@Transactional
public class RecommendServiceImpl implements IRecommendService {
	@Autowired
	private RecommendDao dao;
	@Autowired
	private AppDao appDao;

	@Override
	public boolean addRecommend(Recommend recommend) {

		return dao.addRecommend(recommend);
	}

	@Override
	public List<Recommend> getRecommendListByType(int recommendType) {
		List<Recommend> recommends = dao.getRecommendListByType(recommendType);
		List<App> apps=null;
		if (recommends != null && recommends.size() > 0) {
			String idString = "";
			for (Recommend recommend : recommends) {
				idString = idString + recommend.getAppId() + ",";
			}
			if (!"".equals(idString)) {
				idString = idString.substring(0, idString.length()-1);
				apps = appDao.getAppByAppIdStr(idString);
			}
			if (apps!=null) {
				for(Recommend recommend:recommends){
					for(App app:apps){
						if (recommend.getAppId().equals(app.getApp_id())) {
							recommend.setApp(app);
							break;
						}
					}
				}
			}
		}
		return recommends;
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
