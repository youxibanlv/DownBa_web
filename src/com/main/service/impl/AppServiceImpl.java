package com.main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.dao.AppDao;
import com.main.dao.CommentDao;
import com.main.model.App;
import com.main.service.IAppService;
import com.main.utils.Constance;
@Service
@Transactional
public class AppServiceImpl implements IAppService {
	@Autowired
	private AppDao appDao;
	@Autowired
	private CommentDao commentDao;
	@Override
	public List<App> getAppList(int pageNo, int pageSize) {
		if (pageNo<1) {
			pageNo = 1;
		}
		if (pageSize == 0) {
			pageSize = Constance.DEFALT_PAGESIZE;
		}
		return appDao.getAppList((pageNo-1)*pageSize,pageSize);
	}

	@Override
	public List<App> getAppListByAppName(String appName) {
		List<App> list = appDao.getAppListByAppName(appName);
		return list;
	}

	@Override
	public List<App> getAppListByCateId(int orderType,int cateId,int pageNo,int pageSize) {
		if (pageNo<1) {
			pageNo = 1;
		}
		if (pageSize == 0) {
			pageSize = Constance.DEFALT_PAGESIZE;
		}
		List<App> list;
		if (orderType == Constance.ORDER_HOT) {
			String idString = appDao.getIdStringByCateId(cateId);
			list = appDao.getAppByAppIdStr(idString);
		}else{
			list = appDao.getAppListByCateId(cateId,(pageNo-1)*pageSize,pageSize);
		}
		return list;
	}

	@Override
	public App getAppByAppId(String appId) {
		App app = appDao.getAppByAppId(appId);
		return app;
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
	public int getTotalApp(int cateId) {
		int count = 0;
		try {
			count = appDao.getTotalApp(cateId);
		} catch (Exception e) {
			e.printStackTrace();
			count = 0;
		}
		return count;
	}

	@Override
	public List<App> getAppByAppIdStr(String isString) {
		List<App> list = appDao.getAppByAppIdStr(isString);
		return list;
	}

	@Override
	public App getAppDetails(String appId) {
		App app = new App();
		//查询图片列表
		app.setResource(appDao.getResource(appId));
		//查询评论列表
		app.setCommentList(commentDao.getListByAppId(appId));
		//查询app详情
		app.setApp_desc(appDao.getDesc(appId));
		return app;
	}

	@Override
	public String getDownloadUrl(String appID) {
		return appDao.getDownloadUrl(appID);
	}

	@Override
	public List<App> getAppByKeyword(String key, int pageNo, int pageSize) {
		if (pageNo<1) {
			pageNo = 1;
		}
		if (pageSize == 0) {
			pageSize = Constance.DEFALT_PAGESIZE;
		}
		return appDao.getAppByKeyword(key,(pageNo-1)*pageSize,pageSize);
	}

	@Override
	public int getTotalByKey(String key) {
		return appDao.getTotalByKey(key);
	}

}
