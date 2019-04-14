package com.st.mybatis.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionUtils {
	
	private static SqlSessionFactory factory;
	
	static {
		try {
			InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 获取SqlSession
	 * @return
	 */
	public static SqlSession getSqlSession() {
		return factory.openSession();
	}
}
