package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class DBService {
	
	public Connection connectDB() {
		Dotenv dotenv = Dotenv.load();
		try {
			return DriverManager.getConnection(dotenv.get("DATABASE_URL"), dotenv.get("DB_USERNAME"), dotenv.get("DB_PASSWORD"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
