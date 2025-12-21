package DAO;

import java.sql.*;
import config.DBConnection;
import model.User;


public class UserDAO {
    private Connection conn;

    public UserDAO() throws SQLException {
        this.conn = DBConnection.getInstance().getConnection();
    }

    public boolean login(String username, String password) throws SQLException {
        String sql = "SELECT password FROM users WHERE username=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String pw = rs.getString("password");
                    return password.equals(pw);
                }
            }
        }
        return false;
    }

    public User getUser(String username) throws SQLException {
        String sql = "SELECT username, password FROM users WHERE username=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getString("username"), rs.getString("password"));
                }
            }
        }
        return null;
    }
}

