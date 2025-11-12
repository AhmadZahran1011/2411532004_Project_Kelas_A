package config;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class DbConnection {
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/laundry_apps";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	
	Connection conn;
	
	public static Connection koneksi() {
		try {
			Class.forName(JDBC_DRIVER);
			
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			System.out.println("Connection created...");
			return conn;
		}	catch (Exception e) {
			System.err.println("Connection failed... : "+ e);
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	
}
