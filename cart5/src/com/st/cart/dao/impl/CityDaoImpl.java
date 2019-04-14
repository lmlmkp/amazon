package com.st.cart.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.st.cart.dao.CityDao;
import com.st.cart.domain.City;
import com.st.cart.domain.Province;
import com.st.cart.utils.JdbcUtils;

public class CityDaoImpl implements CityDao {

	@Override
	public List<City> getCitysByProvinceId(int provinceId) {
		List<City> list = new ArrayList<>();
		Connection conn = JdbcUtils.getConnection();
		String sql = "select id, name from city where provinceId = ?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, provinceId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				City c = new City(rs.getInt("id"), rs.getString("name"));
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
