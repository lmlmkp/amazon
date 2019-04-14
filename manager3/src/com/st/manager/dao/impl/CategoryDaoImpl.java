package com.st.manager.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.st.manager.dao.CategoryDao;
import com.st.manager.domain.Category;

import com.st.mybatis.utils.SqlSessionUtils;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public List<Category> getAll() {
	  SqlSession session = SqlSessionUtils.getSqlSession();
		
	  CategoryDao categoryDao = session.getMapper(CategoryDao.class);
	  List<Category> list = categoryDao.getAll();
	  return list;
	}

	@Override
	public String getCategoryById(int id) {
		// TODO Auto-generated method stub
		 SqlSession session = SqlSessionUtils.getSqlSession();
			
		  CategoryDao categoryDao = session.getMapper(CategoryDao.class);
		  String Category =categoryDao.getCategoryById(id);
		  return  Category;
	}
}
