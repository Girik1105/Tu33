package simpleDatabase;

import Encryption.EncryptionHelper;
import Encryption.EncryptionUtils;

import java.util.List;

/**
 * Tests class performs automated tests on the application functionalities.
 */
public class Tests {

    private static DatabaseHelper databaseHelper;
    private static EncryptionHelper encryptionHelper;

    public static void main(String[] args) {
        try {
            // Initialize the database helper and encryption helper
            databaseHelper = new DatabaseHelper();
            encryptionHelper = new EncryptionHelper();

            // Connect to an in-memory database for testing to avoid affecting production data
            databaseHelper.connectToInMemoryDatabase();

            // Run tests
            testUserRegistrationAndLogin();
            testArticleCreationAndRetrieval();
            testArticleDeletion();
            testArticleBackupAndRestore();

            System.out.println("\nAll tests completed.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            databaseHelper.closeConnection();
        }
    }

    /**
     * Tests user registration and login functionalities.
     */
    public static void testUserRegistrationAndLogin() throws Exception {
        System.out.println("Running testUserRegistrationAndLogin...");

        String email = "testuser@example.com";
        String password = "TestPassword123";
        String role = "user";

        // Register user
        databaseHelper.register(email, password, role);

        // Attempt to register the same user again
        boolean userExists = databaseHelper.doesUserExist(email);

        // Test if user exists after registration
        if (userExists) {
            System.out.println("User registration test passed.");
        } else {
            System.out.println("User registration test failed.");
        }

        // Test login with correct credentials
        boolean loginSuccess = databaseHelper.login(email, password, role);
        if (loginSuccess) {
            System.out.println("User login test passed.");
        } else {
            System.out.println("User login test failed.");
        }

        // Test login with incorrect password
        loginSuccess = databaseHelper.login(email, "WrongPassword", role);
        if (!loginSuccess) {
            System.out.println("User login with incorrect password test passed.");
        } else {
            System.out.println("User login with incorrect password test failed.");
        }
    }

    /**
     * Tests article creation and retrieval functionalities.
     */
    public static void testArticleCreationAndRetrieval() throws Exception {
        System.out.println("\nRunning testArticleCreationAndRetrieval...");

        // Create an article
        Article article = new Article();
        article.setTitle("Test Article".toCharArray());
        article.setAuthors("John Doe".toCharArray());
        article.setAbstractText("This is a test abstract.".toCharArray());
        article.setKeywords("Test; Article".toCharArray());
        article.setBody("This is the body of the test article.".toCharArray());
        article.setReferences("Reference 1; Reference 2".toCharArray());

        databaseHelper.createArticle(article);

        // List articles to verify creation
        List<ArticleSummary> articles = databaseHelper.listArticles();
        if (articles.size() == 1 && new String(articles.get(0).getTitle()).equals("Test Article")) {
            System.out.println("Article creation test passed.");
        } else {
            System.out.println("Article creation test failed.");
        }

        // Retrieve the article by ID
        Article retrievedArticle = databaseHelper.getArticleById(articles.get(0).getId());
        if (retrievedArticle != null && new String(retrievedArticle.getTitle()).equals("Test Article")) {
            System.out.println("Article retrieval test passed.");
        } else {
            System.out.println("Article retrieval test failed.");
        }

        // Clear article data
        article.clearData();
        retrievedArticle.clearData();
    }

    /**
     * Tests article deletion functionality.
     */
    public static void testArticleDeletion() throws Exception {
        System.out.println("\nRunning testArticleDeletion...");

        // List articles to get the article ID
        List<ArticleSummary> articles = databaseHelper.listArticles();
        int articleId = articles.get(0).getId();

        // Delete the article
        databaseHelper.deleteArticle(articleId);

        // Verify that the article is deleted
        Article deletedArticle = databaseHelper.getArticleById(articleId);
        if (deletedArticle == null) {
            System.out.println("Article deletion test passed.");
        } else {
            System.out.println("Article deletion test failed.");
        }
    }

    /**
     * Tests article backup and restore functionalities.
     */
    public static void testArticleBackupAndRestore() throws Exception {
        System.out.println("\nRunning testArticleBackupAndRestore...");

        // Create two articles
        Article article1 = new Article();
        article1.setTitle("Backup Article 1".toCharArray());
        article1.setAuthors("Author One".toCharArray());
        article1.setAbstractText("Abstract One".toCharArray());
        article1.setKeywords("Backup; Test".toCharArray());
        article1.setBody("Body of backup article one.".toCharArray());
        article1.setReferences("Ref1".toCharArray());
        databaseHelper.createArticle(article1);

        Article article2 = new Article();
        article2.setTitle("Backup Article 2".toCharArray());
        article2.setAuthors("Author Two".toCharArray());
        article2.setAbstractText("Abstract Two".toCharArray());
        article2.setKeywords("Backup; Test".toCharArray());
        article2.setBody("Body of backup article two.".toCharArray());
        article2.setReferences("Ref2".toCharArray());
        databaseHelper.createArticle(article2);

        // Backup articles to a file
        String backupFilename = "test_backup.dat";
        databaseHelper.backupArticles(backupFilename);

        // Delete all articles
        databaseHelper.deleteAllArticles();

        // Verify that all articles are deleted
        List<ArticleSummary> articles = databaseHelper.listArticles();
        if (articles.isEmpty()) {
            System.out.println("Articles deleted successfully before restore.");
        } else {
            System.out.println("Failed to delete articles before restore.");
        }

        // Restore articles from backup
        databaseHelper.restoreArticles(backupFilename);

        // Verify that articles are restored
        articles = databaseHelper.listArticles();
        if (articles.size() == 2) {
            System.out.println("Article backup and restore test passed.");
        } else {
            System.out.println("Article backup and restore test failed.");
        }

        // Clear article data
        article1.clearData();
        article2.clearData();
    }
}
