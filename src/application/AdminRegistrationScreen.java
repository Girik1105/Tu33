package application;

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
        Label titleLabel = new Label("Admin Registration");
        TextField emailField = new TextField();
        emailField.setPromptText("Enter Admin Email");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Admin Password");
        
        // First Name input field
        TextField firstName = new TextField();
        firstName.setPromptText("Enter first name");

        // Middle Name input field
        TextField middleName = new TextField();
        middleName.setPromptText("Enter middle name (Optional)");

        // Last Name input field
        TextField lastName = new TextField();
        lastName.setPromptText("Enter last name");

        // Last Name input field
        TextField preferredName = new TextField();
        preferredName.setPromptText("Enter preferred name (Optional)");
        
        setStyle("-fx-background-color: floralwhite;");

        Button registerButton = new Button("Register");

        registerButton.setOnAction(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();
            try {
            	// Check if user already exists in the database
            	if(!databaseHelper.doesUserExist(email)) {
            		// Register admin in database
            		databaseHelper.register(email, password, "admin");
            		System.out.println("Administrator setup completed.");
            		databaseHelper.displayUsersByAdmin(); //debug
            		
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
            		System.out.println("Your verification code is admin");
            		
            		// Create default backup to restore default article if it gets deleted
            		databaseHelper.backupArticles("default");
                
                new StartCSE360().showLoginScreen(stage, databaseHelper);
                
            	} else {
            		System.out.println("User already exists.");
            	}

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        Button backButton = new Button("Back to Dashboard");
        backButton.setOnAction(e -> stage.setScene(new Scene(new LoginScreen(stage, databaseHelper), 400, 300)));
        setAlignment(Pos.CENTER);
        getChildren().addAll(titleLabel, emailField, passwordField, firstName, middleName, lastName, registerButton, backButton); // Add components to VBox
        setSpacing(10);
    }
}