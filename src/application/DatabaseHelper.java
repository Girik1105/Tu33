package application;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.bouncycastle.util.Arrays;

import Encryption.EncryptionHelper;
import Encryption.EncryptionUtils;

/**
 * DatabaseHelper class manages the database operations for users and articles.
 */
public class DatabaseHelper {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/firstDatabase";

    // Database credentials
    static final String USER = "sa";
    static final String PASS = "";

    private Connection connection = null;
    private Statement statement = null;

    private EncryptionHelper encryptionHelper;

    /**
     * Constructor initializes the EncryptionHelper.
     */
    public DatabaseHelper() throws Exception {
        encryptionHelper = new EncryptionHelper();
    }

    /**
     * Connects to the database and creates necessary tables.
     */
    public void connectToDatabase() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER); // Load the JDBC driver
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            createTables(); // Create the necessary tables if they don't exist
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        }
    }
    /**
     * Connects to an in-memory H2 database for testing purposes and creates necessary tables.
     * This method is used during testing to avoid modifying the actual database on disk.
     * The in-memory database exists only while the application is running.
     */
    public void connectToInMemoryDatabase() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER); // Load the JDBC driver
            System.out.println("Connecting to in-memory database...");
            // Use an in-memory database URL
            connection = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", USER, PASS);
            statement = connection.createStatement();
            createTables(); // Create the necessary tables if they don't exist
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        }
    }

    /**
     * Creates the users and articles tables if they do not exist.
     */
    private void createTables() throws SQLException {
        String userTable = "CREATE TABLE IF NOT EXISTS cse360users ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "email VARCHAR(255) UNIQUE, "
                + "password VARCHAR(255), "
                + "role VARCHAR(20))";
        statement.execute(userTable);

        String articlesTable = "CREATE TABLE IF NOT EXISTS articles ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "title TEXT, "
                + "authors TEXT, "
                + "abstract TEXT, "
                + "keywords TEXT, "
                + "body TEXT, "
                + "references TEXT)";
        statement.execute(articlesTable);

        
    }

    /**
     * Checks if the database is empty (no users registered).
     *
     * @return True if the database is empty, false otherwise.
     */
    public boolean isDatabaseEmpty() throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM cse360users";
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            return resultSet.getInt("count") == 0;
        }
        return true;
    }

    /**
     * Registers a new user with encrypted password.
     *
     * @param email    User email.
     * @param password User password.
     * @param role     User role.
     */
    public void register(String email, String password, String role) throws Exception {
        String encryptedPassword = Base64.getEncoder().encodeToString(
                encryptionHelper.encrypt(password.getBytes(), EncryptionUtils.getInitializationVector(email.toCharArray()))
        );

        String insertUser = "INSERT INTO cse360users (email, password, role) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertUser)) {
            pstmt.setString(1, email);
            pstmt.setString(2, encryptedPassword);
            pstmt.setString(3, role);
            pstmt.executeUpdate();
        }
    }

    /**
     * Validates user login with encrypted password.
     *
     * @param email    User email.
     * @param password User password.
     * @param role     User role.
     * @return True if login is successful, false otherwise.
     */
    public boolean login(String email, String password, String role) throws Exception {
        String encryptedPassword = Base64.getEncoder().encodeToString(
                encryptionHelper.encrypt(password.getBytes(), EncryptionUtils.getInitializationVector(email.toCharArray()))
        );

        String query = "SELECT * FROM cse360users WHERE email = ? AND password = ? AND role = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, email);
            pstmt.setString(2, encryptedPassword);
            pstmt.setString(3, role);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    /**
     * Checks if a user exists in the database.
     *
     * @param email User email.
     * @return True if the user exists, false otherwise.
     */
    public boolean doesUserExist(String email) {
        String query = "SELECT COUNT(*) FROM cse360users WHERE email = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, email);
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
    
	public void displayUsersByAdmin() throws Exception{
		String sql = "SELECT * FROM cse360users"; 
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql); 

		while(rs.next()) { 
			// Retrieve by column name 
			int id  = rs.getInt("id"); 
			String  email = rs.getString("email"); 
			String role = rs.getString("role");  
			String encryptedPassword = rs.getString("password"); 
			char[] decryptedPassword = EncryptionUtils.toCharArray(
					encryptionHelper.decrypt(
							Base64.getDecoder().decode(
									encryptedPassword
							), 
							EncryptionUtils.getInitializationVector(email.toCharArray())
					)	
			);

			// Display values 
			System.out.print("ID: " + id); 
			System.out.print(", Email: " + email); 
			System.out.print(", Encrypted Password: " + encryptedPassword); 
			//System.out.print(", Decrypted Password: ");
			EncryptionUtils.printCharArray(decryptedPassword);
			System.out.println(", Role: " + role); 
			
			Arrays.fill(decryptedPassword, '0');
		} 
	}

	public List<User> listUsers() throws Exception {
	    List<User> users = new ArrayList<>();
	    String query = "SELECT * FROM cse360users";
	    try (Statement stmt = connection.createStatement();
	         ResultSet rs = stmt.executeQuery(query)) {

	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String email = rs.getString("email");
	            String encryptedPassword = rs.getString("password");
	            String role = rs.getString("role");

	            // Decrypt password
	            char[] decryptedPassword = EncryptionUtils.toCharArray(
	                    encryptionHelper.decrypt(
	                            Base64.getDecoder().decode(encryptedPassword),
	                            EncryptionUtils.getInitializationVector(email.toCharArray())
	                    )
	            );

	            // Add user to the list
	            users.add(new User(id, email, decryptedPassword, role));
	            Arrays.fill(decryptedPassword, '0'); // Clear sensitive data
	        }
	    }
	    return users;
	}


    public void updateUser(int id, String email, String password, String role, String firstName, 
                       String middleName, String lastName, String preferredName) throws SQLException {
        String updateSQL = "UPDATE cse360users SET email = ?, password = ?, role = ?, " +
                        "firstName = ?, middleName = ?, lastName = ?, preferredName = ? WHERE id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(updateSQL)) {
                pstmt.setString(1, email);
                pstmt.setString(2, password);  // Consider encrypting password as needed
                pstmt.setString(3, role);
                pstmt.setString(4, firstName);
                pstmt.setString(5, middleName);
                pstmt.setString(6, lastName);
                pstmt.setString(7, preferredName);
                pstmt.setInt(8, id);
                pstmt.executeUpdate();
            }
}

    // Method to check if a user exists in the database by their ID
    public boolean doesUserExistById(int id) throws SQLException {
        String query = "SELECT COUNT(*) FROM cse360users WHERE id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();
                return rs.next() && rs.getInt(1) > 0;
            }
    }

    // Method to remove a user from the database by their ID
    public void removeUserById(int id) throws SQLException {
        String deleteSQL = "DELETE FROM cse360users WHERE id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(deleteSQL)) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
            }
    }


    /**
     * Closes the database connection.
     */
    public void closeConnection() {
        try {
            if (statement != null) statement.close();
        } catch (SQLException se2) {
            se2.printStackTrace();
        }
        try {
            if (connection != null) connection.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    // ========================= Article Management Methods =========================

    /**
     * Creates a new article with encrypted fields.
     *
     * @param article The article to create.
     */
    public void createArticle(Article article) throws Exception {
        String insertArticle = "INSERT INTO articles (title, authors, abstract, keywords, body, references) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertArticle)) {
            byte[] iv = EncryptionUtils.getArticleInitializationVector();

            // Encrypt each field and encode as Base64 strings
            String encryptedTitle = Base64.getEncoder().encodeToString(
                    encryptionHelper.encrypt(EncryptionUtils.toByteArray(article.getTitle()), iv)
            );
            String encryptedAuthors = Base64.getEncoder().encodeToString(
                    encryptionHelper.encrypt(EncryptionUtils.toByteArray(article.getAuthors()), iv)
            );
            String encryptedAbstract = Base64.getEncoder().encodeToString(
                    encryptionHelper.encrypt(EncryptionUtils.toByteArray(article.getAbstractText()), iv)
            );
            String encryptedKeywords = Base64.getEncoder().encodeToString(
                    encryptionHelper.encrypt(EncryptionUtils.toByteArray(article.getKeywords()), iv)
            );
            String encryptedBody = Base64.getEncoder().encodeToString(
                    encryptionHelper.encrypt(EncryptionUtils.toByteArray(article.getBody()), iv)
            );
            String encryptedReferences = Base64.getEncoder().encodeToString(
                    encryptionHelper.encrypt(EncryptionUtils.toByteArray(article.getReferences()), iv)
            );

            pstmt.setString(1, encryptedTitle);
            pstmt.setString(2, encryptedAuthors);
            pstmt.setString(3, encryptedAbstract);
            pstmt.setString(4, encryptedKeywords);
            pstmt.setString(5, encryptedBody);
            pstmt.setString(6, encryptedReferences);

            pstmt.executeUpdate();
        }
    }
    
    /**
     * Deletes an article by ID.
     *
     * @param id The article ID.
     */
    public void deleteArticle(int id) throws SQLException {
        String deleteSQL = "DELETE FROM articles WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(deleteSQL)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
    /**
    
    Deletes all articles from the database.*/
    public void deleteAllArticles() throws SQLException {
        String deleteSQL = "DELETE FROM articles";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(deleteSQL);
        }
    }
    /**
     * Retrieves a list of articles, including complete details.
     *
     * @return List of Article objects with decrypted fields.
     */
    public List<Article> listArticles() throws Exception {
        List<Article> articles = new ArrayList<>();
        String query = "SELECT * FROM articles";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String encryptedTitle = rs.getString("title");
                String encryptedAuthors = rs.getString("authors");
                String encryptedAbstract = rs.getString("abstract");
                String encryptedKeywords = rs.getString("keywords");
                String encryptedBody = rs.getString("body");
                String encryptedReferences = rs.getString("references");

                byte[] iv = EncryptionUtils.getArticleInitializationVector();

                char[] title = EncryptionUtils.toCharArray(encryptionHelper.decrypt(Base64.getDecoder().decode(encryptedTitle), iv));
                char[] authors = EncryptionUtils.toCharArray(encryptionHelper.decrypt(Base64.getDecoder().decode(encryptedAuthors), iv));
                char[] abstractText = EncryptionUtils.toCharArray(encryptionHelper.decrypt(Base64.getDecoder().decode(encryptedAbstract), iv));
                char[] keywords = EncryptionUtils.toCharArray(encryptionHelper.decrypt(Base64.getDecoder().decode(encryptedKeywords), iv));
                char[] body = EncryptionUtils.toCharArray(encryptionHelper.decrypt(Base64.getDecoder().decode(encryptedBody), iv));
                char[] references = EncryptionUtils.toCharArray(encryptionHelper.decrypt(Base64.getDecoder().decode(encryptedReferences), iv));

                // Create the article object and add to list
                Article article = new Article();
                article.setId(id);
                article.setTitle(title);
                article.setAuthors(authors);
                article.setAbstractText(abstractText);
                article.setKeywords(keywords);
                article.setBody(body);
                article.setReferences(references);

                articles.add(article);

                // Clear sensitive data
                //java.util.Arrays.fill(title, ' ');
                //java.util.Arrays.fill(authors, ' ');
            }
        }
        return articles;
    }


    /**
     * Retrieves an article by ID with decrypted fields.
     *
     * @param id The article ID.
     * @return The Article object or null if not found.
     */
    public Article getArticleById(int id) throws Exception {
        String query = "SELECT * FROM articles WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Get the encrypted fields
                    String encryptedTitle = rs.getString("title");
                    String encryptedAuthors = rs.getString("authors");
                    String encryptedAbstract = rs.getString("abstract");
                    String encryptedKeywords = rs.getString("keywords");
                    String encryptedBody = rs.getString("body");
                    String encryptedReferences = rs.getString("references");

                    // Decrypt each field
                    byte[] iv = EncryptionUtils.getArticleInitializationVector();

                    char[] title = EncryptionUtils.toCharArray(
                            encryptionHelper.decrypt(
                                    Base64.getDecoder().decode(encryptedTitle),
                                    iv
                            )
                    );
                    char[] authors = EncryptionUtils.toCharArray(
                            encryptionHelper.decrypt(
                                    Base64.getDecoder().decode(encryptedAuthors),
                                    iv
                            )
                    );
                    char[] abstractText = EncryptionUtils.toCharArray(
                            encryptionHelper.decrypt(
                                    Base64.getDecoder().decode(encryptedAbstract),
                                    iv
                            )
                    );
                    char[] keywords = EncryptionUtils.toCharArray(
                            encryptionHelper.decrypt(
                                    Base64.getDecoder().decode(encryptedKeywords),
                                    iv
                            )
                    );
                    char[] body = EncryptionUtils.toCharArray(
                            encryptionHelper.decrypt(
                                    Base64.getDecoder().decode(encryptedBody),
                                    iv
                            )
                    );
                    char[] references = EncryptionUtils.toCharArray(
                            encryptionHelper.decrypt(
                                    Base64.getDecoder().decode(encryptedReferences),
                                    iv
                            )
                    );

                    Article article = new Article();
                    article.setId(id);
                    article.setTitle(title);
                    article.setAuthors(authors);
                    article.setAbstractText(abstractText);
                    article.setKeywords(keywords);
                    article.setBody(body);
                    article.setReferences(references);

                    return article;
                } else {
                    return null;
                }
            }
        }
    }

    /**
     * Backs up the articles to a specified file.
     *
     * @param filename The backup file name.
     */
    public void backupArticles(String filename) throws Exception {
        String query = "SELECT * FROM articles";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(new java.io.FileOutputStream(filename))) {
                while (rs.next()) {
                    // Get the encrypted fields
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    String authors = rs.getString("authors");
                    String abstractText = rs.getString("abstract");
                    String keywords = rs.getString("keywords");
                    String body = rs.getString("body");
                    String references = rs.getString("references");

                    // Create an ArticleData object to hold the data
                    ArticleData articleData = new ArticleData(id, title, authors, abstractText, keywords, body, references);

                    // Write the ArticleData object to the file
                    oos.writeObject(articleData);
                }
            }
        }
    }

    /**
     * Restores articles from a backup file.
     *
     * @param filename The backup file name.
     */
    public void restoreArticles(String filename) throws Exception {
        // First, reset the articles table to empty
        String deleteSQL = "DELETE FROM articles";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(deleteSQL);
        }

        try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(new java.io.FileInputStream(filename))) {
            while (true) {
                try {
                    // Read ArticleData object from file
                    ArticleData articleData = (ArticleData) ois.readObject();

                    // Insert into the database
                    String insertArticle = "INSERT INTO articles (id, title, authors, abstract, keywords, body, references) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement pstmt = connection.prepareStatement(insertArticle)) {
                        pstmt.setInt(1, articleData.getId());
                        pstmt.setString(2, articleData.getTitle());
                        pstmt.setString(3, articleData.getAuthors());
                        pstmt.setString(4, articleData.getAbstractText());
                        pstmt.setString(5, articleData.getKeywords());
                        pstmt.setString(6, articleData.getBody());
                        pstmt.setString(7, articleData.getReferences());

                        pstmt.executeUpdate();
                    }

                } catch (java.io.EOFException eof) {
                    // End of file reached
                    break;
                }
            }
        }
    }
    public boolean isAdminSetupComplete() throws SQLException {
        String query = "SELECT COUNT(*) FROM cse360users WHERE role = 'admin'";
        try (ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        }
        return false;
    }

/**
 * Checks if removing admin rights would leave no admin in the system.
 *
 * @return True if at least one admin exists, false otherwise.
 */
public boolean hasOtherAdmins() throws SQLException {
    String query = "SELECT COUNT(*) FROM cse360users WHERE role = 'admin'";
    try (Statement stmt = connection.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
        if (rs.next()) {
            return rs.getInt(1) > 1; // At least one other admin
        }
    }
    return false;
}

    /**
    * Updates the role of a user but ensures admin removal rules are not violated.
    *
    * @param userId    The ID of the user whose role is being updated.
    * @param newRole   The new role to assign.
    * @throws Exception If admin removal rules are violated.
    */
    public void updateUserRole(int userId, String newRole) throws Exception {
        // Check if the user being updated is an admin
        String query = "SELECT role FROM cse360users WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String currentRole = rs.getString("role");
                    if ("admin".equals(currentRole) && !"admin".equals(newRole)) {
                        // Validate if there are other admins before demoting this user
                        if (!hasOtherAdmins()) {
                            throw new IllegalStateException("Cannot remove admin rights. At least one admin is required.");
                        }
                    }
                } else {
                    throw new IllegalArgumentException("User ID not found.");
                }
            }
        }

        // Update the user's role
        String updateSQL = "UPDATE cse360users SET role = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(updateSQL)) {
            pstmt.setString(1, newRole);
            pstmt.setInt(2, userId);
            pstmt.executeUpdate();
        }
    }

}