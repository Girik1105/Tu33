package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserRegistrationScreen extends VBox {

    public UserRegistrationScreen(Stage stage, DatabaseHelper databaseHelper) {
        Label titleLabel = new Label("User Registration");
        TextField emailField = new TextField();
        emailField.setPromptText("Enter User Email");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter User Password");
        
        setStyle("-fx-background-color: floralwhite;");

        Button registerButton = new Button("Register");

        registerButton.setOnAction(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();
            try {
            	// Check if user already exists in the database
            	if(!databaseHelper.doesUserExist(email)) {
            		// Register admin in database
            		databaseHelper.register(email, password, "user");
            		System.out.println("User setup completed.");
            		databaseHelper.displayUsersByAdmin(); //debug
                
                new StartCSE360().showLoginScreen(stage, databaseHelper);
                
            	} else {
            		System.out.println("User already exists.");
            	}

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        setAlignment(Pos.CENTER);
        getChildren().addAll(titleLabel, emailField, passwordField, registerButton);
        setSpacing(10);
    }
}