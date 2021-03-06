package com.main.service;

import java.util.List;


import com.main.model.User;



public interface IUserService {
	User getUserByName(String userName);
	boolean save(User user);
	boolean update(User user);
	boolean delete(int id);
	User findById(int id);
	List<User> findAll();
}
