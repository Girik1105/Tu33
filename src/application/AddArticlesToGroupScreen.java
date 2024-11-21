package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddArticlesToGroupScreen extends VBox {

    public AddArticlesToGroupScreen(Stage stage, DatabaseHelper databaseHelper, int groupId) {
        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setSpacing(10);

        // Header label
        Label headerLabel = new Label("Add Article to Special Access Group");

        // TextField for entering Group ID
        TextField groupIdField = new TextField(String.valueOf(groupId)); // Pre-filled group ID
        groupIdField.setPromptText("Enter Group ID");

        // TextField for entering Article Title
        TextField articleTitleField = new TextField(); // Declare and initialize the article title field
        articleTitleField.setPromptText("Enter Article Title");

        // TextArea for entering Article Body
        TextArea articleBodyArea = new TextArea();
        articleBodyArea.setPromptText("Enter Article Body");
        articleBodyArea.setWrapText(true);

        // Button to submit the action
        Button addArticleButton = new Button("Add Article");
        addArticleButton.setOnAction(e -> {
            try {
                // Retrieve inputs from the UI
                int groupIdInput = Integer.parseInt(groupIdField.getText());
                String title = articleTitleField.getText();
                String body = articleBodyArea.getText();

                // Validate inputs
                if (title == null || title.isEmpty()) {
                    throw new IllegalArgumentException("Article title cannot be empty.");
                }
                if (body == null || body.isEmpty()) {
                    throw new IllegalArgumentException("Article body cannot be empty.");
                }

                // Add the article to the specified group with title and body content
                databaseHelper.addArticleToSpecialGroup(groupIdInput, title, body);
                System.out.println("Article successfully added to Group ID: " + groupIdInput);
            } catch (NumberFormatException ex) {
                System.err.println("Invalid input: Please enter a valid integer for Group ID.");
            } catch (IllegalArgumentException ex) {
                System.err.println("Validation error: " + ex.getMessage());
            } catch (Exception ex) {
                System.err.println("Error adding article to group: " + ex.getMessage());
            }
        });

        // Back button to return to the dashboard
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> stage.setScene(new Scene(new Dashboard(stage, databaseHelper), 500, 400)));

        // Adding all elements to the VBox
        getChildren().addAll(headerLabel, groupIdField, articleTitleField, articleBodyArea, addArticleButton, backButton);
    }
}
