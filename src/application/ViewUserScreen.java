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

        // New Edit User button to go to EditUserScreen
        Button editUserButton = new Button("Edit User");
        editUserButton.setOnAction(e -> stage.setScene(new Scene(new EditUserScreen(stage, databaseHelper), 500, 500)));

        // New Remove User button to go to RemoveUserScreen
        Button removeUserButton = new Button("Remove User");
        removeUserButton.setOnAction(e -> stage.setScene(new Scene(new RemoveUserScreen(stage, databaseHelper), 500, 400)));

        // Invite User button 
        Button inviteUserButton = new Button("Invite User");
        inviteUserButton.setOnAction(e -> System.out.print("Instructor Registration Code: instructor\n"
        		+ "Student Registration Code: student\n"));

        // Reset a User button
        Button resetUser = new Button("Reset User");
        
        // Add a Role to User button
        Button addRole = new Button("Add Role from User");

        // Remove a Role to User button
        Button removeRole = new Button("Remove Role from User");

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
                            .append("First Name: ").append(user.getFirstName()).append("\n")
                            .append("Middle Name: ").append(user.getMiddleName()).append("\n")
                            .append("Last Name: ").append(user.getLastName()).append("\n")
                            .append("Preferred Name: ").append(user.getPreferredName()).append("\n")
                            .append("----------------------------------------\n");
                }
                usersArea.setText(userText.toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace(); // Print any errors to console
            usersArea.setText("An error occurred while retrieving users.");
        }

        // Add elements to VBox, including the new Edit and Remove User button
        getChildren().addAll(headerLabel, usersArea, editUserButton, removeUserButton, inviteUserButton, resetUser, addRole, removeRole, backButton);

    }
}
