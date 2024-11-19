package application;

import java.sql.SQLException;
import java.util.List;

public class StudentService {
    private DatabaseHelper databaseHelper;

    public StudentService(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    /**
     * Records a search request by the student.
     */
    public void recordSearchRequest(int studentId, String query, String type) throws SQLException {
        String sql = "INSERT INTO student_search_requests (student_id, query, type) VALUES (?, ?, ?)";
        try (var pstmt = databaseHelper.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.setString(2, query);
            pstmt.setString(3, type); // 'generic' or 'specific'
            pstmt.executeUpdate();
        }
    }

    /**
     * Retrieves a list of search requests by a student.
     */
    public List<SearchRequest> getSearchRequests(int studentId) throws SQLException {
        String sql = "SELECT * FROM student_search_requests WHERE student_id = ?";
        try (var pstmt = databaseHelper.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            var rs = pstmt.executeQuery();
            List<SearchRequest> requests = new ArrayList<>();
            while (rs.next()) {
                requests.add(new SearchRequest(
                        rs.getInt("id"),
                        rs.getInt("student_id"),
                        rs.getString("query"),
                        rs.getString("type"),
                        rs.getTimestamp("timestamp")
                ));
            }
            return requests;
        }
    }

    /**
     * Searches articles with the given criteria.
     */
    public List<Article> searchArticles(String query, String group, String level) throws SQLException {
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

    /**
     * Retrieves an article by its sequence number.
     */
    public Article getArticleBySequence(int sequence) throws SQLException {
        String sql = "SELECT * FROM articles WHERE id = ?";
        try (var pstmt = databaseHelper.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, sequence);
            var rs = pstmt.executeQuery();
            if (rs.next()) {
                Article article = new Article();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title").toCharArray());
                article.setAuthors(rs.getString("authors").toCharArray());
                article.setAbstractText(rs.getString("abstract").toCharArray());
                article.setBody(rs.getString("body").toCharArray());
                return article;
            }
        }
        return null;
    }
}
