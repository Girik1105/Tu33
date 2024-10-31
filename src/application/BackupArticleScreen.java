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

    public BackupArticleScreen(Stage stage, DatabaseHelper databaseHelper) {
        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setSpacing(10);

        Label promptLabel = new Label("Enter filename for backup:");
        TextField filenameField = new TextField();
        filenameField.setPromptText("Filename");

        Button backupButton = new Button("Backup Articles");
        backupButton.setOnAction(e -> {
            try {
                String filename = filenameField.getText();
                databaseHelper.backupArticles(filename);
                System.out.println("Articles backed up successfully to " + filename);
                stage.setScene(new Scene(new Dashboard(stage, databaseHelper), 500, 400));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        getChildren().addAll(promptLabel, filenameField, backupButton);
    }
}
