package com.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.main.model.Version;

public interface VersionDao {

	List<Version> getList(@Param("pageno") int pageno, @Param("pagesize") int pagesize);

	Version getById(@Param("id") int id);
	
	int getTotal();

	List<Version> getListByName(@Param("name") String name);

	Version getByChannel(@Param("channelId") int channelId);

	boolean add(@Param("version") Version version);

	boolean delete(@Param("id") int id);

	boolean update(@Param("id") Version version);
}
