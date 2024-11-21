package application;

import java.sql.SQLException;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListSpecialAccessGroupsScreen extends VBox {
    public ListSpecialAccessGroupsScreen(Stage stage, DatabaseHelper databaseHelper) {
        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setSpacing(10);

        // Header Label
        Label headerLabel = new Label("Special Access Groups");
        ListView<SpecialAccessGroup> groupListView = new ListView<>();
        
        // Populate the list of special access groups
        try {
            List<SpecialAccessGroup> groups = databaseHelper.listSpecialAccessGroups();
            groupListView.getItems().addAll(groups);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // View Articles in the selected group
        Button viewArticlesButton = new Button("View Articles");
        viewArticlesButton.setOnAction(e -> {
            SpecialAccessGroup selectedGroup = groupListView.getSelectionModel().getSelectedItem();
            if (selectedGroup != null) {
                stage.setScene(new Scene(new ListSpecialAccessGroupArticlesScreen(stage, databaseHelper, selectedGroup.getId()), 500, 400));
            } else {
                System.out.println("No group selected.");
            }
        });

        // Add User to the selected group
        Button addUserButton = new Button("Add User to Group");
        addUserButton.setOnAction(e -> {
            SpecialAccessGroup selectedGroup = groupListView.getSelectionModel().getSelectedItem();
            if (selectedGroup != null) {
                stage.setScene(new Scene(new ManageGroupUserScreen(stage, databaseHelper, selectedGroup.getId(), "add"), 500, 400));
            } else {
                System.out.println("No group selected.");
            }
        });

        // Update User Permissions in the selected group
        Button updateUserButton = new Button("Update User Permissions");
        updateUserButton.setOnAction(e -> {
            SpecialAccessGroup selectedGroup = groupListView.getSelectionModel().getSelectedItem();
            if (selectedGroup != null) {
                stage.setScene(new Scene(new ManageGroupUserScreen(stage, databaseHelper, selectedGroup.getId(), "update"), 500, 400));
            } else {
                System.out.println("No group selected.");
            }
        });

        // Remove User from the selected group
        Button removeUserButton = new Button("Remove User from Group");
        removeUserButton.setOnAction(e -> {
            SpecialAccessGroup selectedGroup = groupListView.getSelectionModel().getSelectedItem();
            if (selectedGroup != null) {
                stage.setScene(new Scene(new ManageGroupUserScreen(stage, databaseHelper, selectedGroup.getId(), "remove"), 500, 400));
            } else {
                System.out.println("No group selected.");
            }
        });

        // Add Articles to the selected group
        Button addArticlesButton = new Button("Add Articles to Group");
        addArticlesButton.setOnAction(e -> {
            SpecialAccessGroup selectedGroup = groupListView.getSelectionModel().getSelectedItem();
            if (selectedGroup != null) {
                stage.setScene(new Scene(new AddArticlesToGroupScreen(stage, databaseHelper, selectedGroup.getId()), 500, 400));
            } else {
                System.out.println("No group selected.");
            }
        });

        // Create a New Special Access Group
        Button createGroupButton = new Button("Create New Group");
        createGroupButton.setOnAction(e -> stage.setScene(new Scene(new CreateSpecialAccessGroupScreen(stage, databaseHelper), 500, 400)));

        // Back Button to return to the Dashboard
        Button backButton = new Button("Back to Dashboard");
        backButton.setOnAction(e -> stage.setScene(new Scene(new Dashboard(stage, databaseHelper), 500, 400)));

        // Add all buttons and components to the VBox
        getChildren().addAll(headerLabel, groupListView, viewArticlesButton, addUserButton, updateUserButton, removeUserButton, addArticlesButton, createGroupButton, backButton);
    }
}