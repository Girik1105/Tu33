package application;

public class User {
    private int id; // Unique identifier for the user
    private String email; // User's email address
    private char[] password; // User's password stored as character array
    private String role; // Role of the user (e.g., admin, user)
    //Added vars
    private String firstName;
    private String middleName;
    private String lastName;
    private String preferredName;

    // Constructor to initialize User object
    public User(int id, String email, char[] password, String role, String firstName, String middleName, String lastName, String preferredName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.preferredName = preferredName;
        
    }

    // Getter methods
    public int getId() { return id; }
    public String getEmail() { return email; }
    public char[] getPassword() { return password; }
    public String getRole() { return role; }
	public String getFirstName() { return firstName; }
	public String getMiddleName() { return middleName; }
	public String getLastName() { return lastName; }
	public String getPreferredName() { return preferredName; }
}
