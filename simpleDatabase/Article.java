package simpleDatabase;

/**
 * Article class represents a help article with various fields stored as char arrays.
 */
public class Article {
    private int id; // Sequence number
    private char[] title;
    private char[] authors;
    private char[] abstractText;
    private char[] keywords;
    private char[] body;
    private char[] references;

    // Constructors
    public Article() {}

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public char[] getTitle() {
        return title;
    }

    public void setTitle(char[] title) {
        this.title = title;
    }

    public char[] getAuthors() {
        return authors;
    }

    public void setAuthors(char[] authors) {
        this.authors = authors;
    }

    public char[] getAbstractText() {
        return abstractText;
    }

    public void setAbstractText(char[] abstractText) {
        this.abstractText = abstractText;
    }

    public char[] getKeywords() {
        return keywords;
    }

    public void setKeywords(char[] keywords) {
        this.keywords = keywords;
    }

    public char[] getBody() {
        return body;
    }

    public void setBody(char[] body) {
        this.body = body;
    }

    public char[] getReferences() {
        return references;
    }

    public void setReferences(char[] references) {
        this.references = references;
    }

    /**
     * Clears the article data by setting all char arrays to blanks.
     */
    public void clearData() {
        if (title != null) java.util.Arrays.fill(title, ' ');
        if (authors != null) java.util.Arrays.fill(authors, ' ');
        if (abstractText != null) java.util.Arrays.fill(abstractText, ' ');
        if (keywords != null) java.util.Arrays.fill(keywords, ' ');
        if (body != null) java.util.Arrays.fill(body, ' ');
        if (references != null) java.util.Arrays.fill(references, ' ');
    }
}
