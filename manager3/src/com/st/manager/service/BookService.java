package com.st.manager.service;

import com.st.manager.domain.Book;
import com.st.manager.utils.PageBean;

public interface BookService {
	public PageBean<Book> getPageBean(int currentPage);
	
	public Book getBookById(int id);
	
	public void update(Book book);

	public void delete(int id);
	public void add(Book book);

	
	
}
