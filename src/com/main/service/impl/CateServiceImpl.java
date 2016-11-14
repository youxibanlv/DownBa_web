package com.main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.dao.CateDao;
import com.main.model.Category;
import com.main.service.ICateService;
@Service
@Transactional
public class CateServiceImpl implements ICateService {

	@Autowired
	private CateDao dao;
	@Override
	public List<Category> getCategorys(int parentId) {
		return dao.getCategorys(parentId);
	}

}
