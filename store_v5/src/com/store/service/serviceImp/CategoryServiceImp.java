package com.store.service.serviceImp;

import java.util.List;

import com.store.dao.CategoryDao;
import com.store.dao.daoImp.CategoryDaoImp;
import com.store.domain.Category;
import com.store.service.CategoryService;

public class CategoryServiceImp implements CategoryService {

	@Override
	public List<Category> getAllCats() throws Exception {
		// TODO Auto-generated method stub
		CategoryDao CategoryDao=new CategoryDaoImp();
		return CategoryDao.getAllCats();
	}

}
