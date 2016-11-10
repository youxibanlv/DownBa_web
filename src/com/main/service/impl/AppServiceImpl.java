package com.main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.dao.AppDao;
import com.main.model.App;
import com.main.service.IAppService;
import com.main.utils.TextUtils;
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
		List<App> list = appDao.getAppListByAppName(appName);
		if (list!= null && list.size()>0) {
			for(App app:list){
				app.setApp_desc(TextUtils.delHTMLTag(app.getApp_desc()));
			}
		}
		return list;
	}

	@Override
	public List<App> getAppListByCateId(int cateId) {
		// TODO Auto-generated method stub
		return appDao.getAppListByCateId(cateId);
	}

	@Override
	public App getAppByAppId(int appId) {
		// TODO Auto-generated method stub
		return appDao.getAppByAppId(appId);
	}

	@Override
	public boolean addApp(App app) {
		// TODO Auto-generated method stub
		return appDao.addApp(app);
	}

	@Override
	public boolean deleteApp(int appId) {
		// TODO Auto-generated method stub
		return appDao.deleteApp(appId);
	}

	@Override
	public boolean updateApp(App app) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getTotalApp() {
		int count = 0;
		try {
			count =appDao.getTotalApp();
		} catch (Exception e) {
			e.printStackTrace();
			count = 0;
		}
		return count;
	}

	@Override
	public List<App> getAppByAppIdStr(String isString) {
		List<App> list = appDao.getAppByAppIdStr(isString);
		if (list!= null && list.size()>0) {
			for(App app:list){
				app.setApp_desc(TextUtils.delHTMLTag(app.getApp_desc()));
			}
		}
		return list;
	}

}
