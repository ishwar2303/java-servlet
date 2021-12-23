package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConDao {
	String url = "jdbc:mysql://localhost:3306/";
	String database = "quizwit";
	String username = "root";
	String password = "23031999";
	
	public Connection connection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url + database, username, password);
		return con;
	}
}
