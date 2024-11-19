package application;

import java.util.List;

public class SpecialAccessGroup {
    private int id;
    private String name;
    private String description;
    private List<Article> articles;
    private List<User> admins;
    private List<User> instructors;
    private List<User> students;

    public SpecialAccessGroup(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Getters and setters for each field
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<Article> getArticles() { return articles; }
    public void setArticles(List<Article> articles) { this.articles = articles; }
    public List<User> getAdmins() { return admins; }
    public void setAdmins(List<User> admins) { this.admins = admins; }
    public List<User> getInstructors() { return instructors; }
    public void setInstructors(List<User> instructors) { this.instructors = instructors; }
    public List<User> getStudents() { return students; }
    public void setStudents(List<User> students) { this.students = students; }
}
