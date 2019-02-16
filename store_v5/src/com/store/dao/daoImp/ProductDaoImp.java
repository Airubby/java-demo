package com.store.dao.daoImp;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.store.dao.ProductDao;
import com.store.domain.Product;
import com.store.utils.JDBCUtils;

public class ProductDaoImp implements ProductDao {

	@Override
	public List<Product> findNews() throws Exception {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM product WHERE pflag=0 ORDER BY pdate DESC LIMIT 0 , 9";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
	}

	@Override
	public List<Product> findHots() throws Exception {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM product WHERE pflag=0 AND is_hot= 1 ORDER BY pdate DESC LIMIT 0 , 9";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
	}

	@Override
	public Product findProductByPid(String pid) throws Exception {
		// TODO Auto-generated method stub
		String sql="select * from product where pid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Product>(Product.class),pid);
	}
	

}
