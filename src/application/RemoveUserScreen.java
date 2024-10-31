package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RemoveUserScreen extends VBox {

    public RemoveUserScreen(Stage stage, DatabaseHelper databaseHelper) {
        setAlignment(Pos.CENTER);  // Center-aligns all elements within the VBox
        setPadding(new Insets(20));  // Sets padding of 20 pixels around the VBox
        setSpacing(10);  // Sets spacing of 10 pixels between each element

        // Label prompting the user to enter the ID of the user to remove
        Label instructionLabel = new Label("Enter the ID of the user to remove:");
        
        // TextField where the user can input the user ID
        TextField userIdField = new TextField();
        userIdField.setPromptText("User ID");  // Placeholder text for the TextField

        // Button that confirms and initiates the user removal process
        Button confirmButton = new Button("Confirm Removal");
        confirmButton.setOnAction(e -> {
            try {
                int userId = Integer.parseInt(userIdField.getText());  // Parses the user ID from the TextField
                if (databaseHelper.doesUserExistById(userId)) {  // Checks if the user exists in the database
                    databaseHelper.removeUserById(userId);  // Removes the user from the database
                    instructionLabel.setText("User removed successfully!");  // Updates label on success
                } else {
                    instructionLabel.setText("User ID not found.");  // Error message if user ID does not exist
                }
            } catch (NumberFormatException ex) {
                instructionLabel.setText("Please enter a valid user ID.");  // Error for non-integer input
            } catch (Exception ex) {
                instructionLabel.setText("An error occurred during removal.");  // Error for database issues
                ex.printStackTrace();  // Prints error details to console
            }
        });

        // Back button that returns the user to the Dashboard screen
        Button backButton = new Button("Back to Dashboard");
        backButton.setOnAction(e -> stage.setScene(new Scene(new Dashboard(stage, databaseHelper), 500, 400)));

        // Adding all UI elements to the VBox
        getChildren().addAll(instructionLabel, userIdField, confirmButton, backButton);
    }
}
