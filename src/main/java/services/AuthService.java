package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import helpers.LoginRequest;


public class AuthService {
	
	private DBService dbs = new DBService();
	
	/*
	 * Returns true or false based on if the user exists.
	 */
	public boolean attemptLogin(LoginRequest request) {
		if(request != null) {
			try {
				// Connect to database,
				// then check if there are any matches to the given username and password in request
				Connection conn =  dbs.connectDB();
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
				stmt.setString(1, request.getUsername());
				stmt.setString(2, request.getPassword());
				
				ResultSet rs = stmt.executeQuery();
				
				/*
				 * Result set starts at pointer before list. If there is a "next" then the list is not empty
				 * meaning a user has been found given that only one user matches the request
				 */
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
