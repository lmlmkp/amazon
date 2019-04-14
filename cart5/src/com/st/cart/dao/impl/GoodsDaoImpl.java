package com.st.cart.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.st.cart.dao.GoodsDao;
import com.st.cart.domain.Book;
import com.st.cart.utils.Commons;
import com.st.cart.utils.JdbcUtils;
import com.st.cart.utils.PageBean;

public class GoodsDaoImpl implements GoodsDao {

	/**
	 * select b.id, b.name, b.price, b.cover 
	 * 	from books b where 1 = 1 and b.category_id = ? and b.name like ?
	 * 	limit ?, ?
	 * 
	 * root';drop table user;--
	 * 
	 * select * from user username = 'root';drop table user; -- and password = '124556';
	 */
	@Override
	public PageBean<Book> getPageDatas(int categoryId, String search, int currentPage) {
		PageBean<Book> pageBean  = new PageBean<Book>();
		pageBean.setCurrentPage(currentPage);
		
		int beginIndex = (currentPage - 1) * 9;  //开始索引位置
		
		StringBuffer sql = new StringBuffer("select b.id, b.name, b.price, b.cover ");
		sql.append(" from books b where 1 = 1 ");
		
		if(-1 != categoryId) {
			sql.append(" and b.category_id = ? ");
		}
		if(null != search && !"".equals(search.trim())) {
			sql.append(" and b.name like ? ");
		}
		
		sql.append(" limit ?, ?");
		
		Connection conn = JdbcUtils.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			
			int i = 0;
			if(-1 != categoryId) {
				ps.setInt(++i, categoryId);
			}
			if(null != search && !"".equals(search.trim())) {
				ps.setString(++i, "%" + search + "%");
			}
			
			ps.setInt(++i, beginIndex);
			ps.setInt(++i, 9);
			
			List<Book> list = new ArrayList<>();
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Book book = new Book(rs.getInt("id"), 
						rs.getString("name"), rs.getBigDecimal("price"), Commons.fileServer + rs.getString("cover"));
				list.add(book);
			}
			
			int total = getTotal(categoryId, search, conn);
			
			JdbcUtils.closeResource(conn, ps, rs);
			
			pageBean.setDatas(list);
			pageBean.setRowCount(total);
			
			return pageBean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询满足条件的总的数据量
	 * @param categoryId
	 * @param search
	 * @return
	 */
	private int getTotal(int categoryId, String search, Connection conn) {
		StringBuffer sql  = new StringBuffer("select count(*) from books b");
		sql.append(" where 1 = 1");
		
		if(-1 != categoryId) {
			sql.append(" and b.category_id = ? ");
		}
		if(null != search && !"".equals(search.trim())) {
			sql.append(" and b.name like ? ");
		}
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			int i = 0;
			if(-1 != categoryId) {
				ps.setInt(++i, categoryId);
			}
			if(null != search && !"".equals(search.trim())) {
				ps.setString(++i, "%" + search + "%");
			}
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			int total  = rs.getInt(1);
			JdbcUtils.closeResource(null, ps, rs);
			return total;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<String> getBooksName(String text) {
		Connection conn = JdbcUtils.getConnection();
		String sql = "select name from books where name like ? limit 10";
		List<String> list = new ArrayList<>();  //LinkedList  HashMap  
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + text + "%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString(1));
			}
			JdbcUtils.closeResource(conn, ps, rs);
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public List<Book> getBooksByIds(List<Integer> ids) {
		StringBuffer sql = new StringBuffer("select b.id, b.name, b.price, b.cover");
		
		List<Book> list = new ArrayList<>();
		
		sql.append(" from books b where id in(");
		
		for(int i = 0;i < ids.size(); i++) {
			if(i == ids.size() - 1) {
				sql.append("?)");
			}else {
				sql.append("?,");
			}
		}
		
		Connection conn = JdbcUtils.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			for(int i = 0; i < ids.size(); i++) {
				ps.setInt(i + 1, ids.get(i));
			}
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Book book = new Book(rs.getInt("id"), 
						rs.getString("name"), rs.getBigDecimal("price"), Commons.fileServer + rs.getString("cover"));
				list.add(book);
			}
			JdbcUtils.closeResource(conn, ps, rs);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
