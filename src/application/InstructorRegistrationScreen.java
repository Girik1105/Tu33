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
        emailField.setPromptText("Enter User Email / Username");

        // Password input field
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter User Password");
        
        // First Name input field
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("Enter first name");

        // Middle Name input field
        TextField middleNameField = new TextField();
        middleNameField.setPromptText("Enter middle name (Optional)");

        // Last Name input field
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Enter last name");

        // Last Name input field
        TextField preferredNameField = new TextField();
        preferredNameField.setPromptText("Enter preferred name (Optional)");

        setStyle("-fx-background-color: floralwhite;"); // Set background color

        // Register button to submit registration details
        Button registerButton = new Button("Register");

        registerButton.setOnAction(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();
            String firstName = firstNameField.getText();
            String middleName = middleNameField.getText();
            String lastName = lastNameField.getText();
            String preferredName = preferredNameField.getText();
            try {

                // Check if the user already exists in the database
                if(!databaseHelper.doesUserExist(email)) {
                    // Register instructor in the database
                    databaseHelper.register(email, password, "instructor", firstName, middleName, lastName, preferredName);
                    System.out.println("User setup completed.");
                    databaseHelper.displayUsersByAdmin(); // For debugging purposes

                    // Redirect to login screen after registration
                    new StartCSE360().showLoginScreen(stage, databaseHelper);
                } else {
                    System.out.println("User already exists."); // Display if user exists
                }

            	// Check if user already exists in the database
            	if(!databaseHelper.doesUserExist(email)) {
            		// Register admin in database
            		databaseHelper.register(email, password, "instructor", firstName, middleName, lastName, preferredName);
            		System.out.println("User setup completed.");
            		System.out.println("Your verification code is instructor.");
            		databaseHelper.displayUsersByAdmin(); //debug
                
                new StartCSE360().showLoginScreen(stage, databaseHelper);
                
            	} else {
            		System.out.println("User already exists.");
            	}


            } catch (Exception ex) {
                ex.printStackTrace(); // Print any errors to console
            }
        });

        Button backButton = new Button("Back to Dashboard");
        backButton.setOnAction(e -> stage.setScene(new Scene(new LoginScreen(stage, databaseHelper), 400, 300)));
        
        setAlignment(Pos.CENTER); // Center-align content
        getChildren().addAll(titleLabel, emailField, passwordField, firstNameField, middleNameField, lastNameField, preferredNameField, registerButton, backButton); // Add components to VBox

        setSpacing(10); // Set spacing between components
    }
}
