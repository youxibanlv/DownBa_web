package com.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.dao.AdminDao;
import com.main.model.Admin;
import com.main.service.IAdminService;

@Service
@Transactional
public class AdminServiceImpl implements IAdminService {
	@Resource
	private AdminDao adminDao;
	//判断用户是否登录成功
	public Admin login(String userName, String password) {
		Admin admin = adminDao.login(userName);
		if (admin != null && password.equals(admin.getPassword())) {
			System.out.println("登录成功"+ admin.toString());
			return admin;
		} else {
			if (admin == null) {
				System.out.println("用户名不存在");
			}else {
				System.out.println("密码不正确!");
			}
			return null;
		}
	}

	public void createAdmin(Admin admin) {
		adminDao.createAdmin(admin);

	}

	public boolean update(int id, Admin admin) {
		// TODO Auto-generated method stub
		return adminDao.update(id, admin);
	}

	public boolean delete(Admin admin) {
		// TODO Auto-generated method stub
		return adminDao.delete(admin);
	}

	public Admin findById(int id) {
		// TODO Auto-generated method stub
		return adminDao.findById(id);
	}

	public List<Admin> findAll() {
		// TODO Auto-generated method stub
		return adminDao.findAll();
	}

}
