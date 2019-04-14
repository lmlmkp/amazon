package com.st.cart.service;

import java.util.List;

import com.st.cart.domain.Book;
import com.st.cart.utils.PageBean;

public interface GoodsService {
	
	public PageBean<Book> getPageDatas(int categoryId, String search, int currentPage);
	
	public List<String> getBooksName(String text);
	
	public List<Book> getBooksByIds(List<Integer> ids);
}
