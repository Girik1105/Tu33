package simpleDatabase;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * StartCSE360 is the main application class that provides the console interface.
 */
public class StartCSE360 {

    private static DatabaseHelper databaseHelper;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        databaseHelper = new DatabaseHelper();
        try {

            databaseHelper.connectToDatabase(); // Connect to the database

            // Check if the database is empty (no users registered)
            if (databaseHelper.isDatabaseEmpty()) {
                System.out.println("In-Memory Database is empty");
                // Set up administrator access
                setupAdministrator();
                articleManagementMenu();
            } else {
            	
                System.out.println("Welcome to the Help Article Management System");
                System.out.print("Type 1 and press enter to continue: ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        userFlow();
                    default:
                        System.out.println("Invalid choice. Please select '1'.");
                        databaseHelper.closeConnection();
                }

            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Good Bye!!");
            databaseHelper.closeConnection();
        }
    }

    /**
     * Sets up the administrator account.
     */
    /**
     * Sets up the administrator account and creates a default article.
     */
    private static void setupAdministrator() throws Exception {

        System.out.println("Setting up the Administrator access.");
        System.out.print("Enter Admin Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Admin Password: ");
        String password = scanner.nextLine();
        databaseHelper.register(email, password, "admin");
        System.out.println("Administrator setup completed.");
        
        // Create a default article
        System.out.println("Creating default article: 'Instructions'.");
        Article defaultArticle = new Article();

        // Setting fields for the default article
        defaultArticle.setTitle("Instructions".toCharArray());
        defaultArticle.setAuthors("Me".toCharArray());
        defaultArticle.setAbstractText("N/A".toCharArray());
        defaultArticle.setKeywords("Non-Fiction".toCharArray());
        defaultArticle.setBody("Press the number according to the option in the list, then press enter to confirm action.".toCharArray());
        defaultArticle.setReferences("N/A".toCharArray()); // Since references were not specified, using "N/A"

        // Store the article in the database
        databaseHelper.createArticle(defaultArticle);
        System.out.println("Default article created successfully.");

        // Clear article data
        defaultArticle.clearData();
    }

    /**
     * Handles user registration and login flow.
     */
    private static void userFlow() throws Exception {
        System.out.println("User flow");
        System.out.print("What would you like to do? 1. Register 2. Login: ");
        String choice = scanner.nextLine();
        String email, password;
        switch (choice) {
            case "1":
                System.out.print("Enter User Email: ");
                email = scanner.nextLine();
                System.out.print("Enter User Password: ");
                password = scanner.nextLine();
                // Check if user already exists in the database
                if (!databaseHelper.doesUserExist(email)) {
                    databaseHelper.register(email, password, "admin");
                    System.out.println("User setup completed.");
                    articleManagementMenu();
                } else {
                    System.out.println("User already exists.");
                }
                break;
            case "2":
                System.out.print("Enter User Email: ");
                email = scanner.nextLine();
                System.out.print("Enter User Password: ");
                password = scanner.nextLine();
                if (databaseHelper.login(email, password, "admin")) {
                    System.out.println("User login successful.");
                    articleManagementMenu();
                } else {
                    System.out.println("Invalid user credentials. Try again!!");
                }
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    /**
     * Displays the article management menu and handles user input.
     */
    private static void articleManagementMenu() throws Exception {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nMenu:");
            System.out.println("1. Create Article");
            System.out.println("2. Delete Article");
            System.out.println("3. List Articles");
            System.out.println("4. View Article");
            System.out.println("5. Backup Articles");
            System.out.println("6. Restore Articles from Backup");
            System.out.println("7. View Users");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createArticle();
                    break;
                case "2":
                    deleteArticle();
                    break;
                case "3":
                    listArticles();
                    break;
                case "4":
                    displayArticle();
                    break;
                case "5":
                    backupArticles();
                    break;
                case "6":
                    restoreArticles();
                    break;
                case "7":
                	databaseHelper.displayUsersByAdmin();
                case "8":
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    /**
     * Handles creating a new article.
     */
    private static void createArticle() throws Exception {
        System.out.println("\nCreate Article");
        Article article = new Article();

        System.out.print("Enter Title: ");
        article.setTitle(scanner.nextLine().toCharArray());

        System.out.print("Enter Author(s): ");
        article.setAuthors(scanner.nextLine().toCharArray());

        System.out.print("Enter Abstract: ");
        article.setAbstractText(scanner.nextLine().toCharArray());

        System.out.print("Enter Keywords: ");
        article.setKeywords(scanner.nextLine().toCharArray());

        System.out.print("Enter Body: ");
        article.setBody(scanner.nextLine().toCharArray());

        System.out.print("Enter References: ");
        article.setReferences(scanner.nextLine().toCharArray());

        databaseHelper.createArticle(article);
        System.out.println("Article created successfully.");

        // Clear article data
        article.clearData();
    }

    /**
     * Handles deleting an article.
     */
    private static void deleteArticle() throws Exception {
        System.out.println("\nDelete Article");
        System.out.print("Enter Article ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        databaseHelper.deleteArticle(id);
        System.out.println("Article deleted successfully.");
    }

    /**
     * Handles listing all articles.
     */
    private static void listArticles() throws Exception {
        System.out.println("\nList of Articles:");
        List<ArticleSummary> articles = databaseHelper.listArticles();
        if (articles.isEmpty()) {
            System.out.println("No articles found.");
        } else {
            for (ArticleSummary summary : articles) {
                System.out.println("ID: " + summary.getId() + ", Title: " + summary.getTitle() + ", Authors: " + summary.getAuthors());
            }
        }
    }

    /**
     * Handles displaying an article.
     */
    private static void displayArticle() throws Exception {
        System.out.println("\nDisplay Article");
        System.out.print("Enter Article ID to display: ");
        int id = Integer.parseInt(scanner.nextLine());
        Article article = databaseHelper.getArticleById(id);
        if (article == null) {
            System.out.println("Article not found.");
        } else {
            System.out.println("ID: " + article.getId());
            System.out.println("Title: " + new String(article.getTitle()));
            System.out.println("Authors: " + new String(article.getAuthors()));
            System.out.println("Abstract: " + new String(article.getAbstractText()));
            System.out.println("Keywords: " + new String(article.getKeywords()));
            System.out.println("Body: " + new String(article.getBody()));
            System.out.println("References: " + new String(article.getReferences()));

            // Clear article data
            article.clearData();
        }
    }

    /**
     * Handles backing up articles to a file.
     */
    private static void backupArticles() throws Exception {
        System.out.print("Enter filename to backup articles: ");
        String filename = scanner.nextLine();
        databaseHelper.backupArticles(filename);
        System.out.println("Articles backed up successfully to " + filename);
    }

    /**
     * Handles restoring articles from a backup file.
     */
    private static void restoreArticles() throws Exception {
        System.out.print("Enter filename to restore articles from backup: ");
        String filename = scanner.nextLine();
        databaseHelper.restoreArticles(filename);
        System.out.println("Articles restored successfully from " + filename);
    }
}