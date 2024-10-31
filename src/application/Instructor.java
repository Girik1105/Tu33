package application;

public class Instructor {
    private int id; // Unique identifier for instructor
    private String email; // Instructor's email address
    private char[] password; // Instructor's password as character array
    private String role; // Role of the instructor
    private String firstName; // First name of instructor
    private String middleName; // Middle name of instructor (optional)
    private String lastName; // Last name of instructor

    // Constructor to initialize Instructor object
    public Instructor(int id, String email, char[] password, String role, String firstName, String middleName, String lastName) {
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
    public String getFirstName() { return firstName; }
    public String getMiddleName() { return middleName; }
    public String getLastName() { return lastName; }
}
