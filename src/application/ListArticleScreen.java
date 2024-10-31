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

    public ListArticleScreen(Stage stage, DatabaseHelper databaseHelper) {
        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setSpacing(10);

        Label headerLabel = new Label("List of Articles:");
        TextArea articlesArea = new TextArea();
        articlesArea.setEditable(false);

        Button backButton = new Button("Back to Dashboard");
        backButton.setOnAction(e -> stage.setScene(new Scene(new Dashboard(stage, databaseHelper), 500, 400)));

        try {
            List<Article> articles = databaseHelper.listArticles();
            if (articles.isEmpty()) {
                articlesArea.setText("No articles found.");
            } else {
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
            ex.printStackTrace();
            articlesArea.setText("An error occurred while retrieving articles.");
        }

        getChildren().addAll(headerLabel, articlesArea, backButton);
    }
}
