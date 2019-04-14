package com.st.cart.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.st.cart.domain.Book;
import com.st.cart.service.GoodsService;
import com.st.cart.service.impl.GoodsServiceImpl;

public class CartServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	
	private GoodsService goodsService = new GoodsServiceImpl(); 

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tag = req.getParameter("tag");
		if("1".equals(tag)) {
			searchDisplay(req, resp);
		}else if("2".equals(tag)){
			cartList(req, resp);
		}
	}
	
	public void searchDisplay(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String text = req.getParameter("text");
		List<String> list = goodsService.getBooksName(text);
		outJson(resp, list);
	}
	
	/**
	 * 该方法是获取购物车中所有的信息
	 */
	public void cartList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idString = req.getParameter("ids");
		String[] idArray = idString.split(",");
		List<Integer> idList = new ArrayList<>();
		
		for(String idStr : idArray) {
			idList.add(Integer.parseInt(idStr));
		}
		
		List<Book> list = goodsService.getBooksByIds(idList);
		outJson(resp, list);
	}
}

