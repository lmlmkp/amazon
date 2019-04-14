package com.st.manager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.st.manager.domain.Book;
import com.st.manager.utils.PageBean;

public interface BookDao {
	public PageBean<Book> getPageBean(int currentPage);
	
	public Book getBookById(@Param("id") int id);
	public List<Book> getPageDatas( @Param("beginIndex") int beginIndex);
	public List<Map<String, Object>> getPage( @Param("beginIndex") int beginIndex);
	
	public String  getBookCover(@Param("id") int id);
	public int getTotal();
	public List<Book> pageQuery(@Param("beginIndex")int beginIndex, 	  @Param("pageSize")int pageSize); 
	public void update(Book book);
	
	public void delete(int id);
	
	public void add(Book book);
}
