package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BackupArticleScreen extends VBox {

    // Constructor initializes the backup screen UI
    public BackupArticleScreen(Stage stage, DatabaseHelper databaseHelper) {
        setAlignment(Pos.CENTER); // Center-aligns content
        setPadding(new Insets(20)); // Adds padding around VBox
        setSpacing(10); // Adds spacing between elements

        Label promptLabel = new Label("Enter filename for backup:");
        
        // Text field for entering the backup filename
        TextField filenameField = new TextField();
        filenameField.setPromptText("Filename");

        // Button to initiate article backup
        Button backupButton = new Button("Backup Articles");
        backupButton.setOnAction(e -> {
            try {
                String filename = filenameField.getText();
                databaseHelper.backupArticles(filename); // Call backup function
                System.out.println("Articles backed up successfully to " + filename);
                
                // Redirect to Dashboard on successful backup
                stage.setScene(new Scene(new Dashboard(stage, databaseHelper), 500, 400));
            } catch (Exception ex) {
                ex.printStackTrace(); // Print any errors to console
            }
        });

        // Button to go back to the Dashboard
        Button backButton = new Button("Back to Dashboard");
        backButton.setOnAction(e -> stage.setScene(new Scene(new Dashboard(stage, databaseHelper), 500, 400)));

        // Add all elements to the VBox
        getChildren().addAll(promptLabel, filenameField, backupButton, backButton);
    }
}
