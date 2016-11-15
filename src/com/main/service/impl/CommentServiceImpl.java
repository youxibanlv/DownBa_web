package com.main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.dao.CommentDao;
import com.main.model.Comment;
import com.main.service.ICommentService;
@Service
@Transactional
public class CommentServiceImpl implements ICommentService {
	@Autowired
	private CommentDao dao;
	@Override
	public List<Comment> getListByAppId(String appId) {
		return dao.getListByAppId(appId);
	}

	@Override
	public Comment getById(int id) {
		return dao.getById(id);
	}

	@Override
	public Comment getByUid(int uid) {
		return dao.getByUid(uid);
	}

	@Override
	public boolean add(Comment comment) {
		return dao.add(comment);
	}

	@Override
	public boolean del(Comment comment) {
		return dao.del(comment);
	}

	@Override
	public boolean update(Comment comment) {
		return dao.update(comment);
	}

}
