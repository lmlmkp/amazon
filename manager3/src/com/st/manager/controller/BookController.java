package com.st.manager.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.ibatis.annotations.Param;
import java.io.File;
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.st.springmvc.utils.TimeUtils;

import com.st.manager.domain.Book;
import com.st.manager.domain.Category;
import com.st.manager.service.BookService;
import com.st.manager.service.CategoryService;
import com.st.manager.service.impl.BookServiceImpl;
import com.st.manager.service.impl.CategoryServiceImpl;
import com.st.manager.utils.Commons;
import com.st.manager.utils.PageBean;


@Controller
public class BookController {
	private BookService bookService = new BookServiceImpl(); 
	private CategoryService categoryService = new CategoryServiceImpl();
	
	@RequestMapping("/first")
	public void first(String tag,HttpServletRequest req) {
		if(null == tag || "".equals(tag.trim())) {
		list(1,req);
	   }
	}
	@RequestMapping("/test")
	public String list(int page,HttpServletRequest req) {
		//System.out.print(page+"--------");
			int currentPage = 1;
			PageBean<Book> pageBean = bookService.getPageBean(page);
			//System.out.print(pageBean.getCurrentPage()+"---------");
			//System.out.println(pageBean.getDatas());
			
			 req.setAttribute("pageBean", pageBean);
			return  "forward:first.jsp";  
	}

	@RequestMapping("/toedit")
	public String toEdit(int bookId, HttpServletRequest req) {
		
		Book book = bookService.getBookById(bookId);
		req.setAttribute("book", book);
		List<Category> list = categoryService.getAll();
		req.setAttribute("list", list);
		return  "forward:edit-book.jsp";  
	}
	@RequestMapping("/toadd")
	public String toAdd(HttpServletRequest req)  {
		List<Category> list = categoryService.getAll();
		req.setAttribute("list", list);
		return  "forward:add-book.jsp"; 
	}
	@RequestMapping("/todelete")
	public String toDelete(int bookId)  {
	 	bookService.delete(bookId);
		return "redirect:first";
	}

	
	@RequestMapping("/add1")
	public String  Add(MultipartHttpServletRequest request, int id, String tag,String name, BigDecimal price,int categoryId){
		Book book=new Book();
        book.setId(id);book.setName(name);book.setPrice(price);book.setCategoryId(categoryId);
		//System.out.print(tag);
		MultipartFile multipartFile = request.getFile("cover");
		String fileName = multipartFile.getOriginalFilename(); //获取文件名
		//String newFileName = TimeUtils.currentDateToString() + fileName.substring(fileName.IndexOf("."));
		String newFileName=null;
		if(null != fileName && !"".equals(fileName.trim())) {
		 newFileName = TimeUtils.currentDateToString() 
					+ fileName.substring(fileName.indexOf("."));
			book.setCover(newFileName);
		}
		
		try {
			multipartFile.transferTo(new File("D:/nginx-1.10.3/html/" + newFileName));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		if("edit".equals(tag)) { 
			bookService.update(book);
			}else {
				//System.out.print("进入");
			 bookService.add(book);
			}
	
		return "redirect:first";
	}
	

}
