package com.main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.dao.HomeBeanDao;
import com.main.model.HomeBean;
import com.main.service.IHomeBeanService;
@Service
@Transactional
public class HomeBeanServiceImpl implements IHomeBeanService {
	@Autowired
	private HomeBeanDao dao;
	@Override
	public List<HomeBean> getList(int pageno, int pagesize,int homeBeanType) {
		// TODO Auto-generated method stub
		return dao.getList(pageno, pagesize,homeBeanType);
	}

	@Override
	public int getTotalHomeBean(int homeBeanType) {
		// TODO Auto-generated method stub
		return dao.getTotalHomeBean(homeBeanType);
	}

	@Override
	public List<HomeBean> getListByTitle(String title) {
		// TODO Auto-generated method stub
		return dao.getListByTitle(title);
	}

	@Override
	public List<HomeBean> getListByType(int homeBeanType) {
		// TODO Auto-generated method stub
		return dao.getListByType(homeBeanType);
	}

	@Override
	public HomeBean getBeanById(int id) {
		// TODO Auto-generated method stub
		return dao.getBeanById(id);
	}

	@Override
	public boolean addHomeBean(HomeBean bean) {
		// TODO Auto-generated method stub
		return dao.addHomeBean(bean);
	}

	@Override
	public boolean deleteHomeBean(int id) {
		// TODO Auto-generated method stub
		return dao.deleteHomeBean(id);
	}

	@Override
	public boolean updateHomeBean(HomeBean bean) {
		// TODO Auto-generated method stub
		return dao.updateHomeBean(bean);
	}

}
