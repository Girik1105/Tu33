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

public class AdminRegistrationScreen extends VBox {

    public AdminRegistrationScreen(Stage stage, DatabaseHelper databaseHelper) {
        // Constructor initializes the instructor registration screen UI
        VBox title = new VBox(); // Container for title section
        title.setPadding(new Insets(10)); // Set padding for title section
        title.setAlignment(Pos.CENTER); // Center-align title
        title.setStyle(StartCSE360.blueBackground); // Apply blue background style
    	
        Label titleLabel = new Label("Admin Registration");
        titleLabel.setStyle(StartCSE360.h1);
        
        TextField emailField = new TextField();
        emailField.setPromptText("Enter Admin Email");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Admin Password");
        
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
        
        setStyle("-fx-background-color: floralwhite;");

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
                // Check if user already exists in the database
                if (!databaseHelper.doesUserExist(email)) {
                    // Register admin in database
                    databaseHelper.register(email, password, "admin", firstName, middleName, lastName, preferredName);
                    System.out.println("Administrator setup completed.");
                    databaseHelper.displayUsersByAdmin(); // debug
                    
                    // Create default article
                    Article defaultArticle = new Article();
                    defaultArticle.setTitle("Instructions".toCharArray());
                    defaultArticle.setAuthors("Me".toCharArray());
                    defaultArticle.setAbstractText("N/A".toCharArray());
                    defaultArticle.setKeywords("Non-Fiction".toCharArray());
                    defaultArticle.setBody("Press the number according to the option in the list, then press enter to confirm action.".toCharArray());
                    defaultArticle.setReferences("N/A".toCharArray());
                    
                    databaseHelper.createArticle(defaultArticle);
                    System.out.println("Default article created successfully.");
                    System.out.println("Your verification code is admin.");
                    
                    // Create default backup to restore default article if it gets deleted
                    databaseHelper.backupArticles("default");
                    
                    // After successful registration, show the login screen
                    new StartCSE360().showLoginScreen(stage, databaseHelper);
                    
                } else {
                    System.out.println("User already exists.");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        
        Button contButton = new Button("Continue to Login");
        contButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        contButton.setOnAction(e -> stage.setScene(new Scene(new LoginScreen(stage, databaseHelper), 400, 300)));
        setAlignment(Pos.CENTER);
        getChildren().addAll (titleLabel, emailField, passwordField, firstNameField, middleNameField, lastNameField, preferredNameField, registerButton, contButton); // Add components to VBox
        setSpacing(10);
    }
    

}