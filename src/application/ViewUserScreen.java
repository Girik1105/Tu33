package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

public class ViewUserScreen extends VBox {

    // Constructor initializes the view users screen UI
    public ViewUserScreen(Stage stage, DatabaseHelper databaseHelper) {
        setAlignment(Pos.CENTER); // Center-aligns content
        setPadding(new Insets(20)); // Adds padding around VBox
        setSpacing(10); // Adds spacing between elements

        Label headerLabel = new Label("List of Users:");
        
        // Text area to display user information; non-editable
        TextArea usersArea = new TextArea();
        usersArea.setEditable(false);

        // Back button to return to the Dashboard
        Button backButton = new Button("Back to Dashboard");
        backButton.setOnAction(e -> stage.setScene(new Scene(new Dashboard(stage, databaseHelper), 500, 400)));

        try {
            // Fetch list of users from the database
            List<User> users = databaseHelper.listUsers();
            if (users.isEmpty()) {
                usersArea.setText("No users found."); // Display if no users
            } else {
                // Build string representation of users
                StringBuilder userText = new StringBuilder();
                for (User user : users) {
                    userText.append("ID: ").append(user.getId()).append("\n")
                            .append("Email: ").append(user.getEmail()).append("\n")
                            .append("Role: ").append(user.getRole()).append("\n")
                            .append("----------------------------------------\n");
                }
                usersArea.setText(userText.toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace(); // Print any errors to console
            usersArea.setText("An error occurred while retrieving users.");
        }

        getChildren().addAll(headerLabel, usersArea, backButton); // Add elements to VBox
    }
}
