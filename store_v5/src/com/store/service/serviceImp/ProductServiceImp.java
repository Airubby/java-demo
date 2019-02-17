package com.store.service.serviceImp;

import java.util.List;

import com.store.dao.ProductDao;
import com.store.dao.daoImp.ProductDaoImp;
import com.store.domain.PageModel;
import com.store.domain.Product;
import com.store.service.ProductService;

public class ProductServiceImp implements ProductService {
	ProductDao productDao=new ProductDaoImp();
	
	@Override
	public PageModel findProductsByCidWithPage(String cid, int curNum) throws Exception {
		// TODO Auto-generated method stub
		//1_创建PageModel对象 目的:计算分页参数
		//统计当前分类下商品个数  select count(*) from product where cid=?
		int totalRecords = productDao.findTotalRecords(cid);
		PageModel pm=new PageModel(curNum,totalRecords,12);
//		//2_关联集合 select * from product where cid =? limit ? ,?
		List list=productDao.findProductsByCidWithPage(cid,pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
//		//3_关联url
		pm.setUrl("ProductServlet?method=findProductsByCidWithPage&cid="+cid);
		return pm;
	}

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
