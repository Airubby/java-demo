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

}
