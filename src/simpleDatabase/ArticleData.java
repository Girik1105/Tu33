package simpleDatabase;

import java.io.Serializable;

/**
 * ArticleData class represents the serialized form of an article for backup purposes.
 * This class implements Serializable to allow instances of ArticleData to be
 * written to an output stream, enabling the storage and retrieval of article data.
 */
public class ArticleData implements Serializable {
    private static final long serialVersionUID = 1L; // Unique identifier for serialization
    private int id; // Unique identifier for the article
    private String title; // Title of the article
    private String authors; // Authors of the article
    private String abstractText; // Abstract of the article
    private String keywords; // Keywords associated with the article
    private String body; // Main content of the article
    private String references; // References cited in the article

    /**
     * Constructor to create an instance of ArticleData with specified values.
     *
     * @param id           the unique identifier for the article
     * @param title        the title of the article
     * @param authors      the authors of the article
     * @param abstractText the abstract of the article
     * @param keywords     the keywords associated with the article
     * @param body         the main content of the article
     * @param references   the references cited in the article
     */
    public ArticleData(int id, String title, String authors, String abstractText, String keywords, String body, String references) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.abstractText = abstractText;
        this.keywords = keywords;
        this.body = body;
        this.references = references;
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

    /**
     * Gets the abstract of the article.
     *
     * @return the abstract text of the article.
     */
    public String getAbstractText() {
        return abstractText;
    }

    /**
     * Gets the keywords associated with the article.
     *
     * @return the keywords of the article.
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * Gets the main content of the article.
     *
     * @return the body of the article.
     */
    public String getBody() {
        return body;
    }

    /**
     * Gets the references cited in the article.
     *
     * @return the references of the article.
     */
    public String getReferences() {
        return references;
    }
}
