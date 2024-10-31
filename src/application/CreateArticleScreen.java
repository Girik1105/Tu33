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

    public CreateArticleScreen(Stage stage, DatabaseHelper databaseHelper) {
        setPadding(new Insets(20));
        setHgap(10);
        setVgap(10);
        setAlignment(Pos.CENTER);

        // Labels and TextFields for Article details
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

        // Save button
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            try {
                // Create a new Article object with field data
                Article article = new Article();
                article.setTitle(titleField.getText().toCharArray());
                article.setAuthors(authorsField.getText().toCharArray());
                article.setAbstractText(abstractField.getText().toCharArray());
                article.setKeywords(keywordsField.getText().toCharArray());
                article.setBody(bodyField.getText().toCharArray());
                article.setReferences(referencesField.getText().toCharArray());

                // Save to database
                databaseHelper.createArticle(article);
                System.out.println("Article created successfully.");

                // Clear fields after saving
                titleField.clear();
                authorsField.clear();
                abstractField.clear();
                keywordsField.clear();
                bodyField.clear();
                referencesField.clear();

                // Return to dashboard
                stage.setScene(new Scene(new Dashboard(stage, databaseHelper), 500, 400));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        
        Button backButton = new Button("Back to Dashboard");
        backButton.setOnAction(e -> stage.setScene(new Scene(new Dashboard(stage, databaseHelper), 500, 400)));

        // Add components to grid
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

        setStyle("-fx-background-color: floralwhite;");
    }
}
