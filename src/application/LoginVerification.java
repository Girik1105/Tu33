package application;


import javafx.geometry.Insets;
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

    public LoginVerification(Stage primaryStage, String expectedUsername, String expectedPassword, String preferredName, String firstName) {
        this.expectedUsername = expectedUsername.trim();
        this.expectedPassword = expectedPassword;
        this.preferredName = preferredName;
        this.firstName = firstName;

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));

        // Username input
        Label usernameLabel = new Label("Username:");
        grid.add(usernameLabel, 0, 0);
        TextField usernameInput = new TextField();
        grid.add(usernameInput, 1, 0);

        // Password input
        Label passwordLabel = new Label("Enter Password:");
        grid.add(passwordLabel, 0, 1);
        PasswordField passwordInput = new PasswordField();
        grid.add(passwordInput, 1, 1);

        // Confirm Password input
        Label confirmPasswordLabel = new Label("Confirm Password:");
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

            if (!enteredUsername.equals(expectedUsername)) {
                // Username doesn't match
                System.out.println("Username does not match the one on file!");
            } else if (!password.equals(confirmPassword)) {
                // Passwords do not match
                System.out.println("Passwords do not match!");
            } else {
                // Successful match, return to login screen
                System.out.println("Username and password confirmed!");
                
                Dashboard dashboard = new Dashboard(primaryStage, preferredName, firstName);
                primaryStage.setScene(dashboard.getScene());
                primaryStage.setTitle("Dashboard");
            }
        });

        scene = new Scene(grid, 400, 300);
    }

    public Scene getScene() {
        return scene;
    }
}
