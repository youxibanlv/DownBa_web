package com.main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.dao.AppDao;
import com.main.model.App;
import com.main.service.IAppService;
@Service
@Transactional
public class AppServiceImpl implements IAppService {
	@Autowired
	private AppDao appDao;
	
	@Override
	public List<App> getAppList(int pageNo, int pageSize) {
		
		return appDao.getAppList(pageNo, pageSize);
	}

	@Override
	public List<App> getAppListByAppName(String appName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<App> getAppListByCateId(int cateId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public App getAppByAppId(int appId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addApp(App app) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteApp(int appId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateApp(App app) {
		// TODO Auto-generated method stub
		return false;
	}

}
