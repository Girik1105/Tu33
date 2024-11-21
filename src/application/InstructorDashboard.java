package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import application.Article;

public class InstructorDashboard extends VBox {

    // Constructor initializes the dashboard screen UI
    public InstructorDashboard(Stage stage, DatabaseHelper databaseHelper) {

        
        // Button to navigate to List Groups screen
        Button listGroupsButton = new Button("List Special Access Groups");
        listGroupsButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        listGroupsButton.setOnAction(e -> {
            stage.setScene(new Scene(new ListSpecialAccessGroupsScreen(stage, databaseHelper), 500, 400));
        });
        
        // Button to navigate to Backup Articles screen
        Button backupArticlesButton = new Button("Backup Articles");
        backupArticlesButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        backupArticlesButton.setOnAction(e -> {
            stage.setScene(new Scene(new BackupArticleScreen(stage, databaseHelper), 500, 400));
        });
        
        // Button to navigate to Restore Articles screen
        Button restoreArticlesButton = new Button("Restore Articles");
        restoreArticlesButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        restoreArticlesButton.setOnAction(e -> {
            stage.setScene(new Scene(new RestoreArticleScreen(stage, databaseHelper), 500, 400));
        });
        
        // Button to navigate to View Users screen
        Button viewUsersButton = new Button("View Users");
        viewUsersButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        viewUsersButton.setOnAction(e -> {
            stage.setScene(new Scene(new ViewUserScreen(stage, databaseHelper), 500, 400));
        });
        
        // Button to navigate to Create Special Access Group screen
        Button createGroupButton = new Button("Create Special Access Group");
        createGroupButton.setOnAction(e -> stage.setScene(new Scene(new CreateSpecialAccessGroupScreen(stage, databaseHelper), 500, 400)));

        // Button to navigate to Add Users to Group screen
        Button addUserToGroupButton = new Button("Add Users to Group");
        addUserToGroupButton.setOnAction(e -> {
            // For simplicity, hardcoding groupId; ideally, this should be dynamic or selected by the user
            int groupId = 1; // Replace with actual logic to get the selected group ID
            stage.setScene(new Scene(new AddUsersToGroupScreen(stage, databaseHelper), 500, 400));
        });

        // Button to navigate to Add Articles to Group screen
        Button addArticleToGroupButton = new Button("Add Articles to Group");
        addArticleToGroupButton.setOnAction(e -> {
            // For simplicity, hardcoding groupId; ideally, this should be dynamic or selected by the user
            int groupId = 1; // Replace with actual logic to get the selected group ID
            stage.setScene(new Scene(new AddArticlesToGroupScreen(stage, databaseHelper, groupId), 500, 400));
        });

 
        // Logout button to return to login screen
        Button logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-background-color: lightcoral;" + StartCSE360.h3bold);
        logoutButton.setOnAction(e -> {
            try {
                new StartCSE360().showLoginScreen(stage, databaseHelper); // Show login screen
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        setAlignment(Pos.CENTER); // Center-align all elements
        getChildren().addAll(listGroupsButton, backupArticlesButton, restoreArticlesButton, viewUsersButton, logoutButton);
        setSpacing(10); // Set spacing between buttons
        setStyle("-fx-background-color: floralwhite;"); // Set background color
    }
}
