package simpleDatabase;

/**
 * ArticleSummary class represents a summary of an article for listing purposes.
 */
public class ArticleSummary {
    private int id;
    private String title;
    private String authors;

    // Constructor
    public ArticleSummary(int id, String title, String authors) {
        this.id = id;
        this.title = title;
        this.authors = authors;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }
}
