package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	// Connection object has been created.
	Connection myConn = null;

	/**
	 * This method creates a connection in MySQL database
	 * 
	 * @return Connection is returned
	 */
	public Connection getConnections() {
		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Connecting to a host using the User name and Password.
			myConn = DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/510labs?autoReconnect=true&useSSL=false","db510","510");
			if(myConn != null)
				System.out.println("Connected to Database....");

			// Handle error for JDBC
		} catch (SQLException e) {
			e.getMessage();
		}
		// Handle error for Class.forName
		catch (ClassNotFoundException e) {
			e.getMessage();
		}

		// Returns the connection object.
		return myConn;
	}
}
