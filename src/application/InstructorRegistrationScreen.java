package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InstructorRegistrationScreen extends VBox {

    // Constructor initializes the instructor registration screen UI
    public InstructorRegistrationScreen(Stage stage, DatabaseHelper databaseHelper) {
        Label titleLabel = new Label("Instructor Registration");

        // Email input field
        TextField emailField = new TextField();
        emailField.setPromptText("Enter User Email");

        // Password input field
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter User Password");
        
        setStyle("-fx-background-color: floralwhite;"); // Set background color

        // Register button to submit registration details
        Button registerButton = new Button("Register");

        registerButton.setOnAction(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();
            try {
                // Check if the user already exists in the database
                if(!databaseHelper.doesUserExist(email)) {
                    // Register instructor in the database
                    databaseHelper.register(email, password, "instructor");
                    System.out.println("User setup completed.");
                    databaseHelper.displayUsersByAdmin(); // For debugging purposes

                    // Redirect to login screen after registration
                    new StartCSE360().showLoginScreen(stage, databaseHelper);
                } else {
                    System.out.println("User already exists."); // Display if user exists
                }

            } catch (Exception ex) {
                ex.printStackTrace(); // Print any errors to console
            }
        });

        setAlignment(Pos.CENTER); // Center-align content
        getChildren().addAll(titleLabel, emailField, passwordField, registerButton); // Add components to VBox
        setSpacing(10); // Set spacing between components
    }
}
