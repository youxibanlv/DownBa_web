package com.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.main.model.User;



public interface UserDao {
	User getUserByName(@Param("userName")String userName);
	void save(User user);
	boolean update(@Param("user")User user);
	boolean delete(int id);
	User findById(int id);
	List<User> findAll();
}
