package com.st.cart.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
	
	public static Connection conn;
	private static ComboPooledDataSource cpds;
	
	static {
		cpds = new ComboPooledDataSource("mysql");
	}
	
	/**
	 * 获取连接
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			conn = cpds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void closeResource(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if(null != rs) {
				rs.close();
			}
			if(null != ps) {
				ps.close();
			}
			if(null != conn) {
				conn.close();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		/**
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conn = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/cart", "root", "");
		
		PreparedStatement ps = conn.prepareStatement("select * from user");
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			System.out.println(rs.getObject(2));
		}
		
		rs.close();
		ps.close();
		conn.close();
		*/
		
		ComboPooledDataSource cpds = new ComboPooledDataSource("mysql");
		
		Connection conn = cpds.getConnection();
		
		PreparedStatement ps = conn.prepareStatement("select * from user");
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			System.out.println(rs.getObject(2));
		}
	}
}
