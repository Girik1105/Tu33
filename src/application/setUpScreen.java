package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class setUpScreen {
    private Scene scene;
    private String firstName, middleName, lastName, email, preferredName;

    public setUpScreen(Stage primaryStage, String username, String password) {
    	GridPane grid = new GridPane();
        grid.setStyle(LoginPage.baseBackground);
        grid.setAlignment(Pos.CENTER); // align items in grid to centera
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // First name
        Label firstNameLabel = new Label("First Name:");
        TextField firstNameInput = new TextField();
        firstNameLabel.setStyle(LoginPage.h3bold);
        grid.add(firstNameLabel, 0, 0);
        grid.add(firstNameInput, 1, 0);

        // Middle name
        Label middleNameLabel = new Label("Middle Name:");
        TextField middleNameInput = new TextField();
        middleNameLabel.setStyle(LoginPage.h3bold);
        grid.add(middleNameLabel, 0, 1);
        grid.add(middleNameInput, 1, 1);

        // Last name
        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameInput = new TextField();
        lastNameLabel.setStyle(LoginPage.h3bold);
        grid.add(lastNameLabel, 0, 2);
        grid.add(lastNameInput, 1, 2);

        // Preferred name
        Label preferredNameLabel = new Label("Preferred Name:");
        TextField preferredNameInput = new TextField();
        preferredNameLabel.setStyle(LoginPage.h3bold);
        grid.add(preferredNameLabel, 0, 3);
        grid.add(preferredNameInput, 1, 3);

        // Email
        Label emailLabel = new Label("Email:");
        TextField emailInput = new TextField();
        emailLabel.setStyle(LoginPage.h3bold);
        grid.add(emailLabel, 0, 4);
        grid.add(emailInput, 1, 4);

        // Submit Button
        Button submitButton = new Button("Finish Setup");
        submitButton.setOnAction(e -> {
        	firstName = firstNameInput.getText().trim();
            String middleName = middleNameInput.getText().trim();
            String lastName = lastNameInput.getText().trim();
            String preferredName = preferredNameInput.getText().trim();
            String email = emailInput.getText().trim();
            
            if(firstName.isEmpty() || firstName == null || middleName.isEmpty() || middleName == null || lastName.isEmpty() || lastName == null || email.isEmpty() || email == null){
            	Label errorMessage = new Label("Please fill out all empty fields");
            	errorMessage.setStyle(LoginPage.errorText);
            	grid.add(errorMessage, 1, 7);
            } else {  
            LoginVerification loginVerification = new LoginVerification(primaryStage, username, password, preferredName, firstName, middleName, lastName, email);
            primaryStage.setScene(loginVerification.getScene());
            primaryStage.setTitle("Verify Login");
            
            }
        });
        submitButton.setStyle(LoginPage.buttonStyle);
        grid.add(submitButton, 1, 6);

        scene = new Scene(grid, 400, 300);
    }

    public Scene getScene() {
        return scene;
    }
}