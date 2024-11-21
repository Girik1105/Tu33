package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import org.h2.engine.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListSpecialAccessGroups2 extends VBox {
    private final List<Group> groups = new ArrayList<>();

    public ListSpecialAccessGroups2(Stage stage, DatabaseHelper databaseHelper) {
        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setSpacing(10);

        Label headerLabel = new Label("Special Access Groups");
        ListView<Group> groupListView = new ListView<>();

        // Hardcode two initial groups
        groups.add(new Group(1, "Group 1"));
        groups.add(new Group(2, "Group 2"));
        groupListView.getItems().addAll(groups);

        Button addGroupButton = new Button("Add Group");
        addGroupButton.setOnAction(e -> {
            Group newGroup = new Group(groups.size() + 1, "Group " + (groups.size() + 1));
            groups.add(newGroup);
            groupListView.getItems().add(newGroup);
        });

        Button viewGroupButton = new Button("View Articles in Group");
        viewGroupButton.setOnAction(e -> {
            Group selectedGroup = groupListView.getSelectionModel().getSelectedItem();
            if (selectedGroup != null) {
                stage.setScene(new Scene(new GroupArticlesScreen(stage, selectedGroup, databaseHelper), 500, 400));
            }
        });
        
        Button backButton = new Button("Back to Login");
        backButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        backButton.setOnAction(e -> stage.setScene(new Scene(new InstructorDashboard(stage, databaseHelper), 400, 300)));

        getChildren().addAll(headerLabel, groupListView, addGroupButton, viewGroupButton, backButton);
    }
}


