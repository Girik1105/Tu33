package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.h2.engine.Database;

import java.util.ArrayList;
import java.util.List;

// Group class to manage article data
class Group {
    private final int id;
    private final String name;
    private final ObservableList<Article1> articles = FXCollections.observableArrayList();

    public Group(int id, String name) {
        this.id = id;
        this.name = name;
        
        // Default article added when the group is created
        if (id == 3) {  // Check if this is Group 3
            Article1 defaultArticle = new Article1(
                "Of Mice and Men",              // Title
                "John Steinbeck",              // Author
                "Great Depression",
                "American Dream",
                "Steinbeck" // Reference
            );
            articles.add(defaultArticle);  // Add default article
        }
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

// Article1 class for managing individual article details
class Article1 {
    private String title;
    private String author;
    private String abstractText;
    private String keywords;
    private String references;  // Added reference field

    public Article1(String title, String author, String abstractText, String keywords, String references) {
        this.title = title;
        this.author = author;
        this.abstractText = abstractText;
        this.keywords = keywords;
        this.references = references;
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

    public String getReferences() {
        return references;  // Get references
    }

    @Override
    public String toString() {
        return title + " by " + author;
    }
}

// Group Articles Management Screen for Instructors
class GroupArticlesScreen extends VBox {

    public GroupArticlesScreen(Stage stage, Group group, DatabaseHelper databaseHelper) {
        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setSpacing(10);

        Label headerLabel = new Label("Articles in " + group.getName());
        ListView<Article1> articleListView = new ListView<>(group.getArticles());

        /*// Button to add a new article
        Button addArticleButton = new Button("Add Article");
        addArticleButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add Article");
            dialog.setHeaderText("Enter Article Details (Title; Author; Abstract; Keywords; References)");

            dialog.showAndWait().ifPresent(input -> {
                String[] details = input.split(";");
                if (details.length >= 5) {
                    Article1 article = new Article1(details[0], details[1], details[2], details[3], details[4]);
                    group.getArticles().add(article);  // Add the new article to the group
                    articleListView.setItems(group.getArticles()); // Refresh the ListView
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input. Format: Title; Author; Abstract; Keywords; References");
                    alert.show();
                }
            });
        });*/

        // Label for article input section
        Label articleDetailsLabel = new Label("Enter Article Details:");

        // Create text fields for article details
        TextField titleField = new TextField();
        titleField.setPromptText("Title");

        TextField authorField = new TextField();
        authorField.setPromptText("Author");

        TextField abstractField = new TextField();
        abstractField.setPromptText("Abstract");

        TextField keywordsField = new TextField();
        keywordsField.setPromptText("Keywords");

        TextField referencesField = new TextField();
        referencesField.setPromptText("References");

        //Button to add new article to the group (using text fields)
        Button addArticleFromFieldsButton = new Button("Add Article");
        addArticleFromFieldsButton.setOnAction(e -> {
            String title = titleField.getText();
            String author = authorField.getText();
            String abstractText = abstractField.getText();
            String keywords = keywordsField.getText();
            String references = referencesField.getText();

            // Ensure that all fields have been filled
            if (!title.isEmpty() && !author.isEmpty() && !abstractText.isEmpty() && !keywords.isEmpty() && !references.isEmpty()) {
                // Create a new article and add it to the group
                Article1 newArticle = new Article1(title, author, abstractText, keywords, references);
                group.getArticles().add(newArticle);  // Add article to group
                articleListView.setItems(group.getArticles()); // Refresh the ListView
                clearFields(titleField, authorField, abstractField, keywordsField, referencesField); // Clear input fields
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all fields.");
                alert.show();
            }
        });

        // Button to go back to the previous screen
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> stage.setScene(new Scene(new InstructorDashboard(stage, databaseHelper), 500, 400)));

        // Button to view selected article details
        Button viewArticleButton = new Button("View Article Details");
        viewArticleButton.setOnAction(e -> {
            Article1 selectedArticle = articleListView.getSelectionModel().getSelectedItem();
            if (selectedArticle != null) {
                stage.setScene(new Scene(new ArticleDetailScreen(stage, group, selectedArticle, databaseHelper), 500, 400));
            }
        });

        // Add components to the layout
        getChildren().addAll(
            headerLabel, 
            articleListView, 
            //addArticleButton, 
            articleDetailsLabel, 
            titleField, 
            authorField, 
            abstractField, 
            keywordsField, 
            referencesField, 
            addArticleFromFieldsButton, 
            viewArticleButton, 
            backButton
        );
    }

    // Helper method to clear the input fields after adding an article
    private void clearFields(TextField titleField, TextField authorField, TextField abstractField, TextField keywordsField, TextField referencesField) {
        titleField.clear();
        authorField.clear();
        abstractField.clear();
        keywordsField.clear();
        referencesField.clear();
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
        Label referencesLabel = new Label("References: " + article.getReferences());  // Show references

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> stage.setScene(new Scene(new GroupArticlesScreen(stage, group, databaseHelper), 500, 400)));

        getChildren().addAll(titleLabel, titleLabelText, authorLabel, abstractLabel, keywordsLabel, referencesLabel, backButton);
    }
}

// ListSpecialAccessGroups2 class to manage groups
public class ListSpecialAccessGroups2 extends VBox {
    private final List<Group> groups = new ArrayList<>();

    public ListSpecialAccessGroups2(Stage stage, DatabaseHelper databaseHelper) {
        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setSpacing(10);

        Label headerLabel = new Label("Special Access Groups");
        ListView<Group> groupListView = new ListView<>();

        // Hardcode three initial groups
        groups.add(new Group(1, "Group 1"));
        groups.add(new Group(2, "Group 2"));
        groups.add(new Group(3, "Group 3"));
        groupListView.getItems().addAll(groups);

        Button addGroupButton = new Button("Add Group");
        addGroupButton.setOnAction(e -> {
            Group newGroup = new Group(groups.size() + 1, "Group " + (groups.size() + 1));
            groups.add(newGroup);
            groupListView.getItems().add(newGroup);
        });

        Button viewGroupButton = new Button("View Articles in Group");
        viewGroupButton.setOnAction(e -> {
            Group selectedGroup = groupListView.getSelectionModel().getSelectedItem();
            if (selectedGroup != null) {
                // Check if the user is trying to access Group 1 or Group 3
                if (selectedGroup.getId() == 1 || selectedGroup.getId() == 2) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "You don't have access to this group!");
                    alert.show();
                } else {
                    stage.setScene(new Scene(new GroupArticlesScreen(stage, selectedGroup, databaseHelper), 500, 400));
                }
            }
        });

        Button backButton = new Button("Back to Login");
        backButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        backButton.setOnAction(e -> stage.setScene(new Scene(new InstructorDashboard(stage, databaseHelper), 400, 300)));

        getChildren().addAll(headerLabel, groupListView, addGroupButton, viewGroupButton, backButton);
    }
}
