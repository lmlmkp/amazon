package com.st.cart.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.st.cart.domain.Book;
import com.st.cart.domain.Category;
import com.st.cart.service.CategoryService;
import com.st.cart.service.GoodsService;
import com.st.cart.service.impl.CategoryServiceImpl;
import com.st.cart.service.impl.GoodsServiceImpl;
import com.st.cart.utils.PageBean;

public class BookServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	private CategoryService categoryService = new CategoryServiceImpl(); 
	private GoodsService goodsService = new GoodsServiceImpl(); 
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageStr = req.getParameter("page");
		String categoryStr = req.getParameter("category");
		String search = req.getParameter("search");
		
		int currentPage = 1;
		if(null != pageStr && !"".equals(pageStr.trim())) {
			try {
				currentPage = Integer.parseInt(pageStr);
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		int category = -1;
		if(null != categoryStr && !"".equals(categoryStr.trim())) {
			try {
				category = Integer.parseInt(categoryStr);
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		List<Category> categoryList = categoryService.getAll();
		PageBean<Book> pageBean = goodsService.getPageDatas(category, search, currentPage);
		
		req.setAttribute("pageBean", pageBean);
		req.setAttribute("categoryList", categoryList);
		
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
