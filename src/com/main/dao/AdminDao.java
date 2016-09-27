package com.main.dao;

import java.util.List;

import com.main.model.Admin;

public interface AdminDao {
	
	Admin login(String account);
	
	void createAdmin(Admin admin);
	
	boolean update(int id,Admin admin);
	
	boolean delete(Admin admin);
	
	Admin findById(int id);
	
	List<Admin> findAll();
}
