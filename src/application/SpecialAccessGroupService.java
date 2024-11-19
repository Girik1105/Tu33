package application;

public class SpecialAccessGroupService {
    private DatabaseHelper databaseHelper;

    public SpecialAccessGroupService(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public void createGroup(String name, String description, int adminUserId) throws SQLException {
        databaseHelper.addSpecialAccessGroup(name, description);
        int groupId = databaseHelper.getLastInsertedId("special_access_groups"); // Assuming a utility method exists
        databaseHelper.addGroupAdmin(groupId, adminUserId);
    }

    public void addInstructor(int groupId, int userId) throws SQLException {
        databaseHelper.addGroupInstructor(groupId, userId);
    }

    public void addStudent(int groupId, int userId, boolean canViewBody) throws SQLException {
        databaseHelper.addGroupStudent(groupId, userId, canViewBody);
    }
}
