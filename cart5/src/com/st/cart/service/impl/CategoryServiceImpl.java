package com.st.cart.service.impl;

import java.util.List;
import com.st.cart.dao.CategoryDao;
import com.st.cart.dao.impl.CategoryDaoImpl;
import com.st.cart.domain.Category;
import com.st.cart.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	private CategoryDao categoryDao = new CategoryDaoImpl(); 
	
	@Override
	public List<Category> getAll() {
		return categoryDao.getAll();
	}
}
