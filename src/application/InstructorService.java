package application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstructorService {
    private DatabaseHelper databaseHelper;

    public InstructorService(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    // Search Articles with Group and Level Filters
    public List<Article> searchArticles(String query) throws SQLException {
        String group = SessionManager.getInstance().getCurrentGroup();
        String level = SessionManager.getInstance().getCurrentLevel();

        String sql = """
            SELECT * FROM articles 
            WHERE 
                (title LIKE ? OR authors LIKE ? OR abstract LIKE ?)
                AND (? = 'all' OR level = ?)
                AND (? = 'all' OR group_id = ?)
        """;

        try (var pstmt = databaseHelper.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, "%" + query + "%");
            pstmt.setString(2, "%" + query + "%");
            pstmt.setString(3, "%" + query + "%");
            pstmt.setString(4, level);
            pstmt.setString(5, level);
            pstmt.setString(6, group);
            pstmt.setString(7, group);
            var rs = pstmt.executeQuery();

            List<Article> articles = new ArrayList<>();
            while (rs.next()) {
                Article article = new Article();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title").toCharArray());
                article.setAuthors(rs.getString("authors").toCharArray());
                article.setAbstractText(rs.getString("abstract").toCharArray());
                articles.add(article);
            }
            return articles;
        }
    }

    // Create Article
    public void createArticle(Article article) throws Exception {
        databaseHelper.createArticle(article); //apparently not SQL
    }

    // Edit Article
    public void editArticle(int articleId, Article updatedArticle) throws Exception {
        databaseHelper.updateArticle(articleId, updatedArticle);
    }

    // Delete Article
    public void deleteArticle(int articleId) throws SQLException {
        databaseHelper.deleteArticle(articleId);
    }

    // Add Student to Group
    public void addStudentToGroup(int studentId, int groupId, String groupType) throws SQLException {
        String sql = "INSERT INTO student_groups (student_id, group_id, group_type) VALUES (?, ?, ?)";
        try (var pstmt = databaseHelper.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, groupId);
            pstmt.setString(3, groupType); // "general" or "special"
            pstmt.executeUpdate();
        }
    }

    // Remove Student from Group
    public void removeStudentFromGroup(int studentId, int groupId) throws SQLException {
        String sql = "DELETE FROM student_groups WHERE student_id = ? AND group_id = ?";
        try (var pstmt = databaseHelper.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, groupId);
            pstmt.executeUpdate();
        }
    }

    // Backup Articles
    public void backupArticles(String filename) throws Exception {
        databaseHelper.backupArticles(filename);
    }

    // Restore Articles
    public void restoreArticles(String filename) throws Exception {
        databaseHelper.restoreArticles(filename);
    }
}
