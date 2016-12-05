package com.main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.dao.KeywordDao;
import com.main.model.Keyword;
import com.main.service.IKeywordService;
@Service
@Transactional
public class KeywordServiceImpl implements IKeywordService {
	@Autowired
	private KeywordDao dao;
	@Override
	public List<Keyword> getDefault(String key, int num) {
		if (num <= 0) {
			num = 6;
		}
		return dao.getDefault(key, num);
	}

	@Override
	public boolean add(String keyword) {
		Keyword key = dao.getKeyword(keyword);
		if (key == null) {
			key = new Keyword();
			key.setQ(keyword);
			return dao.add(key);
		}
		return false;
	}

}
