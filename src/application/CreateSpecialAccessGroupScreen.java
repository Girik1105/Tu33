package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateSpecialAccessGroupScreen extends VBox {

    public CreateSpecialAccessGroupScreen(Stage stage, DatabaseHelper databaseHelper) {
        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setSpacing(10);

        Label titleLabel = new Label("Create Special Access Group");
        TextField nameField = new TextField();
        nameField.setPromptText("Group Name");
        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Group Description");

        Button createButton = new Button("Create Group");
        createButton.setOnAction(e -> {
            String name = nameField.getText();
            String description = descriptionField.getText();

            try {
                databaseHelper.addSpecialAccessGroup(name, description);
                System.out.println("Group created successfully!");
                stage.setScene(new Scene(new Dashboard(stage, databaseHelper), 500, 400));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button backButton = new Button("Back to Dashboard");
        backButton.setOnAction(e -> stage.setScene(new Scene(new Dashboard(stage, databaseHelper), 500, 400)));

        getChildren().addAll(titleLabel, nameField, descriptionField, createButton, backButton);
    }
}
