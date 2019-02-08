package com.store.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.junit.Test;

import com.store.domain.User;

public class TestBeanUtils {
	@Test
	public void test01() throws Exception {
		//模拟request.getParameterMap()
		Map<String,String[]> map=new HashMap<String,String[]>();
		map.put("username", new String[] {"tom"});
		map.put("password", new String[] {"123"});
		map.put("birthday", new String[] {"1992-2-3"});
		User user=new User();
		
		//BeanUtils找到User.class文件上有setBirthday这个方法，要执行，将"1922-3-3"转换为日期类型
		//BeanUtils不知道这个字符串的时间格式是什么，以下设置时间转换格式
		//1创建时间类型的转换器
		DateConverter dt=new DateConverter();
		//2设置转换格式
		dt.setPattern("yyyy-MM-dd");
		//3注册转换器
		ConvertUtils.register(dt, java.util.Date.class);
		
		BeanUtils.populate(user, map);
		System.out.println(user);
	}
}
