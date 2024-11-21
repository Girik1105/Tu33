package application;

import java.sql.SQLException;

public class SpecialAccessGroupService {
    private DatabaseHelper databaseHelper;

    public SpecialAccessGroupService(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public void createGroup(String name, String description, int adminUserId) throws SQLException {
        databaseHelper.addSpecialAccessGroup(name, description);
        int groupId = databaseHelper.getLastInsertedId("special_access_groups"); // Get the last inserted group ID
        databaseHelper.addGroupAdmin(groupId, adminUserId);
    }

    public void addUserToGroup(int groupId, int userId, boolean canViewBody, boolean isAdmin) throws SQLException {
        databaseHelper.addUserToSpecialGroup(groupId, userId, canViewBody, isAdmin);
    }

    public void addArticleToGroup(int groupId, int articleId) throws SQLException {
        databaseHelper.addArticleToSpecialGroup(groupId, articleId);
    }
}
