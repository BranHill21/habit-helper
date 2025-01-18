package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import helpers.LoginRequest;

public class AuthService {
	public boolean attemptLogin(LoginRequest request) {
		if(request != null) {
			try {
				// Connect to database,
				// then check if there are any matches to the given username and password in request
				Connection conn =  DriverManager.getConnection(System.getenv("DATABASE_URL"),System.getenv("DB_USERNAME"),System.getenv("DB_PASSWORD"));
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
				stmt.setString(1, request.getUsername());
				stmt.setString(2, request.getPassword());
				
				ResultSet rs = stmt.executeQuery();
				if(rs.next()) {
					return true;
				}
				else {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
