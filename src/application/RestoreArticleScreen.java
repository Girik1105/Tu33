package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RestoreArticleScreen extends VBox {

    // Constructor initializes the restore article screen UI
    public RestoreArticleScreen(Stage stage, DatabaseHelper databaseHelper) {
        setAlignment(Pos.CENTER); // Center-align content
        setPadding(new Insets(20)); // Add padding around VBox
        setSpacing(10); // Add spacing between elements

        // Label and input field for entering the filename to restore
        Label promptLabel = new Label("Enter filename to restore from backup:");
        TextField filenameField = new TextField();
        filenameField.setPromptText("Filename");

        // Button to restore articles from the specified file
        Button restoreButton = new Button("Restore Articles");
        restoreButton.setOnAction(e -> {
            try {
                String filename = filenameField.getText();
                databaseHelper.restoreArticles(filename); // Restore articles from backup
                System.out.println("Articles restored successfully from " + filename);
                
                // Redirect to Dashboard after restoration
                stage.setScene(new Scene(new Dashboard(stage, databaseHelper), 500, 400));
            } catch (Exception ex) {
                ex.printStackTrace(); // Print any errors to console
            }
        });
        
        // Button to go back to the Dashboard
        Button backButton = new Button("Back to Dashboard");
        backButton.setOnAction(e -> stage.setScene(new Scene(new Dashboard(stage, databaseHelper), 500, 400)));

        // Add all elements to the VBox
        getChildren().addAll(promptLabel, filenameField, restoreButton, backButton);
    }
}
