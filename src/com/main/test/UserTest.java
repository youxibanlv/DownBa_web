package com.main.test;



import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.main.dao.AdminDao;
import com.main.dao.UserDao;
import com.main.model.Admin;
import com.main.model.User;
import com.main.utils.MD5Util;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class UserTest {

	@Autowired
	private UserDao userMapper;
	@Autowired
	private AdminDao adminDao;
	
//	@Test
//	public void testAdd(){
//		User user = new User(1, "xiaowuyue", "xiaowuyue","123123","13608394815","小舞月","","","90");
//		System.out.println(user.toString());
//		userMapper.save(user);
//	}
	@Test
	public void addAdmin() {
		Admin admin = new Admin(0, "xiaowuyue", MD5Util.getMd5("1"), 0, 0,"小舞月");
		System.out.println(admin.toString());
		adminDao.createAdmin(admin);
	}
//	@Test
//	public void findUserById() {
//		List<User> list = userMapper.findAll();
//		System.out.println(list.get(0).toString());
//	}
}
