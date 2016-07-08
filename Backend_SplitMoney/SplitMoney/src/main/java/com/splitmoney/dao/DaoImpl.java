package com.splitmoney.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoImpl {
private Connection conn = null;
	
	public Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String connectionUrl = "jdbc:mysql://localhost:3306/splitwise";
			String connectionUser = "root";
			String connectionPassword = "newuser@123";
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
		return conn;
	}
	
	public void CloseConnection(Connection connection) throws SQLException{
			connection.close();
		
	}
	
	
}
