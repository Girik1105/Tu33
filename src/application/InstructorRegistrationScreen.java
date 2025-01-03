package application;

import javafx.geometry.Insets;
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
        VBox title = new VBox(); // Container for title section
        title.setPadding(new Insets(10)); // Set padding for title section
        title.setAlignment(Pos.CENTER); // Center-align title
        title.setStyle(StartCSE360.blueBackground); // Apply blue background style
        
        // Create the main title label
        Label titleLabel = new Label("Instructor Registration"); // Application title
        titleLabel.setStyle(StartCSE360.h1); // Apply title text styling

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
        middleNameField.setPromptText("Enter middle name (Optional - Enter N/A if unavailable)");

        // Last Name input field
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Enter last name");

        // Last Name input field
        TextField preferredNameField = new TextField();
        preferredNameField.setPromptText("Enter preferred name (Optional - Enter N/A if unavailable)");

        setStyle("-fx-background-color: floralwhite;"); // Set background color

        // Register button to submit registration details
        Button registerButton = new Button("Register");
        registerButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
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
	                    new StartCSE360().showLoginScreen(stage, databaseHelper);
                   
                } else {
            		System.out.println("User already exists.");
            	}


            } catch (Exception ex) {
                ex.printStackTrace(); // Print any errors to console
            }
        });

        Button backButton = new Button("Back to Login");
        backButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        backButton.setOnAction(e -> stage.setScene(new Scene(new LoginScreen(stage, databaseHelper), 400, 300)));
        
        setAlignment(Pos.CENTER); // Center-align content
        getChildren().addAll(titleLabel, emailField, passwordField, firstNameField, middleNameField, lastNameField, preferredNameField, registerButton, backButton); // Add components to VBox

        setSpacing(10); // Set spacing between components
    }
}
