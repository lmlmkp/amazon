package com.st.manager.service.impl;

import java.util.List;

import com.st.manager.dao.CategoryDao;
import com.st.manager.dao.impl.CategoryDaoImpl;
import com.st.manager.domain.Category;
import com.st.manager.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	private CategoryDao categoryDao = new CategoryDaoImpl(); 
	
	@Override
	public List<Category> getAll() {
		return categoryDao.getAll();
	}
}
