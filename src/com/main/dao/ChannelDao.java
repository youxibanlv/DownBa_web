package com.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.main.model.Channel;

public interface ChannelDao {

	List<Channel> getList(@Param("pageno") int pageno, @Param("pagesize") int pagesize);

	int getTotal();

	List<Channel> getListByName(@Param("name") String name);

	Channel getBeanById(@Param("id") String id);

	boolean add(@Param("channel") Channel channel);

	boolean delete(@Param("id") int id);

	boolean update(@Param("id") Channel channel);
}
