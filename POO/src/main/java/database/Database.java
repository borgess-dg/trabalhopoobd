package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private final String databaseURL = "jdbc:postgresql://localhost:5432/tabajara_db";
	private final String user = "postgres";
	private final String password = "159753";
	
	public Connection connect() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(databaseURL, user, password);
		}
		catch (SQLException error) {
			System.out.println(error.getMessage());
		}
		return connection;
	}
}
