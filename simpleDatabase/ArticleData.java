package simpleDatabase;

import java.io.Serializable;

/**
 * ArticleData class represents the serialized form of an article for backup purposes.
 */
public class ArticleData implements Serializable {
    private static final long serialVersionUID = 1L; // Added serialVersionUID
    private int id;
    private String title;
    private String authors;
    private String abstractText;
    private String keywords;
    private String body;
    private String references;

    // Constructor
    public ArticleData(int id, String title, String authors, String abstractText, String keywords, String body, String references) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.abstractText = abstractText;
        this.keywords = keywords;
        this.body = body;
        this.references = references;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public String getAbstractText() {
        return abstractText;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getBody() {
        return body;
    }

    public String getReferences() {
        return references;
    }
}
