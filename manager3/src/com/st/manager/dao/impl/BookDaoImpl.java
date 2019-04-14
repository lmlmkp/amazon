package com.st.manager.dao.impl;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.st.manager.dao.BookDao;
import com.st.manager.domain.Book;
import com.st.manager.utils.Commons;
import com.st.manager.utils.PageBean;
import com.st.mybatis.utils.SqlSessionUtils;

public class BookDaoImpl implements BookDao {
	 
	@Override
	public PageBean<Book> getPageBean(int currentPage) {
		
		SqlSession session = SqlSessionUtils.getSqlSession();
		BookDao bookDao  = session.getMapper(BookDao.class);
		
		PageBean<Book> pageBean = new PageBean<Book>();
		
		pageBean.setCurrentPage(currentPage);
		
		int beginIndex = (currentPage - 1) * 10;
	
			//List<Book> list = new LinkedList<>();  //查询的每页的数据
			//list =bookDao.getPageDatas(beginIndex);
		List<Book> list = new LinkedList<>();
		List<Map<String, Object>> listbook = bookDao.getPage(beginIndex);
		for(Map<String, Object> map : listbook) {
			Book book=new Book();
			book.setId((int) map.get("id"));
			book.setName((String) map.get("name"));
			book.setCover((String) map.get("cover"));
			book.setPrice((BigDecimal) map.get("price"));
			book.setCreateTime((Timestamp) map.get("create_time"));
			book.setUpdateTime((Timestamp) map.get("update_time"));
			book.setCategory((String) map.get("cname"));
			list.add(book);
			
		}
		
			pageBean.setDatas(list);

			int total = bookDao.getTotal();//获得总数
			
			pageBean.setRowCount(total);
			 session.close();
			return pageBean;
	}

	@Override
	public Book getBookById(int id) {
		SqlSession session = SqlSessionUtils.getSqlSession();
		BookDao bookDao  = session.getMapper(BookDao.class);
		Book book=new Book();
		book=bookDao.getBookById(id);
		 session.close();
		return book;
	
	}

	@Override
	public void update(Book book) {
		SqlSession session = SqlSessionUtils.getSqlSession();
		BookDao bookDao  = session.getMapper(BookDao.class);
			/** 
			 * 如果封面不为空，表示用户更改的图片，旧图片需要删除
			 */
			if(null != book.getCover()) {
				String cover = bookDao.getBookCover(book.getId());
				File file = new File(Commons.localPath + cover);
				
				if(file.exists()) {
					file.delete();
				}
			}else {
				String cover = bookDao.getBookCover(book.getId());
				book.setCover(cover);
			}
          book.setUpdateTime(new Timestamp(new Date().getTime()));
          book.setCreateTime(new Timestamp(new Date().getTime()));
		 bookDao.update(book);
		 session.commit();
		 session.close();
	}

	@Override
	public void delete(int id) {
		SqlSession session = SqlSessionUtils.getSqlSession();
		BookDao bookDao  = session.getMapper(BookDao.class);
	
	
            String cover = bookDao.getBookCover(id);
			File file = new File(Commons.localPath + cover);
				if(file.exists()) {
					file.delete();
				}
				
				bookDao.delete(id);
				session.commit();
				 session.close();
	}

	@Override
	public void add(Book book) {
		// TODO Auto-generated method stub
		SqlSession session = SqlSessionUtils.getSqlSession();
		BookDao bookDao  = session.getMapper(BookDao.class);
		book.setUpdateTime(new Timestamp(new Date().getTime()));
        book.setCreateTime(new Timestamp(new Date().getTime()));
		bookDao.add(book);
		session.commit();
		 session.close();
	}

	@Override
	public List<Book> getPageDatas(int beginIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getBookCover(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> pageQuery(int beginIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getPage(int beginIndex) {
		// TODO Auto-generated method stub
		
	
			SqlSession session = SqlSessionUtils.getSqlSession();
			BookDao bookDao  = session.getMapper(BookDao.class);
		
			List<Map<String, Object>> listbook = bookDao.getPage(beginIndex);
			 session.close();
			return listbook;
		
		
		
	}




		
		




	
}
