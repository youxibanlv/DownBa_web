package com.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.main.model.Keyword;

public interface KeywordDao {
	List<Keyword> getDefault(@Param("key") String key,@Param("num") int num);
	boolean add(@Param("keyword") Keyword keyword);
	Keyword getKeyword(@Param("key")String key);
}
