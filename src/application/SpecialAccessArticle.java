package application;

public class SpecialAccessArticle {
    private int id;
    private int groupId;
    private String title;
    private String encryptedBody; // Body stored in encrypted form

    public SpecialAccessArticle(int id, int groupId, String title, String encryptedBody) {
        this.id = id;
        this.groupId = groupId;
        this.title = title;
        this.encryptedBody = encryptedBody;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getGroupId() { return groupId; }
    public void setGroupId(int groupId) { this.groupId = groupId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getEncryptedBody() { return encryptedBody; }
    public void setEncryptedBody(String encryptedBody) { this.encryptedBody = encryptedBody; }
}
