package com.st.cart.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.st.cart.dao.DistrictDao;
import com.st.cart.domain.City;
import com.st.cart.domain.District;
import com.st.cart.utils.JdbcUtils;

public class DistrictDaoImpl implements DistrictDao {

	@Override
	public List<District> getDistrictsByCityId(int cityId) {
		List<District> list = new ArrayList<>();
		Connection conn = JdbcUtils.getConnection();
		String sql = "select id, name from district where cityId = ?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cityId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				District c = new District(rs.getInt("id"), rs.getString("name"));
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
