package com.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.dao.UserDao;
import com.main.model.User;
import com.main.service.IUserService;
@Service("userService")
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class UserServiceImpl implements IUserService{
	@Resource
	private UserDao usermapper;

	public void save(User user) {
		usermapper.save(user);
		
	}

	public boolean update(User user) {
		// TODO Auto-generated method stub
		return usermapper.update(user);
	}

	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return usermapper.delete(id);
	}

	public User findById(int id) {
		// TODO Auto-generated method stub
		return usermapper.findById(id);
	}

	public List<User> findAll() {
		// TODO Auto-generated method stub
		return usermapper.findAll();
	}
	
	

}
