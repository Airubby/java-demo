package com.store.dao;

import java.sql.SQLException;

import com.store.domain.User;

public interface UserDao {

	void userRegist(User user) throws SQLException;

}
