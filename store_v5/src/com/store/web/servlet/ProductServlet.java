package com.store.web.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.domain.PageModel;
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
	public String findProductsByCidWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取cid,num
		String cid=request.getParameter("cid");
		int curNum=Integer.parseInt(request.getParameter("num"));
		//调用业务层功能:以分页形式查询当前类别下商品信息
		//返回PageModel对象(1_当前页商品信息2_分页3_url)
		ProductService ProductService=new ProductServiceImp();
		PageModel pm=ProductService.findProductsByCidWithPage(cid,curNum);
		//将PageModel对象放入request
		request.setAttribute("page", pm);
		//转发到/jsp/product_list.jsp
		return  "/jsp/product_list.jsp";
	}
	
}
