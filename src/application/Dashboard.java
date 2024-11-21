package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import application.Article;

public class Dashboard extends VBox {

    // Constructor initializes the dashboard screen UI
    public Dashboard(Stage stage, DatabaseHelper databaseHelper) {
        // Button to navigate to Create Article screen
        Button createArticleButton = new Button("Create Article");
        createArticleButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        createArticleButton.setOnAction(e -> {
            stage.setScene(new Scene(new CreateArticleScreen(stage, databaseHelper), 500, 400));
        });

        // Button to navigate to Delete Article screen
        Button deleteArticleButton = new Button("Delete Article");
        deleteArticleButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        deleteArticleButton.setOnAction(e -> {
            stage.setScene(new Scene(new DeleteArticleScreen(stage, databaseHelper), 500, 400));
        });

        // Button to navigate to List Articles screen
        Button listArticlesButton = new Button("List Articles");
        listArticlesButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        listArticlesButton.setOnAction(e -> {
            stage.setScene(new Scene(new ListArticleScreen(stage, databaseHelper), 500, 400));
        });
        
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
        getChildren().addAll(createArticleButton, deleteArticleButton, listArticlesButton, listGroupsButton, backupArticlesButton, restoreArticlesButton, viewUsersButton, logoutButton);
        setSpacing(10); // Set spacing between buttons
        setStyle("-fx-background-color: floralwhite;"); // Set background color
    }
}
