package com.main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.dao.SubjectDao;
import com.main.model.HomeBean;
import com.main.model.Subject;
import com.main.service.ISubjectService;
@Service
@Transactional
public class SubjectServiceImpl implements ISubjectService {
	@Autowired
	private SubjectDao dao;
	@Override
	public List<Subject> getList(int pageno, int pagesize) {
		if (pageno>0) {
			return dao.getList((pageno-1)*pagesize, pagesize);
		}else{
			System.out.println("******页码不能小于1*******");
			return null;
		}
		
	}

	@Override
	public int getTotal() {
		return dao.getTotal();
	}

	@Override
	public List<Subject> getListByTitle(String title) {
		return dao.getListByTitle(title);
	}

	@Override
	public Subject getBeanById(int id) {
		return dao.getBeanById(id);
	}

	@Override
	public boolean add(Subject bean) {
		return dao.add(bean);
	}

	@Override
	public boolean delete(int id) {
		return dao.delete(id);
	}

	@Override
	public boolean update(HomeBean bean) {
		return dao.update(bean);
	}

}
