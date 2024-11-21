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

        Label headerLabel = new Label("Special Access Groups");
        ListView<SpecialAccessGroup> groupListView = new ListView<>();

        try {
            List<SpecialAccessGroup> groups = databaseHelper.listSpecialAccessGroups();
            groupListView.getItems().addAll(groups);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button viewArticlesButton = new Button("View Articles");
        viewArticlesButton.setOnAction(e -> {
            SpecialAccessGroup selectedGroup = groupListView.getSelectionModel().getSelectedItem();
            if (selectedGroup != null) {
                stage.setScene(new Scene(new ListSpecialAccessGroupArticlesScreen(stage, databaseHelper, selectedGroup.getId()), 500, 400));
            }
        });

        Button addUserButton = new Button("Add User to Group");
        addUserButton.setOnAction(e -> {
            SpecialAccessGroup selectedGroup = groupListView.getSelectionModel().getSelectedItem();
            if (selectedGroup != null) {
                stage.setScene(new Scene(new ManageGroupUserScreen(stage, databaseHelper, selectedGroup.getId(), "add"), 500, 400));
            }
        });

        Button updateUserButton = new Button("Update User Permissions");
        updateUserButton.setOnAction(e -> {
            SpecialAccessGroup selectedGroup = groupListView.getSelectionModel().getSelectedItem();
            if (selectedGroup != null) {
                stage.setScene(new Scene(new ManageGroupUserScreen(stage, databaseHelper, selectedGroup.getId(), "update"), 500, 400));
            }
        });

        Button removeUserButton = new Button("Remove User from Group");
        removeUserButton.setOnAction(e -> {
            SpecialAccessGroup selectedGroup = groupListView.getSelectionModel().getSelectedItem();
            if (selectedGroup != null) {
                stage.setScene(new Scene(new ManageGroupUserScreen(stage, databaseHelper, selectedGroup.getId(), "remove"), 500, 400));
            }
        });

        Button addArticlesButton = new Button("Add Articles to Group");
        addArticlesButton.setOnAction(e -> {
            SpecialAccessGroup selectedGroup = groupListView.getSelectionModel().getSelectedItem();
            if (selectedGroup != null) {
                stage.setScene(new Scene(new AddArticlesToGroupScreen(stage, databaseHelper, selectedGroup.getId()), 500, 400));
            }
        });

        Button createGroupButton = new Button("Create New Group");
        createGroupButton.setOnAction(e -> stage.setScene(new Scene(new CreateSpecialAccessGroupScreen(stage, databaseHelper), 500, 400)));

        Button backButton = new Button("Back to Dashboard");
        backButton.setOnAction(e -> stage.setScene(new Scene(new Dashboard(stage, databaseHelper), 500, 400)));

        getChildren().addAll(headerLabel, groupListView, viewArticlesButton, addUserButton, updateUserButton, removeUserButton, addArticlesButton, createGroupButton, backButton);
    }
}
