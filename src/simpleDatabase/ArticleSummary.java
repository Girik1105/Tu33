package simpleDatabase;

/**
 * ArticleSummary class represents a summary of an article for listing purposes.
 * It provides a concise view of an article's essential details, including its
 * unique identifier, title, and authors.
 */
public class ArticleSummary {
    private int id; // Unique identifier for the article
    private String title; // Title of the article
    private String authors; // Authors of the article

    /**
     * Constructor to create an instance of ArticleSummary with specified values.
     *
     * @param id      the unique identifier for the article
     * @param title   the title of the article
     * @param authors the authors of the article
     */
    public ArticleSummary(int id, String title, String authors) {
        this.id = id;
        this.title = title;
        this.authors = authors;
    }

    // Getters

    /**
     * Gets the unique identifier for the article.
     *
     * @return the article's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the title of the article.
     *
     * @return the title of the article.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the authors of the article.
     *
     * @return the authors of the article.
     */
    public String getAuthors() {
        return authors;
    }
}
