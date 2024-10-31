package application;

public class Instructor {
    private int id;
    private String email;
    private char[] password;
    private String role;
    private String firstName;
    private String middleName;
    private String lastName;

    public Instructor(int id, String email, char[] password, String role, String firstName, String middleName, String lastName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getId() { return id; }
    public String getEmail() { return email; }
    public char[] getPassword() { return password; }
    public String getRole() { return role; }
    public String getFirstName() { return firstName; }
    public String getMiddleName() { return middleName; }
    public String getLastName() { return lastName; }
}
