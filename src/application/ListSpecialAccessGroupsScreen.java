package application;

import java.sql.SQLException;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListSpecialAccessGroupsScreen extends VBox {
    public ListSpecialAccessGroupsScreen(Stage stage, DatabaseHelper databaseHelper) {
        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setSpacing(10);
        Label headerLabel = new Label("Special Access Groups");
        ListView<SpecialAccessGroup> groupListView = new ListView<>();
        

        // Populate the list of special access groups
        Button viewArticlesButton = new Button("View Articles");
        try {
            List<SpecialAccessGroup> groups = databaseHelper.listSpecialAccessGroups();
            groupListView.getItems().addAll(groups);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // View articles in the selected group
        Button addUserButton = new Button("Add User to Group");
        viewArticlesButton.setOnAction(e -> {
            SpecialAccessGroup selectedGroup = groupListView.getSelectionModel().getSelectedItem();
            if (selectedGroup != null) {
                stage.setScene(new Scene(new ListSpecialAccessGroupArticlesScreen(stage, databaseHelper, selectedGroup.getId()), 500, 400));
            }
        });

        // Add user to the selected group
        addUserButton.setOnAction(e -> {
            SpecialAccessGroup selectedGroup = groupListView.getSelectionModel().getSelectedItem();
            if (selectedGroup != null) {
                stage.setScene(new Scene(new ManageGroupUserScreen(stage, databaseHelper, selectedGroup.getId(), "add"), 500, 400));
            }
        });
        // Update user permissions in the selected group
        Button updateUserButton = new Button("Update User Permissions");
        updateUserButton.setOnAction(e -> {
            SpecialAccessGroup selectedGroup = groupListView.getSelectionModel().getSelectedItem();
            if (selectedGroup != null) {
                stage.setScene(new Scene(new ManageGroupUserScreen(stage, databaseHelper, selectedGroup.getId(), "update"), 500, 400));
            }
        });

        Button removeUserButton = new Button("Remove User from Group");
        // Remove user from the selected group
        removeUserButton.setOnAction(e -> {
            SpecialAccessGroup selectedGroup = groupListView.getSelectionModel().getSelectedItem();
            if (selectedGroup != null) {
                stage.setScene(new Scene(new ManageGroupUserScreen(stage, databaseHelper, selectedGroup.getId(), "remove"), 500, 400));
            }
        });
        
        // Back button to return to the Dashboard
        Button backButton = new Button("Back to Dashboard");
        backButton.setOnAction(e -> stage.setScene(new Scene(new Dashboard(stage, databaseHelper), 500, 400)));


        getChildren().addAll(headerLabel, groupListView, viewArticlesButton, addUserButton, updateUserButton, removeUserButton, backButton);
    }
}