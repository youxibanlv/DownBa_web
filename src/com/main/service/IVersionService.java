package com.main.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.main.model.Version;

public interface IVersionService {
	List<Version> getList(int pageno,  int pagesize);

	int getTotal();

	Version getById(int id);
	
	List<Version> getListByName( String name);

	Version getByChannel(int channelId);

	boolean add( Version version);

	boolean delete(int id);

	boolean update( Version version);
}
