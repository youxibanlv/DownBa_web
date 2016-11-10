package com.main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.dao.VersionDao;
import com.main.model.Version;
import com.main.service.IVersionService;

@Service
@Transactional
public class VersionServiceImpl implements IVersionService {
	@Autowired
	private VersionDao dao;

	@Override
	public List<Version> getList(int pageno, int pagesize) {
		if (pageno < 1) {
			pageno = 1;
		}
		return dao.getList((pageno - 1) * pagesize, pagesize);
	}

	@Override
	public int getTotal() {
		return dao.getTotal();
	}

	@Override
	public List<Version> getListByName(String name) {
		return dao.getListByName(name);
	}

	@Override
	public Version getByChannel(int channelId) {
		return dao.getByChannel(channelId);
	}

	@Override
	public boolean add(Version version) {
		return dao.add(version);
	}

	@Override
	public boolean delete(int id) {
		return dao.delete(id);
	}

	@Override
	public boolean update(Version version) {
		return dao.update(version);
	}

	@Override
	public Version getById(int id) {
		return dao.getById(id);
	}

}
