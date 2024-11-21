package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InstructorDashboard extends VBox {

    public InstructorDashboard(Stage stage, DatabaseHelper databaseHelper) {

        Button listGroupsButton = new Button("List Special Access Groups 2");
        listGroupsButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        listGroupsButton.setOnAction(e -> {
            stage.setScene(new Scene(new ListSpecialAccessGroups2(stage, databaseHelper), 500, 400));
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
        
        
        Button logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-background-color: lightcoral;" + StartCSE360.h3bold);
        logoutButton.setOnAction(e -> {
            try {
                new StartCSE360().showLoginScreen(stage, databaseHelper);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        setAlignment(Pos.CENTER);
        getChildren().addAll(listGroupsButton, backupArticlesButton, restoreArticlesButton, viewUsersButton, logoutButton);
        setSpacing(10);
        setStyle("-fx-background-color: floralwhite;");
    }
}
