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
            List<ArticleSummary> articles = databaseHelper.listArticles();
            if (articles.isEmpty()) {
                articlesArea.setText("No articles found.");
            } else {
                StringBuilder articleText = new StringBuilder();
                for (ArticleSummary summary : articles) {
                    articleText.append("ID: ").append(summary.getId())
                               .append(", Title: ").append(summary.getTitle())
                               .append(", Authors: ").append(summary.getAuthors()).append("\n");
                }
                articlesArea.setText(articleText.toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        getChildren().addAll(headerLabel, articlesArea, backButton);
    }
}
