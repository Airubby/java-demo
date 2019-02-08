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

	@Override
	public boolean userActive(String code) throws SQLException{
		// TODO Auto-generated method stub
		UserDao UserDao=new UserDaoImp();
		User user=UserDao.userActive(code);
		if(null!=user) {
			//可以根据激活码查询到一个用户
			//修改用户状态，清除激活码
			user.setState(1);
			user.setCode(null);
			//对数据库执行一次真实的操作
			UserDao.updateUser(user);
			return true;
		}else {
			return false;
		}
		
	}

}
