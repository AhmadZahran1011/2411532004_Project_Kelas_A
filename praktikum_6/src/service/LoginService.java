package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.DbConnection;
import model.User;

public class LoginService {
	public boolean authenticate(User user) {
		String query = "SELECT * FROM user WHERE username = ? AND password = ?";
		
		try (Connection connection = DbConnection.koneksi();
			PreparedStatement statement = connection.prepareStatement(query)){
		statement.setString(1, user.getUsername());
		statement.setString(2, user.getPassword());
		
		ResultSet resultSet = statement.executeQuery();
		return resultSet.next();
	  }	catch (SQLException e) {
		  e.printStackTrace();
	  }
		
		return false;
	}
}
