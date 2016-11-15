package com.main.service;

import java.util.List;

import com.main.model.Comment;

public interface ICommentService {

	List<Comment> getListByAppId(String appId);
	
	Comment getById(int id);
	
	Comment getByUid(int uid);
	
	boolean add(Comment comment);
	
	boolean del(Comment comment);
	
	boolean update(Comment comment);
	
}
