package application;

/**
 * Article class represents a help article with various fields stored as char arrays.
 * Each article consists of a sequence number (id), title, authors, abstract, keywords,
 * body, and references, which are all represented as character arrays.
 */
public class Article {
    private int id; // Sequence number of the article
    private char[] title; // Title of the article
    private char[] authors; // Authors of the article
    private char[] abstractText; // Abstract of the article
    private char[] keywords; // Keywords associated with the article
    private char[] body; // Main content of the article
    private char[] references; // References cited in the article

    // Default constructor
    public Article() {}

    // Getters and setters

    /**
     * Gets the sequence number of the article.
     * @return the article's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the sequence number of the article.
     * @param id the article's ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the title of the article.
     * @return the title as a char array.
     */
    public char[] getTitle() {
        return title;
    }

    /**
     * Sets the title of the article.
     * @param title a char array representing the title.
     */
    public void setTitle(char[] title) {
        this.title = title;
    }

    /**
     * Gets the authors of the article.
     * @return the authors as a char array.
     */
    public char[] getAuthors() {
        return authors;
    }

    /**
     * Sets the authors of the article.
     * @param authors a char array representing the authors.
     */
    public void setAuthors(char[] authors) {
        this.authors = authors;
    }

    /**
     * Gets the abstract of the article.
     * @return the abstract text as a char array.
     */
    public char[] getAbstractText() {
        return abstractText;
    }

    /**
     * Sets the abstract of the article.
     * @param abstractText a char array representing the abstract.
     */
    public void setAbstractText(char[] abstractText) {
        this.abstractText = abstractText;
    }

    /**
     * Gets the keywords associated with the article.
     * @return the keywords as a char array.
     */
    public char[] getKeywords() {
        return keywords;
    }

    /**
     * Sets the keywords associated with the article.
     * @param keywords a char array representing the keywords.
     */
    public void setKeywords(char[] keywords) {
        this.keywords = keywords;
    }

    /**
     * Gets the main content of the article.
     * @return the body of the article as a char array.
     */
    public char[] getBody() {
        return body;
    }

    /**
     * Sets the main content of the article.
     * @param body a char array representing the article's body.
     */
    public void setBody(char[] body) {
        this.body = body;
    }

    /**
     * Gets the references cited in the article.
     * @return the references as a char array.
     */
    public char[] getReferences() {
        return references;
    }

    /**
     * Sets the references cited in the article.
     * @param references a char array representing the references.
     */
    public void setReferences(char[] references) {
        this.references = references;
    }

    /**
     * Clears the article data by setting all char arrays to blanks.
     * This method ensures that all fields are reset, effectively removing 
     * any existing data from the article instance.
     */
    public void clearData() {
        if (title != null) java.util.Arrays.fill(title, ' '); // Clear title
        if (authors != null) java.util.Arrays.fill(authors, ' '); // Clear authors
        if (abstractText != null) java.util.Arrays.fill(abstractText, ' '); // Clear abstract
        if (keywords != null) java.util.Arrays.fill(keywords, ' '); // Clear keywords
        if (body != null) java.util.Arrays.fill(body, ' '); // Clear body
        if (references != null) java.util.Arrays.fill(references, ' '); // Clear references
    }
}
