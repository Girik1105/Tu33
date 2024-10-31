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

    public RestoreArticleScreen(Stage stage, DatabaseHelper databaseHelper) {
        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setSpacing(10);

        Label promptLabel = new Label("Enter filename to restore from backup:");
        TextField filenameField = new TextField();
        filenameField.setPromptText("Filename");

        Button restoreButton = new Button("Restore Articles");
        restoreButton.setOnAction(e -> {
            try {
                String filename = filenameField.getText();
                databaseHelper.restoreArticles(filename);
                System.out.println("Articles restored successfully from " + filename);
                stage.setScene(new Scene(new Dashboard(stage, databaseHelper), 500, 400));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        getChildren().addAll(promptLabel, filenameField, restoreButton);
    }
}
