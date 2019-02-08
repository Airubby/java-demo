package com.store.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.store.domain.User;
import com.store.service.UserService;
import com.store.service.serviceImp.UserServiceImp;
import com.store.utils.MailUtils;
import com.store.utils.MyBeanUtils;
import com.store.utils.UUIDUtils;
import com.store.web.base.BaseServlet;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
	
	//registUI
	public String registUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/register.jsp";
	}
	//userRegist
	public String userRegist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//接受表单参数
		Map<String,String[]> map=request.getParameterMap();
		User user=new User();
		MyBeanUtils.populate(user, map);
		//为用户的其他属性赋值
		user.setUid(UUIDUtils.getId());
		user.setState(0);
		user.setCode(UUIDUtils.getCode());
		System.out.println(user);

		UserService UserService=new UserServiceImp();
		try {
			UserService.userRegist(user);
			//注册成功，向用户邮箱发送信息，跳转到提示页面
			//发送邮件
			MailUtils.sendMail(user.getEmail(),user.getCode());
			request.setAttribute("msg", "用户注册成功，请激活！");
		} catch (Exception e) {
			//注册失败，跳转到提示页面
			request.setAttribute("msg", "用户注册失败");
			// TODO: handle exception
		}
		return "/jsp/info.jsp";
		
	}
	//active
	public String active(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//服务端获取到激活码,和数据库中已经存在的激活码对比,如果存在,激活成功,更改用户激活状态1,转发到登录页面,提示:激活成功,请登录.
		String code=request.getParameter("code");
		//调用业务层功能:根据激活码查询用户 select * from user where code=?
		UserService UserService=new UserServiceImp();
		boolean flag=UserService.userActive(code);
		if(flag==true) {
			//转发到登录页面,提示:激活成功,请登录
			request.setAttribute("msg", "用户激活成功,请登录");
			return "/jsp/login.jsp";
		}else {
			//转发到提示页面,提示:激活失败
			request.setAttribute("msg", "用户激活成功,请登录");
			return "/jsp/info.jsp";
		}
	}
	//loginUI
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		return "/jsp/login.jsp";
	}
	//userLogin
	public String userLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user=new User();
		MyBeanUtils.populate(user, request.getParameterMap());
		//调用业务登录功能
		UserService UserService=new UserServiceImp();
		User user02=null;
		try {
			user02=UserService.userLogin(user);
			//用户登录成功，将用户信息放入session
			request.getSession().setAttribute("loginUser", user02);
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			String msg=e.getMessage();
			System.out.println(msg);
			request.setAttribute("msg", msg);
			return "/jsp/login.jsp";
		}
	}
	//logOut
	public String logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//用户退出,清空用户session
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath()+"/jsp/index.jsp");
		return null;
	}

}
