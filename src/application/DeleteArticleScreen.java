package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DeleteArticleScreen extends VBox {

    // Constructor initializes the delete article screen UI
    public DeleteArticleScreen(Stage stage, DatabaseHelper databaseHelper) {
        setAlignment(Pos.CENTER); // Center-aligns content
        setPadding(new Insets(20)); // Adds padding around VBox
        setSpacing(10); // Adds spacing between elements

        // Label and input field for entering the Article ID
        Label promptLabel = new Label("Enter Article ID to delete:");
        TextField idField = new TextField();
        idField.setPromptText("Article ID");

        // Button to delete the specified article
        Button deleteButton = new Button("Delete Article");
        deleteButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                databaseHelper.deleteArticle(id); // Delete article from database
                System.out.println("Article deleted successfully.");
                
                // Redirect to Dashboard after deletion
                stage.setScene(new Scene(new Dashboard(stage, databaseHelper), 500, 400));
            } catch (Exception ex) {
                ex.printStackTrace(); // Print any errors to console
            }
        });
        
        // Button to go back to the Dashboard
        Button backButton = new Button("Back to Dashboard");
        backButton.setOnAction(e -> stage.setScene(new Scene(new Dashboard(stage, databaseHelper), 500, 400)));

        // Add all elements to the VBox
        getChildren().addAll(promptLabel, idField, deleteButton, backButton);
    }
}
