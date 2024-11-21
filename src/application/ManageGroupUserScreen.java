package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManageGroupUserScreen extends VBox {
public ManageGroupUserScreen(Stage stage, DatabaseHelper databaseHelper, int groupId, String action) {
    setAlignment(Pos.CENTER);
    setPadding(new Insets(20));
    setSpacing(10);

    Label headerLabel = new Label(action + " User for Group ID: " + groupId);
    TextField userIdField = new TextField();
    userIdField.setPromptText("User ID");
    CheckBox canViewBodyCheckBox = new CheckBox("Can View Body");
    CheckBox isAdminCheckBox = new CheckBox("Is Admin");
    Button submitButton = new Button("Submit");
    Button backButton = new Button("Back");

    // Submit action
    submitButton.setOnAction(e -> {
        try {
            int userId = Integer.parseInt(userIdField.getText());
            boolean canViewBody = canViewBodyCheckBox.isSelected();
            boolean isAdmin = isAdminCheckBox.isSelected();

            switch (action) {
                case "add":
                    databaseHelper.addUserToSpecialGroup(groupId, userId, canViewBody, isAdmin);
                    break;
                case "update":
                    databaseHelper.updateUserPermissionsInSpecialGroup(groupId, userId, canViewBody, isAdmin);
                    break;
                case "remove":
                    databaseHelper.removeUserFromSpecialGroup(groupId, userId);
                    break;
            }

            stage.setScene(new Scene(new ListSpecialAccessGroupsScreen(stage, databaseHelper), 500, 400));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    });

    backButton.setOnAction(e -> stage.setScene(new Scene(new ListSpecialAccessGroupsScreen(stage, databaseHelper), 500, 400)));

    getChildren().addAll(headerLabel, userIdField, canViewBodyCheckBox, isAdminCheckBox, submitButton, backButton);
}
}
