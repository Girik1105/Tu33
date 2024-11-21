package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Group class to manage article data
class Group {
    private final int id;
    private final String name;
    private final ObservableList<Article1> articles = FXCollections.observableArrayList();

    public Group(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ObservableList<Article1> getArticles() {
        return articles;
    }

    @Override
    public String toString() {
        return name;
    }
}

// Group Articles Management Screen for Instructors
public class GroupArticlesScreen extends VBox {

    public GroupArticlesScreen(Stage stage, Group group, DatabaseHelper databaseHelper) {
        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setSpacing(10);

        Label headerLabel = new Label("Articles in " + group.getName());
        ListView<Article1> articleListView = new ListView<>(group.getArticles());

        Button addArticleButton = new Button("Add Article");
        addArticleButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add Article");
            dialog.setHeaderText("Enter Article Details (Title; Author; Abstract; Keywords)");

            dialog.showAndWait().ifPresent(input -> {
                String[] details = input.split(";");
                if (details.length >= 4) {
                    Article1 article = new Article1(details[0], details[1], details[2], details[3]);
                    group.getArticles().add(article);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input. Format: Title; Author; Abstract; Keywords");
                    alert.show();
                }
            });
        });

        Button viewArticleButton = new Button("View Article Details");
        viewArticleButton.setOnAction(e -> {
            Article1 selectedArticle = articleListView.getSelectionModel().getSelectedItem();
            if (selectedArticle != null) {
                stage.setScene(new Scene(new ArticleDetailScreen(stage, group, selectedArticle, databaseHelper), 500, 400));
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> stage.setScene(new Scene(new InstructorDashboard(stage, databaseHelper), 500, 400)));

        getChildren().addAll(headerLabel, articleListView, addArticleButton, viewArticleButton, backButton);
    }
}

// Article1 class for managing individual article details
class Article1 {
    private String title;
    private String author;
    private String abstractText;
    private String keywords;

    public Article1(String title, String author, String abstractText, String keywords) {
        this.title = title;
        this.author = author;
        this.abstractText = abstractText;
        this.keywords = keywords;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getAbstractText() {
        return abstractText;
    }

    public String getKeywords() {
        return keywords;
    }

    @Override
    public String toString() {
        return title + " by " + author;
    }
}

// ArticleDetailScreen class for viewing article details
class ArticleDetailScreen extends VBox {
    public ArticleDetailScreen(Stage stage, Group group, Article1 article, DatabaseHelper databaseHelper) {
        setPadding(new Insets(20));
        setSpacing(10);
        setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Article Details");
        titleLabel.setStyle(StartCSE360.h2);

        Label titleLabelText = new Label("Title: " + article.getTitle());
        Label authorLabel = new Label("Author: " + article.getAuthor());
        Label abstractLabel = new Label("Abstract: " + article.getAbstractText());
        Label keywordsLabel = new Label("Keywords: " + article.getKeywords());

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> stage.setScene(new Scene(new GroupArticlesScreen(stage, group, databaseHelper), 500, 400)));

        getChildren().addAll(titleLabel, titleLabelText, authorLabel, abstractLabel, keywordsLabel, backButton);
    }
}
