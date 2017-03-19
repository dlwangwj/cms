package cn.nlr.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.nlr.dao.UserDao;
import cn.nlr.model.User;
import cn.nlr.service.UserService;



@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	
	@Transactional
	public void saveUser(User user){
		user = userDao.selectByPrimaryKey(1);
		user.setName("5566");
		userDao.updateByPrimaryKey(user);
		
		List<User> users = userDao.selectByAge(1);
		for(User u : users){
			System.out.println(u.getName());
		}
	}
}
