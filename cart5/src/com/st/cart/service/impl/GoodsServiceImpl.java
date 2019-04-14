package com.st.cart.service.impl;

import java.util.List;

import com.st.cart.dao.GoodsDao;
import com.st.cart.dao.impl.GoodsDaoImpl;
import com.st.cart.domain.Book;
import com.st.cart.service.GoodsService;
import com.st.cart.utils.PageBean;

public class GoodsServiceImpl implements GoodsService {

	private GoodsDao goodsDao = new GoodsDaoImpl(); 
	
	@Override
	public PageBean<Book> getPageDatas(int categoryId, String search, int currentPage) {
		return goodsDao.getPageDatas(categoryId, search, currentPage);
	}

	@Override
	public List<String> getBooksName(String text) {
		return goodsDao.getBooksName(text);
	}

	@Override
	public List<Book> getBooksByIds(List<Integer> ids) {
		return goodsDao.getBooksByIds(ids);
	}
}
