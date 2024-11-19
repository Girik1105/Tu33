package application;

import java.sql.Timestamp;

public class SearchRequest {
    private int id;
    private int studentId;
    private String query;
    private String type; // 'generic' or 'specific'
    private Timestamp timestamp;

    public SearchRequest(int id, int studentId, String query, String type, Timestamp timestamp) {
        this.id = id;
        this.studentId = studentId;
        this.query = query;
        this.type = type;
        this.timestamp = timestamp;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public String getQuery() { return query; }
    public void setQuery(String query) { this.query = query; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Timestamp getTimestamp() { return timestamp; }
    public void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }
}
