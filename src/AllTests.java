import application

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({})
public class AllTests {

	
	package application;

	import static org.junit.Assert.*;
	import org.junit.Before;
	import org.junit.Test;

	public class ArticleManagementTests {
	    private DatabaseHelper databaseHelper;

	    @Before
	    public void setup() throws Exception {
	        databaseHelper = new DatabaseHelper();
	        databaseHelper.connectToInMemoryDatabase();
	    }

	    @Test
	    public void testAdminCreatesArticle() throws Exception {
	        Article article = new Article();
	        article.setTitle("Test Article".toCharArray());
	        article.setAuthors("Admin".toCharArray());
	        databaseHelper.createArticle(article);
	        assertEquals("One article should be present", 1, databaseHelper.listArticles().size());
	    }
	    @Test
	    public void testArticleFields() throws Exception {
	        Article article = new Article();
	        article.setTitle("Detailed Article".toCharArray());
	        article.setBody("This is a test body.".toCharArray());
	        databaseHelper.createArticle(article);
	        Article retrieved = databaseHelper.getArticleById(1);
	        assertEquals("Body should match", "This is a test body.", new String(retrieved.getBody()));
	    }

	    @Test
	    public void testGroupingArticles() throws Exception {
	        databaseHelper.addSpecialAccessGroup("Test Group", "Description");
	        Article article = new Article();
	        article.setTitle("Grouped Article".toCharArray());
	        databaseHelper.createArticle(article);
	        databaseHelper.addArticleToGroup(1, 1); // Article ID 1, Group ID 1
	        List<Article> groupArticles = databaseHelper.listArticlesBySpecialGroup(1);
	        assertEquals("One article should be in the group", 1, groupArticles.size());
	    }

	    @Test
	    public void testBackupArticles() throws Exception {
	        databaseHelper.backupArticles("test_backup.dat");
	        assertTrue("Backup file should exist", databaseHelper.doesBackupExist("test_backup.dat"));
	    }

	    @Test
	    public void testDuplicateBackupNames() throws Exception {
	        databaseHelper.backupArticles("duplicate_backup.dat");
	        boolean addedAgain = databaseHelper.backupArticles("duplicate_backup.dat");
	        assertFalse("Duplicate backup name should not be added", addedAgain);
	    }
	}
}
