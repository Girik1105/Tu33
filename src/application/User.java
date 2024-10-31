package application;

public class User {
    private int id; // Unique identifier for the user
    private String email; // User's email address
    private char[] password; // User's password stored as character array
    private String role; // Role of the user (e.g., admin, user)

    // Constructor to initialize User object
    public User(int id, String email, char[] password, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getter methods
    public int getId() { return id; }
    public String getEmail() { return email; }
    public char[] getPassword() { return password; }
    public String getRole() { return role; }
}
