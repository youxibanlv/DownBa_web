package com.main.dao;

import java.util.List;

import com.main.model.User;



public interface UserDao {
	void save(User user);
	boolean update(User user);
	boolean delete(int id);
	User findById(int id);
	List<User> findAll();
}
