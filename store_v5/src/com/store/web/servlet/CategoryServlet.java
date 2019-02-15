package com.store.web.servlet;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.domain.Category;
import com.store.service.CategoryService;
import com.store.service.serviceImp.CategoryServiceImp;
import com.store.utils.JedisUtils;
import com.store.web.base.BaseServlet;

import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/CategoryServlet")
public class CategoryServlet extends BaseServlet {
	public String findAllCats(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		Jedis jedis=JedisUtils.getJedis();
		String jsonStr=jedis.get("allCats");
		//如果内存中没有数据就获取后返回；有数据了就直接返回
		if(null==jsonStr||"".equals(jsonStr)) {
			//调用业务层获取全部分类；将全部分类转为json格式；并响应到客户端
			CategoryService categoryService=new CategoryServiceImp();
			List<Category> list=categoryService.getAllCats();
			jsonStr=JSONArray.fromObject(list).toString();
		}
		resp.setContentType("application/json;charset=utf-8");
		resp.getWriter().print(jsonStr);
		return null;
	}
}
