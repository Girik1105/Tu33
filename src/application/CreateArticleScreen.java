package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import application.Article;

public class CreateArticleScreen extends GridPane {

    // Constructor initializes the article creation screen UI
    public CreateArticleScreen(Stage stage, DatabaseHelper databaseHelper) {
        setPadding(new Insets(20)); // Set padding around grid
        setHgap(10); // Horizontal gap between grid elements
        setVgap(10); // Vertical gap between grid elements
        setAlignment(Pos.CENTER); // Center-align grid contents

        // Labels and input fields for Article details
        Label titleLabel = new Label("Title:");
        TextField titleField = new TextField();

        Label authorsLabel = new Label("Author(s):");
        TextField authorsField = new TextField();

        Label abstractLabel = new Label("Abstract:");
        TextField abstractField = new TextField();

        Label keywordsLabel = new Label("Keywords:");
        TextField keywordsField = new TextField();

        Label bodyLabel = new Label("Body:");
        TextField bodyField = new TextField();

        Label referencesLabel = new Label("References:");
        TextField referencesField = new TextField();

        // Save button to create and save the article
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            try {
                // Create a new Article object with entered details
                Article article = new Article();
                article.setTitle(titleField.getText().toCharArray());
                article.setAuthors(authorsField.getText().toCharArray());
                article.setAbstractText(abstractField.getText().toCharArray());
                article.setKeywords(keywordsField.getText().toCharArray());
                article.setBody(bodyField.getText().toCharArray());
                article.setReferences(referencesField.getText().toCharArray());

                // Save article to the database
                databaseHelper.createArticle(article);
                System.out.println("Article created successfully.");

                // Clear input fields after saving
                titleField.clear();
                authorsField.clear();
                abstractField.clear();
                keywordsField.clear();
                bodyField.clear();
                referencesField.clear();

                // Navigate back to Dashboard
                stage.setScene(new Scene(new Dashboard(stage, databaseHelper), 500, 400));
            } catch (Exception ex) {
                ex.printStackTrace(); // Print any errors to console
            }
        });
        
        // Button to go back to the Dashboard
        Button backButton = new Button("Back to Dashboard");
        backButton.setOnAction(e -> stage.setScene(new Scene(new Dashboard(stage, databaseHelper), 500, 400)));

        // Add components to grid layout
        add(titleLabel, 0, 0);
        add(titleField, 1, 0);
        add(authorsLabel, 0, 1);
        add(authorsField, 1, 1);
        add(abstractLabel, 0, 2);
        add(abstractField, 1, 2);
        add(keywordsLabel, 0, 3);
        add(keywordsField, 1, 3);
        add(bodyLabel, 0, 4);
        add(bodyField, 1, 4);
        add(referencesLabel, 0, 5);
        add(referencesField, 1, 5);
        add(saveButton, 1, 6);
        add(backButton, 1, 7);

        setStyle("-fx-background-color: floralwhite;"); // Set background color
    }
}
