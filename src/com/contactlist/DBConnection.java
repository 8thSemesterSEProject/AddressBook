package com.contactlist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	private static String username = "b8XcYHmAD4txdLpu";
	private static String password = "Dw3JadGY7VdZZht3";
	private static String connectionURL = "jdbc:derby:./data;user=" + username
			+ ";password=" + password;
	private static Statement stmt;
	private static Connection conn;

	public static void connect() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(connectionURL);
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static int executeUpdate(String sql) throws SQLException {
		if (conn == null)
			connect(); // System.out.println(sql);
		return stmt.executeUpdate(sql);
	}

	public static ResultSet executeQuery(String sql) throws SQLException {
		if (conn == null)
			connect(); // System.out.println(sql);
		return stmt.executeQuery(sql);
	}
}