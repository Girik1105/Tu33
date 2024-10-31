package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import application.Article;

public class Dashboard extends VBox {

    public Dashboard(Stage stage, DatabaseHelper databaseHelper) {
        Button createArticleButton = new Button("Create Article");
        createArticleButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        createArticleButton.setOnAction(e -> {
            // Code to create article
    	    stage.setScene(new Scene(new CreateArticleScreen(stage, databaseHelper), 400, 400));

        });

        Button deleteArticleButton = new Button("Delete Article");
        deleteArticleButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        deleteArticleButton.setOnAction(e -> {
            // Code to delete article
    	    stage.setScene(new Scene(new DeleteArticleScreen(stage, databaseHelper), 400, 400));

        });

        Button listArticlesButton = new Button("List Articles");
        listArticlesButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        listArticlesButton.setOnAction(e -> {
            // Code to list articles
    	    stage.setScene(new Scene(new ListArticleScreen(stage, databaseHelper), 400, 400));

        });
        
        Button backupArticlesButton = new Button("Backup Articles");
        backupArticlesButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        backupArticlesButton.setOnAction(e -> {
        	// Code to view users in database
        	stage.setScene(new Scene(new BackupArticleScreen(stage, databaseHelper), 400, 400));
        });
        
        Button restoreArticlesButton = new Button("Restore Articles");
        restoreArticlesButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        restoreArticlesButton.setOnAction(e -> {
            // Code to view users in database
    	    stage.setScene(new Scene(new RestoreArticleScreen(stage, databaseHelper), 400, 400));

        });
        
        Button viewUsersButton = new Button("View Users");
        viewUsersButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        viewUsersButton.setOnAction(e -> {
            // Code to view users in database
    	    stage.setScene(new Scene(new ViewUserScreen(stage, databaseHelper), 400, 400));
        });

        Button logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-background-color: lightcoral;" + StartCSE360.h3bold);
        logoutButton.setOnAction(e -> {
            try {
                // Return to the login screen with the existing database helper
                new StartCSE360().showLoginScreen(stage, databaseHelper);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        setAlignment(Pos.CENTER);
        getChildren().addAll(createArticleButton, deleteArticleButton, listArticlesButton, backupArticlesButton, restoreArticlesButton, viewUsersButton, logoutButton);
        setSpacing(10);
        setStyle("-fx-background-color: floralwhite;");
    }
}
