package application;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginVerification {
	private Scene scene;
    private String expectedUsername; // The username from account setup
    private String expectedPassword; // password form account setup
    private String preferredName; // preferred name from account setup
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;

    public LoginVerification(Stage primaryStage, String expectedUsername, String expectedPassword, String preferredName, String firstName, String middleName, String lastName, String email) {
        this.expectedUsername = expectedUsername.trim();
        this.expectedPassword = expectedPassword;
        this.preferredName = preferredName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;

        GridPane grid = new GridPane();
        grid.setStyle(LoginPage.baseBackground);
        grid.setAlignment(Pos.CENTER); // align items in grid to center
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Username input
        Label usernameLabel = new Label("Username:");
        usernameLabel.setStyle(LoginPage.h3bold);
        grid.add(usernameLabel, 0, 0);
        TextField usernameInput = new TextField();
        grid.add(usernameInput, 1, 0);

        // Password input
        Label passwordLabel = new Label("Enter Password:");
        passwordLabel.setStyle(LoginPage.h3bold);
        grid.add(passwordLabel, 0, 1);
        PasswordField passwordInput = new PasswordField();
        grid.add(passwordInput, 1, 1);

        // Confirm Password input
        Label confirmPasswordLabel = new Label("Confirm Password:");
        confirmPasswordLabel.setStyle(LoginPage.h3bold);
        grid.add(confirmPasswordLabel, 0, 2);
        PasswordField confirmPasswordInput = new PasswordField();
        grid.add(confirmPasswordInput, 1, 2);

        // Submit Button
        Button submitButton = new Button("Submit");
        grid.add(submitButton, 1, 3);
        submitButton.setOnAction(e -> {
            String enteredUsername = usernameInput.getText().trim();
            String password = passwordInput.getText();
            String confirmPassword = confirmPasswordInput.getText();
            
            if(enteredUsername.isEmpty() || enteredUsername == null || password.isEmpty() || password == null){
                Label errorMessage = new Label("Please fill out all empty fields");
                errorMessage.setStyle(LoginPage.errorText);
                grid.add(errorMessage, 1, 7);
                
            } else {

            if (!enteredUsername.equals(expectedUsername)) {
                // Username doesn't match
                System.out.println("Username does not match the one on file!");
            } else if (!password.equals(confirmPassword)) {
                // Passwords do not match
                System.out.println("Passwords do not match!");
            } else {
                // Successful match, return to login screen
                System.out.println("Username and password confirmed!");
                
                Dashboard dashboard = new Dashboard(primaryStage, preferredName, firstName, middleName, lastName, email);
                primaryStage.setScene(dashboard.getScene());
                primaryStage.setTitle("Dashboard");
            } 
           }
        });
        submitButton.setStyle(LoginPage.buttonStyle);
        scene = new Scene(grid, 400, 300);
    }

    public Scene getScene() {
        return scene;
    }
}
