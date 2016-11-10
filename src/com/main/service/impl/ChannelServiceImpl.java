package com.main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.dao.ChannelDao;
import com.main.model.Channel;
import com.main.model.Version;
import com.main.service.IChannelService;
@Service
@Transactional
public class ChannelServiceImpl implements IChannelService {
	
	@Autowired
	private ChannelDao dao;
	@Override
	public List<Channel> getList(int pageno, int pagesize) {
		if (pageno<1) {
			pageno = 1;
		}
		return dao.getList((pageno-1)*pagesize, pagesize);
	}

	@Override
	public int getTotal() {
		return dao.getTotal();
	}

	@Override
	public List<Channel> getListByName(String name) {
		return dao.getListByName(name);
	}

	@Override
	public Channel getBeanById(String id) {
		return dao.getBeanById(id);
	}

	@Override
	public boolean add(Channel channel) {
		return dao.add(channel);
	}

	@Override
	public boolean delete(int id) {
		return dao.delete(id);
	}

	@Override
	public boolean update(Channel channel) {
		return dao.update(channel);
	}

}
