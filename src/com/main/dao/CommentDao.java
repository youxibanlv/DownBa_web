package com.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.main.model.Comment;

public interface CommentDao {
	List<Comment> getListByAppId(@Param("appId")String appId);

	Comment getById(@Param("id")int id);

	Comment getByUid(@Param("uid")int uid);

	boolean add(@Param("comment")Comment comment);

	boolean del(@Param("comment")Comment comment);

	boolean update(@Param("comment")Comment comment);
}
