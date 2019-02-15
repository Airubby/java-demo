package com.store.web.servlet;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.domain.Product;
import com.store.service.ProductService;
import com.store.service.serviceImp.ProductServiceImp;
import com.store.web.base.BaseServlet;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends BaseServlet {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
/*		//调用业务层功能：获取全部分类信息，返回集合
		CategoryService CategoryService=new CategoryServiceImp();
		List<Category> list=CategoryService.getAllCats();
		//将返回的集合放入request
		request.setAttribute("allCats", list);
		以上的这种写法是直接返回数据到页面循环显示用的，但页面跳转后没有再获取就不显示分类了，所以用header.jsp中ajax请求获取分类信息
		*/
		//调用业务层查询最新，最热商品
		ProductService productService=new ProductServiceImp();
		List<Product> list01=productService.findHots();
		List<Product> list02=productService.findNews();
		//将两个集合放到request中
		request.setAttribute("hots", list01);
		request.setAttribute("news", list02);
		//转发到真实的首页
		return "/jsp/index.jsp";
	}
}
