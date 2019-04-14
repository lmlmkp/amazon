package com.st.cart.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.st.cart.dao.CategoryDao;
import com.st.cart.domain.Category;
import com.st.cart.utils.JdbcUtils;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public List<Category> getAll() {
		List<Category> list = new ArrayList<>();
		Connection conn = JdbcUtils.getConnection();
		String sql = "select * from category";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Category c = new Category(rs.getInt("id"), rs.getString("name"));
				list.add(c);
			}
			JdbcUtils.closeResource(conn, ps, rs);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
