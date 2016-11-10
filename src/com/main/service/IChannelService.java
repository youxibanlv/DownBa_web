package com.main.service;

import java.util.List;


import com.main.model.Channel;
import com.main.model.Version;

public interface IChannelService {
	List<Channel> getList(int pageno, int pagesize);

	int getTotal();

	List<Channel> getListByName(String name);

	Channel getBeanById(String id);

	boolean add(Channel channel);

	boolean delete(int id);

	boolean update(Channel channel);
}
