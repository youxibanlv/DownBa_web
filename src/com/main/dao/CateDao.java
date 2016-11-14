package com.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.main.model.Category;

public interface CateDao {
	List<Category> getCategorys(@Param(value="parentId")int parentId);
}
