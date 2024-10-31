package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

public class ListArticleScreen extends VBox {

    // Constructor initializes the list articles screen UI
    public ListArticleScreen(Stage stage, DatabaseHelper databaseHelper) {
        setAlignment(Pos.CENTER); // Center-aligns content
        setPadding(new Insets(20)); // Adds padding around VBox
        setSpacing(10); // Adds spacing between elements

        Label headerLabel = new Label("List of Articles:");
        
        // Text area to display articles; non-editable
        TextArea articlesArea = new TextArea();
        articlesArea.setEditable(false);

        // Back button to return to the Dashboard
        Button backButton = new Button("Back to Dashboard");
        backButton.setOnAction(e -> stage.setScene(new Scene(new Dashboard(stage, databaseHelper), 500, 400)));

        try {
            // Fetch list of articles from the database
            List<Article> articles = databaseHelper.listArticles();
            if (articles.isEmpty()) {
                articlesArea.setText("No articles found."); // Display if no articles
            } else {
                // Build string representation of articles
                StringBuilder articleText = new StringBuilder();
                for (Article article : articles) {
                    articleText.append("ID: ").append(article.getId()).append("\n")
                               .append("Title: ").append(article.getTitle()).append("\n")
                               .append("Authors: ").append(article.getAuthors()).append("\n")
                               .append("Abstract: ").append(article.getAbstractText()).append("\n")
                               .append("Keywords: ").append(article.getKeywords()).append("\n")
                               .append("Body: ").append(article.getBody()).append("\n")
                               .append("References: ").append(article.getReferences()).append("\n")
                               .append("----------------------------------------\n");
                }
                articlesArea.setText(articleText.toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace(); // Print any errors to console
            articlesArea.setText("An error occurred while retrieving articles.");
        }

        getChildren().addAll(headerLabel, articlesArea, backButton); // Add elements to VBox
    }
}
