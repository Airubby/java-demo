package com.store.service.serviceImp;

import java.sql.SQLException;

import com.store.dao.UserDao;
import com.store.dao.daoImp.UserDaoImp;
import com.store.domain.User;
import com.store.service.UserService;

public class UserServiceImp implements UserService {

	@Override
	public void userRegist(User user) throws SQLException {
		// 实现注册功能
		UserDao UserDao=new UserDaoImp();
		UserDao.userRegist(user);
	}

}
