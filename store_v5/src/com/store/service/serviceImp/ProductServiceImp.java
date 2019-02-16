package com.store.service.serviceImp;

import java.util.List;

import com.store.dao.ProductDao;
import com.store.dao.daoImp.ProductDaoImp;
import com.store.domain.Product;
import com.store.service.ProductService;

public class ProductServiceImp implements ProductService {
	ProductDao productDao=new ProductDaoImp();
	
	@Override
	public Product findProductByPid(String pid) throws Exception {
		// TODO Auto-generated method stub
		return productDao.findProductByPid(pid);
	}

	@Override
	public List<Product> findHots() throws Exception {
		// TODO Auto-generated method stub
		
		return productDao.findHots();
	}

	@Override
	public List<Product> findNews() throws Exception {
		// TODO Auto-generated method stub
		return productDao.findNews();
	}

}
