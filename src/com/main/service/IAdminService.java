package com.main.service;

import java.util.List;

import com.main.model.Admin;

public interface IAdminService {

	Admin login(String userName,String password);
	
	void createAdmin(Admin admin);
	
	boolean update(int id,Admin admin);
	
	boolean delete(Admin admin);
	
	Admin findById(int id);
	
	List<Admin> findAll();
}
