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

    public DeleteArticleScreen(Stage stage, DatabaseHelper databaseHelper) {
        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setSpacing(10);

        Label promptLabel = new Label("Enter Article ID to delete:");
        TextField idField = new TextField();
        idField.setPromptText("Article ID");

        Button deleteButton = new Button("Delete Article");
        deleteButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                databaseHelper.deleteArticle(id);
                System.out.println("Article deleted successfully.");
                stage.setScene(new Scene(new Dashboard(stage, databaseHelper), 500, 400));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        
        Button backButton = new Button("Back to Dashboard");
        backButton.setOnAction(e -> stage.setScene(new Scene(new Dashboard(stage, databaseHelper), 500, 400)));

        getChildren().addAll(promptLabel, idField, deleteButton, backButton);
    }
}
