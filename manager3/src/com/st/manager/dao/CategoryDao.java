package com.st.manager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.st.manager.domain.Category;

public interface CategoryDao {
	public List<Category> getAll();
	public String  getCategoryById(@Param("id") int id);
}
