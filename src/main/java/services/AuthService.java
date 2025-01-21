package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import helpers.LoginRequest;
import io.github.cdimascio.dotenv.Dotenv;

public class AuthService {
	public boolean attemptLogin(LoginRequest request) {
		if(request != null) {
			try {
				// Connect to database,
				// then check if there are any matches to the given username and password in request
				Dotenv dotenv = Dotenv.load();
				System.out.println();
				Connection conn =  DriverManager.getConnection(dotenv.get("DATABASE_URL"), dotenv.get("DB_USERNAME"), dotenv.get("DB_PASSWORD"));
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
