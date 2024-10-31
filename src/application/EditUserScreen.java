package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditUserScreen extends VBox {

    public EditUserScreen(Stage stage, DatabaseHelper databaseHelper) {
        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setSpacing(10);

        Label instructionLabel = new Label("Enter the ID of the user to edit:");
        TextField userIdField = new TextField();
        userIdField.setPromptText("User ID");

        // Fields for new user details
        TextField emailField = new TextField();
        emailField.setPromptText("New Email");

        TextField passwordField = new TextField();
        passwordField.setPromptText("New Password");

        TextField roleField = new TextField();
        roleField.setPromptText("New Role");

        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");

        TextField middleNameField = new TextField();
        middleNameField.setPromptText("Middle Name (optional)");

        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");

        TextField preferredNameField = new TextField();
        preferredNameField.setPromptText("Preferred Name (optional)");

        // Button to confirm the update
        Button confirmButton = new Button("Confirm Update");
        confirmButton.setOnAction(e -> {
            try {
                int userId = Integer.parseInt(userIdField.getText());
                String email = emailField.getText();
                String password = passwordField.getText();
                String role = roleField.getText();
                String firstName = firstNameField.getText();
                String middleName = middleNameField.getText().isEmpty() ? null : middleNameField.getText();
                String lastName = lastNameField.getText();
                String preferredName = preferredNameField.getText().isEmpty() ? null : preferredNameField.getText();

                if (databaseHelper.doesUserExistById(userId)) {  // Check if user exists
                    databaseHelper.updateUser(userId, email, password, role, firstName, middleName, lastName, preferredName);  // Update user info in database
                    instructionLabel.setText("User updated successfully!");
                } else {
                    instructionLabel.setText("User ID not found.");
                }
            } catch (NumberFormatException ex) {
                instructionLabel.setText("Please enter a valid user ID.");
            } catch (Exception ex) {
                instructionLabel.setText("An error occurred during the update.");
                ex.printStackTrace();
            }
        });

        // Back button to return to the Dashboard
        Button backButton = new Button("Back to Dashboard");
        backButton.setOnAction(e -> stage.setScene(new Scene(new Dashboard(stage, databaseHelper), 500, 400)));

        // Add elements to VBox
        getChildren().addAll(instructionLabel, userIdField, emailField, passwordField, roleField, 
                             firstNameField, middleNameField, lastNameField, preferredNameField, 
                             confirmButton, backButton);
    }
}
