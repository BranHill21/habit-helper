package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import helpers.LoginRequest;
import helpers.SignupRequest;
import helpers.SignupResponse;


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
	
	/*
	 * Returns true or false based on if the attempt to sign up for an account is successful
	 */
	public SignupResponse attemptSignup(SignupRequest request) {
		SignupResponse response = new SignupResponse();
		try {
			Connection conn =  dbs.connectDB();
			PreparedStatement stmt = conn.prepareStatement("SELECT " +
		               "(CASE WHEN EXISTS (SELECT 1 FROM users WHERE username = ?) THEN true ELSE false END) AS is_username_taken, " +
		               "(CASE WHEN EXISTS (SELECT 1 FROM users WHERE email = ?) THEN true ELSE false END) AS is_email_taken");
			
			stmt.setString(1, request.getUsername());
			stmt.setString(2, request.getEmail());
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				boolean isUsernameTaken = rs.getBoolean("is_username_taken");
	            boolean isEmailTaken = rs.getBoolean("is_email_taken");
	            
	            if(isUsernameTaken && isEmailTaken)
	            {
	            	response.setMessage("Account already exist. Please switch to log in page or create a new account.");
	            	response.setSuccess(false);
	            }
	            else if(isUsernameTaken) {
	            	response.setMessage("That username is taken. Please try a different username.");
	            	response.setSuccess(false);
	            }
	            else if(isEmailTaken) {
	            	response.setMessage("That email is taken. Please try a different email.");
	            	response.setSuccess(false);
	            }
	            else {
	            	response.setMessage("Account Successfully Created!");
	            	response.setSuccess(true);
	            }
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return response;
	}
}
