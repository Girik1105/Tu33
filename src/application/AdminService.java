package application;

import java.sql.SQLException;
import java.util.List;

public class AdminService {
    private DatabaseHelper databaseHelper;

    public AdminService(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    // Create Help Article (no body content)
    public void createArticleWithoutBody(String title, String authors, String abstractText, String keywords) throws SQLException {
        Article article = new Article();
        article.setTitle(title.toCharArray());
        article.setAuthors(authors.toCharArray());
        article.setAbstractText(abstractText.toCharArray());
        article.setKeywords(keywords.toCharArray());
        article.setBody("".toCharArray()); // Set empty body as admin can't add/view body
        databaseHelper.createArticle(article);
    }

    // Delete Help Article
    public void deleteArticle(int articleId) throws SQLException {
        databaseHelper.deleteArticle(articleId);
    }

    // Backup Articles, Groups, and Special Access Groups
    public void backupArticlesAndGroups(String filename) throws Exception {
        databaseHelper.backupArticles(filename); // Backup all articles
        // Extend backup logic for groups as needed
    }

    // Restore Articles, Groups, and Special Access Groups
    public void restoreArticlesAndGroups(String filename) throws Exception {
        databaseHelper.restoreArticles(filename); // Restore all articles
        // Extend restore logic for groups as needed
    }

    // Add Admin Rights for a Group
    public void addAdminToGroup(int adminId, int groupId, String groupType) throws SQLException {
        String sql = "INSERT INTO admin_group_rights (admin_id, group_id, group_type) VALUES (?, ?, ?)";
        try (var pstmt = databaseHelper.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, adminId);
            pstmt.setInt(2, groupId);
            pstmt.setString(3, groupType); // "general" or "special"
            pstmt.executeUpdate();
        }
    }

    // Remove Admin Rights for a Group (Ensure at least one admin remains)
    public void removeAdminFromGroup(int adminId, int groupId) throws SQLException {
        String sql = "DELETE FROM admin_group_rights WHERE admin_id = ? AND group_id = ?";
        try (var pstmt = databaseHelper.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, adminId);
            pstmt.setInt(2, groupId);

            // Check remaining admin count for the group
            int remainingAdmins = getAdminCountForGroup(groupId);
            if (remainingAdmins <= 1) {
                throw new IllegalStateException("Cannot remove admin: at least one admin must remain for the group.");
            }

            pstmt.executeUpdate();
        }
    }

    // Count Admins for a Group
    public int getAdminCountForGroup(int groupId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM admin_group_rights WHERE group_id = ?";
        try (var pstmt = databaseHelper.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, groupId);
            var rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    // Add/Remove Students, Instructors, and Admins from General Groups
    public void addUserToGroup(int userId, int groupId, String role, String groupType) throws SQLException {
        String table = groupType.equals("general") ? "student_groups" : "special_access_groups";
        String sql = "INSERT INTO " + table + " (user_id, group_id, role) VALUES (?, ?, ?)";
        try (var pstmt = databaseHelper.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, groupId);
            pstmt.setString(3, role);
            pstmt.executeUpdate();
        }
    }

    public void removeUserFromGroup(int userId, int groupId, String groupType) throws SQLException {
        String table = groupType.equals("general") ? "student_groups" : "special_access_groups";
        String sql = "DELETE FROM " + table + " WHERE user_id = ? AND group_id = ?";
        try (var pstmt = databaseHelper.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, groupId);
            pstmt.executeUpdate();
        }
    }
}
