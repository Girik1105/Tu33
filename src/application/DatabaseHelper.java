package application;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


class DatabaseHelper {

	// JDBC driver name and database URL 
	static final String JDBC_DRIVER = "org.h2.Driver";   
	static final String DB_URL = "jdbc:h2:./data/firstDatabase";  

	//  Database credentials 
	static final String USER = "sa"; 
	static final String PASS = ""; 

	private Connection connection = null;
	private Statement statement = null; 
	//	PreparedStatement pstmt

	public void connectToDatabase() throws SQLException {
		try {
			Class.forName(JDBC_DRIVER); // Load the JDBC driver
			System.out.println("Connecting to database...");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			statement = connection.createStatement(); 
			System.out.println("Database connected");
			createTables();  // Create the necessary tables if they don't exist
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver not found: " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Database connection error: " + e.getMessage());
		}
	}

	private void createTables() throws SQLException {
		String userTable = "CREATE TABLE IF NOT EXISTS cse360users ("
				+ "id INT AUTO_INCREMENT PRIMARY KEY, "
				+ "username VARCHAR(255) UNIQUE, "
				+ "password VARCHAR(100), "
//				+ "role VARCHAR(20), "
				+ "email VARCHAR(255) UNIQUE, "
				+ "first_name VARCHAR(100), "
				+ "middle_name VARCHAR(100), "
				+ "last_name VARCHAR(100), "
				+ "preferred_name VARCHAR(100))";
		statement.execute(userTable);
	}

	// Check if the database is empty
	public boolean isDatabaseEmpty() throws SQLException {
		String query = "SELECT COUNT(*) AS count FROM cse360users";
		ResultSet resultSet = statement.executeQuery(query);
		if (resultSet.next()) {
			return resultSet.getInt("count") == 0;
		}
		return true;
	}

	public void register(String username, String password) throws SQLException {
		String insertUser = "INSERT INTO cse360users (username, password) VALUES (?, ?)";
		try (PreparedStatement pstmt = connection.prepareStatement(insertUser)) {
			pstmt.setString(1, username);
			pstmt.setString(2, password);
//			pstmt.setString(3, role);
			pstmt.executeUpdate();
		}
	}
//	public void register(String email, String password, String role) throws SQLException {
//		String insertUser = "INSERT INTO cse360users (email, password, role) VALUES (?, ?, ?)";
//		try (PreparedStatement pstmt = connection.prepareStatement(insertUser)) {
//			pstmt.setString(1, email);
//			pstmt.setString(2, password);
//			pstmt.setString(3, role);
//			pstmt.executeUpdate();
//		}
//	}

	public boolean login(String username, String password) throws SQLException {
		String query = "SELECT * FROM cse360users WHERE username = ? AND password = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setString(1, username);
			pstmt.setString(2, password);
//			pstmt.setString(3, role);
			try (ResultSet rs = pstmt.executeQuery()) {
				return rs.next();
			}
		}
	}
	
	public boolean doesUserExist(String username) {
	    String query = "SELECT COUNT(*) FROM cse360users WHERE username = ?";
	    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
	        
	        pstmt.setString(1, username);
	        ResultSet rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	            // If the count is greater than 0, the user exists
	            return rs.getInt(1) > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false; // If an error occurs, assume user doesn't exist
	}
	
	public void printAllUsers() throws SQLException {
	    String query = "SELECT * FROM cse360users";
	    try {
	        connectToDatabase();
	        try (Statement stmt = connection.createStatement();
	             ResultSet rs = stmt.executeQuery(query)) {
	            System.out.println("ID\tUsername\t\tPassword\t\tFirst Name\tMiddle Name\tLast Name\tPreferred Name");
	            System.out.println("-----------------------------------------------------------------------------------------------------------");

	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String username = rs.getString("username");
	                String password = rs.getString("password");
//	                String role = rs.getString("role");
	                String firstName = rs.getString("first_name");
	                String middleName = rs.getString("middle_name");
	                String lastName = rs.getString("last_name");
	                String preferredName = rs.getString("preferred_name");

	                // Handle null values to avoid MissingFormatArgumentException
	                System.out.printf("%d\t%s\t%s\t%s\t%s\t%s\t%s\n", 
	                                  id, 
	                                  (username != null ? username : "N/A"),
	                                  (password != null ? password : "N/A"),
//	                                  (role != null ? role : "N/A"),
	                                  (firstName != null ? firstName : "N/A"),
	                                  (middleName != null ? middleName : "N/A"),
	                                  (lastName != null ? lastName : "N/A"),
	                                  (preferredName != null ? preferredName : "N/A"));
	            }
	        }
	    } finally {
	        closeConnection();
	    }
	}

//	public void displayUsersByAdmin() throws SQLException{
//		String sql = "SELECT * FROM cse360users"; 
//		Statement stmt = connection.createStatement();
//		ResultSet rs = stmt.executeQuery(sql); 
//
//		while(rs.next()) { 
//			// Retrieve by column name 
//			int id  = rs.getInt("id"); 
//			String  email = rs.getString("email"); 
//			String password = rs.getString("password"); 
//			String role = rs.getString("role");  
//
//			// Display values 
//			System.out.print("ID: " + id); 
//			System.out.print(", Age: " + email); 
//			System.out.print(", First: " + password); 
//			System.out.println(", Last: " + role); 
//		} 
//	}
	
	public void displayUsersByUser() throws SQLException{
		String sql = "SELECT * FROM cse360users"; 
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql); 

		while(rs.next()) { 
			// Retrieve by column name 
			int id  = rs.getInt("id"); 
			String  email = rs.getString("email"); 
			String password = rs.getString("password"); 
			String role = rs.getString("role");  

			// Display values 
			System.out.print("ID: " + id); 
			System.out.print(", Age: " + email); 
			System.out.print(", First: " + password); 
			System.out.println(", Last: " + role); 
		} 
	}
	
	public void updateUserProfile(String email, String firstName, String middleName, String lastName, String prefName) throws SQLException {
	    String updateProfile = "UPDATE cse360users SET first_name = ?, middle_name = ?, last_name = ?, preferred_name = ? WHERE email = ?";
	    try (PreparedStatement pstmt = connection.prepareStatement(updateProfile)) {
	        pstmt.setString(1, firstName);
	        pstmt.setString(2, middleName);
	        pstmt.setString(3, lastName);
	        pstmt.setString(4, prefName);
	        pstmt.setString(5, email);
	        pstmt.executeUpdate();
	    }
	}


	public void closeConnection() {
		try{ 
			if(statement!=null) statement.close(); 
		} catch(SQLException se2) { 
			se2.printStackTrace();
		} 
		try { 
			if(connection!=null) connection.close(); 
		} catch(SQLException se){ 
			se.printStackTrace(); 
		} 
	}

}
