package com.st.cart.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.st.cart.dao.ProvinceDao;
import com.st.cart.domain.Category;
import com.st.cart.domain.Province;
import com.st.cart.utils.JdbcUtils;

public class ProvinceDaoImpl implements ProvinceDao {

	@Override
	public List<Province> getAll() {
		List<Province> list = new ArrayList<>();
		Connection conn = JdbcUtils.getConnection();
		String sql = "select id, name from province";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Province p = new Province(rs.getInt("id"), rs.getString("name"));
				list.add(p);
			}
			JdbcUtils.closeResource(conn, ps, rs);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
