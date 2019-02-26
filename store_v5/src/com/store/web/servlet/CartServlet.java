package com.store.web.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.domain.Cart;
import com.store.domain.CartItem;
import com.store.domain.Product;
import com.store.service.ProductService;
import com.store.service.serviceImp.ProductServiceImp;
import com.store.web.base.BaseServlet;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends BaseServlet {
	//添加购物项到购物车
		public String addCartItemToCart(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			//从session获取购物车
			Cart cart=(Cart)req.getSession().getAttribute("cart");
			if(null==cart){
			  //如果获取不到,创建购物车对象,放在session中
				cart=new Cart();
				req.getSession().setAttribute("cart", cart);
			}
	   		//如果获取到,使用即可
			//获取到商品id,数量
			String pid=req.getParameter("pid");
			int num=Integer.parseInt(req.getParameter("quantity"));
			//通过商品id查询都商品对象
			ProductService ProductService=new ProductServiceImp();
			Product product=ProductService.findProductByPid(pid);	
			//获取到待购买的购物项
			CartItem cartItem=new CartItem();
			cartItem.setNum(num);
			cartItem.setProduct(product);

			//调用购物车上的方法
			cart.addCartItemToCar(cartItem);
			
			//重定向到/jsp/cart.jsp
			resp.sendRedirect(req.getContextPath()+"/jsp/cart.jsp");
			//return "/jsp/cart.jsp";
			return  null;
		}
		public String delCartItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
			//获取到被删除商品pid
			String pid=request.getParameter("pid");
			Cart cart=(Cart)request.getSession().getAttribute("cart");
			cart.delCart(pid);
			response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
			return null;
		}
		//clearCart
		public String clearCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			Cart cart=(Cart)request.getSession().getAttribute("cart");
			cart.clearCart();
			response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
			return null;
		}
}
