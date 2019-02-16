package com.store.web.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.domain.Product;
import com.store.service.ProductService;
import com.store.service.serviceImp.ProductServiceImp;
import com.store.web.base.BaseServlet;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends BaseServlet {
	public String findProductByPid(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pid=request.getParameter("pid");
		//根据pid查询商品
		ProductService productService=new ProductServiceImp();
		Product product=productService.findProductByPid(pid);
		request.setAttribute("product", product);
		return "jsp/product_info.jsp";
	}
}
