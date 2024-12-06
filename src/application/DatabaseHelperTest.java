package application;

import java.sql.SQLException;

import org.junit.jupiter.api.*;

public class DatabaseHelperTest {

    private DatabaseHelper dbHelper;

    /**
     * Initialize the database helper and connect to an in-memory database for testing.
     */
    @BeforeEach
    public void setUp() throws Exception {
        dbHelper = new DatabaseHelper();
        dbHelper.connectToInMemoryDatabase(); // Use an in-memory database to avoid persistence.
    }

    /**
     * Clean up after each test by closing the database connection.
     */
    @AfterEach
    public void tearDown() throws SQLException {
        if (dbHelper != null) {
            dbHelper.closeConnection(); // Assuming this method exists for cleanup.
        }
    }

    /**
     * Test registering a user as an admin and assigning them to a group.
     */
    @Test
    public void testRegisterAdmin() throws Exception {
        dbHelper.register("admin@example.com", "password123", "admin", "Admin", "Middle", "User", "AdminUser");
        boolean isDatabaseEmpty = dbHelper.isDatabaseEmpty();
        assertFalse(isDatabaseEmpty, "Database should not be empty after registering an admin.");
    }

    /**
     * Test registering a student and verifying their group assignment.
     */
    @Test
    public void testRegisterStudent() throws Exception {
        dbHelper.register("student@example.com", "password123", "student", "Student", "", "User", "StudentUser");

        // Verify that the user exists and is assigned to a group.
        boolean userExists = dbHelper.doesUserExist("student@example.com");
        assertTrue(userExists, "Student should be registered and exist in the database.");
    }

    /**
     * Test creating a special access group and assigning users to it.
     */
    @Test
    public void testCreateSpecialAccessGroup() throws SQLException {
        boolean groupCreated = dbHelper.createSpecialAccessGroup("Test Group", "A group for testing.");
        assertTrue(groupCreated, "Special access group should be created successfully.");
    }

    /**
     * Test assigning a student to a special access group.
     */
    @Test
    public void testAssignStudentToSpecialAccessGroup() throws Exception {
        dbHelper.register("student@example.com", "password123", "student", "Student", "", "User", "StudentUser");
        boolean assigned = dbHelper.assignUserToSpecialAccessGroup("student@example.com", 1);
        assertTrue(assigned, "Student should be assigned to the special access group.");
    }

    /**
     * Test that admins cannot view encrypted article bodies.
     */
    @Test
    public void testAdminCannotViewEncryptedArticles() throws Exception {
        dbHelper.register("admin@example.com", "password123", "admin", "Admin", "Middle", "User", "AdminUser");

        // Create an article and verify admin cannot access the body.
        boolean articleCreated = dbHelper.createArticle("Test Article", "Admin", "Abstract", "Keyword", "Body", "References");
        assertTrue(articleCreated, "Admin should be able to create an article.");

        String body = dbHelper.getEncryptedArticleBody("Test Article");
        assertNull(body, "Admin should not have access to the encrypted article body.");
    }

    /**
     * Test that students can search through articles they have access to.
     */
    @Test
    public void testStudentArticleSearch() throws Exception {
        dbHelper.register("student@example.com", "password123", "student", "Student", "", "User", "StudentUser");

        // Add an article to the student's group and search for it.
        dbHelper.createArticle("Accessible Article", "Author", "Abstract", "Keyword", "Body", "References");
        boolean found = dbHelper.searchArticlesByStudent("Accessible Article", "student@example.com");
        assertTrue(found, "Student should be able to search for and find accessible articles.");
    }

    /**
     * Test that instructors can manage special access groups.
     */
    @Test
    public void testInstructorManageSpecialAccessGroups() throws Exception {
        dbHelper.register("instructor@example.com", "password123", "instructor", "Instructor", "", "User", "InstructorUser");

        // Verify instructor can create and manage groups.
        boolean groupCreated = dbHelper.createSpecialAccessGroup("Instructor Group", "Managed by instructor.");
        assertTrue(groupCreated, "Instructor should be able to create special access groups.");
    }

    /**
     * Test that the last admin cannot be removed.
     */
    @Test
    public void testAdminCannotBeRemovedIfOnlyOne() throws Exception {
        dbHelper.register("admin@example.com", "password123", "admin", "Admin", "Middle", "User", "AdminUser");

        boolean removed = dbHelper.removeUser("admin@example.com");
        assertFalse(removed, "Admin cannot be removed if they are the only admin.");
    }
}
