package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddUsersToGroupScreen extends VBox {

    public AddUsersToGroupScreen(Stage stage, DatabaseHelper databaseHelper) {
        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setSpacing(10);

        // Header label
        Label headerLabel = new Label("Add User to Special Access Group");

        // TextField for entering Group ID
        TextField groupIdField = new TextField();
        groupIdField.setPromptText("Enter Group ID");

        // TextField for entering User ID
        TextField userIdField = new TextField();
        userIdField.setPromptText("Enter User ID");

        // Button to submit the action
        Button addUserButton = new Button("Add User");
        addUserButton.setOnAction(e -> {
            try {
                int groupId = Integer.parseInt(groupIdField.getText());
                int userId = Integer.parseInt(userIdField.getText());

                // Ensure the table has the correct columns
                databaseHelper.addUserToSpecialGroup(groupId, userId, true, false); // Example: canViewBody=true, isAdmin=false
                System.out.println("User successfully added to Group ID: " + groupId);
            } catch (NumberFormatException ex) {
                System.err.println("Invalid input: Please enter valid integers for Group ID and User ID.");
            } catch (Exception ex) {
                System.err.println("Error adding user to group: " + ex.getMessage());
            }
        });

        // Back button to return to the dashboard
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> stage.setScene(new Scene(new Dashboard(stage, databaseHelper), 500, 400)));

        // Adding all elements to the VBox
        getChildren().addAll(headerLabel, groupIdField, userIdField, addUserButton, backButton);
    }

}
