package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class ListSpecialAccessGroupArticlesScreen extends VBox {
    public ListSpecialAccessGroupArticlesScreen(Stage stage, DatabaseHelper databaseHelper, int groupId) {
        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setSpacing(10);

        Label headerLabel = new Label("Articles in Group ID: " + groupId);
        ListView<Article> articleListView = new ListView<>();

        try {
            List<Article> articles = databaseHelper.listArticlesBySpecialGroup(groupId);
            articleListView.getItems().addAll(articles);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> stage.setScene(new Scene(new ListSpecialAccessGroupsScreen(stage, databaseHelper), 500, 400)));

        getChildren().addAll(headerLabel, articleListView, backButton);
    }
}
