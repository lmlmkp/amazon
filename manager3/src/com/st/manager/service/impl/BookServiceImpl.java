package com.st.manager.service.impl;

import org.apache.ibatis.session.SqlSession;

import com.st.manager.dao.BookDao;
import com.st.manager.dao.impl.BookDaoImpl;
import com.st.manager.domain.Book;
import com.st.manager.service.BookService;
import com.st.manager.utils.PageBean;
import com.st.mybatis.utils.SqlSessionUtils;

public class BookServiceImpl implements BookService {

	private BookDao bookDao = new BookDaoImpl(); 

	
	@Override
	public PageBean<Book> getPageBean(int currentPage) {
	
		return bookDao.getPageBean(currentPage);
	}

	@Override
	public Book getBookById(int id) {
		return bookDao.getBookById(id);
	}

	@Override
	public void update(Book book) {
		bookDao.update(book);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		bookDao.delete(id);
	}

	@Override
	public void add(Book book) {
		// TODO Auto-generated method stub
		bookDao.add(book);
	}
}
