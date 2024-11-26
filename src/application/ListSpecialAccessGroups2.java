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
                "Historical Fiction", 
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

// Article1 class for managing individual article details\
class Article1 {
 private String title;
 private String author;
 private String abstractText;
 private String body; // Added body field
 private String keywords;
 private String references;

 public Article1(String title, String author, String abstractText, String keywords, String references, String body) {
     this.title = title;
     this.author = author;
     this.abstractText = abstractText;
     this.body = body; // Initialize body
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
 
 public String getBody() {
     return body; // Getter for body
 }

 public String getKeywords() {
     return keywords;
 }

 public String getReferences() {
     return references;
 }

 @Override
 public String toString() {
     return title + " by " + author;
 }
}

// Group Articles Management Screen for Instructors
//Updated GroupArticlesScreen class
class GroupArticlesScreen extends VBox {

 public GroupArticlesScreen(Stage stage, Group group, DatabaseHelper databaseHelper) {
     setAlignment(Pos.CENTER);
     setPadding(new Insets(20));
     setSpacing(10);

     Label headerLabel = new Label("Articles in " + group.getName());
     ListView<Article1> articleListView = new ListView<>(group.getArticles());

     // Fields for article details
     Label articleDetailsLabel = new Label("Enter Article Details:");
     TextField titleField = new TextField();
     titleField.setPromptText("Title");

     TextField authorField = new TextField();
     authorField.setPromptText("Author");

     TextField abstractField = new TextField();
     abstractField.setPromptText("Abstract");
     
     TextArea bodyField = new TextArea(); // TextArea for the body field
     bodyField.setPromptText("Body");

     TextField keywordsField = new TextField();
     keywordsField.setPromptText("Keywords");

     TextField referencesField = new TextField();
     referencesField.setPromptText("References");

     // Add Article Button
     Button addArticleFromFieldsButton = new Button("Add Article");
     addArticleFromFieldsButton.setOnAction(e -> {
         String title = titleField.getText();
         String author = authorField.getText();
         String abstractText = abstractField.getText();
         String keywords = keywordsField.getText();
         String references = referencesField.getText();
         String body = bodyField.getText();

         if (!title.isEmpty() && !author.isEmpty() && !abstractText.isEmpty() && 
             !keywords.isEmpty() && !references.isEmpty() && !body.isEmpty()) {
             Article1 newArticle = new Article1(title, author, abstractText, keywords, references, body);
             group.getArticles().add(newArticle); // Add article to group
             articleListView.setItems(group.getArticles()); // Refresh ListView
             clearFields(titleField, authorField, abstractField, keywordsField, referencesField, bodyField);
         } else {
             Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all fields.");
             alert.show();
         }
     });

     // Delete Article Button
     Button deleteArticleButton = new Button("Delete Selected Article");
     deleteArticleButton.setOnAction(e -> {
         Article1 selectedArticle = articleListView.getSelectionModel().getSelectedItem();
         if (selectedArticle != null) {
             group.getArticles().remove(selectedArticle); // Remove the selected article
             articleListView.setItems(group.getArticles()); // Refresh ListView
         } else {
             Alert alert = new Alert(Alert.AlertType.ERROR, "No article selected to delete.");
             alert.show();
         }
     });
     
     Button editButton = new Button("Edit Selected Article");
     editButton.setOnAction(e -> {
         Article1 selectedArticle = articleListView.getSelectionModel().getSelectedItem();
         if (selectedArticle != null) {
             group.getArticles().remove(selectedArticle); // Remove the selected article
             articleListView.setItems(group.getArticles()); // Refresh ListView
         } else {
             Alert alert = new Alert(Alert.AlertType.ERROR, "No article selected to delete.");
             alert.show();
         }
     });

     // View Article Details Button
     Button viewArticleButton = new Button("View Article Details");
     viewArticleButton.setOnAction(e -> {
         Article1 selectedArticle = articleListView.getSelectionModel().getSelectedItem();
         if (selectedArticle != null) {
             stage.setScene(new Scene(new ArticleDetailScreen(stage, group, selectedArticle, databaseHelper), 500, 400));
         }
     });

     // Back Button
     Button backButton = new Button("Back");
     backButton.setOnAction(e -> stage.setScene(new Scene(new InstructorDashboard(stage, databaseHelper), 500, 400)));

     // Layout
     getChildren().addAll(
         headerLabel, 
         articleListView, 
         articleDetailsLabel, 
         titleField, 
         authorField, 
         abstractField, 
         keywordsField, 
         referencesField, 
         bodyField, 
         addArticleFromFieldsButton,
         editButton,
         deleteArticleButton, 
         viewArticleButton, 
         backButton
     );
 }
 
//Helper method to clear the input fields after adding an article
 private void clearFields(TextField titleField, TextField authorField, TextField abstractField, 
                          TextField keywordsField, TextField referencesField, TextArea bodyField) {
     titleField.clear();
     authorField.clear();
     abstractField.clear();
     keywordsField.clear();
     referencesField.clear();
     bodyField.clear(); // Clear body field
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
        Label bodyLabel = new Label("Body: " + article.getBody()); // Display body
        Label keywordsLabel = new Label("Keywords: " + article.getKeywords());
        Label referencesLabel = new Label("References: " + article.getReferences());

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> stage.setScene(new Scene(new GroupArticlesScreen(stage, group, databaseHelper), 500, 600)));

        getChildren().addAll(titleLabel, titleLabelText, authorLabel, abstractLabel, bodyLabel, keywordsLabel, referencesLabel, backButton);
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
                    stage.setScene(new Scene(new GroupArticlesScreen(stage, selectedGroup, databaseHelper), 500, 1000));
                }
            }
        });
        // Add User to the selected group
        Button addUserButton = new Button("Add User to Group");
        addUserButton.setOnAction(e -> {
            Group selectedGroup = groupListView.getSelectionModel().getSelectedItem();
            if (selectedGroup != null) {
                stage.setScene(new Scene(new ManageGroupUserScreen(stage, databaseHelper, selectedGroup.getId(), "add"), 500, 400));
            } else {
                System.out.println("No group selected.");
            }
        });

        // Update User Permissions in the selected group
        Button updateUserButton = new Button("Update User Permissions");
        updateUserButton.setOnAction(e -> {
            Group selectedGroup = groupListView.getSelectionModel().getSelectedItem();
            if (selectedGroup != null) {
                stage.setScene(new Scene(new ManageGroupUserScreen(stage, databaseHelper, selectedGroup.getId(), "update"), 500, 400));
            } else {
                System.out.println("No group selected.");
            }
        });

        // Remove User from the selected group
        Button removeUserButton = new Button("Remove User from Group");
        removeUserButton.setOnAction(e -> {
            Group selectedGroup = groupListView.getSelectionModel().getSelectedItem();
            if (selectedGroup != null) {
                stage.setScene(new Scene(new ManageGroupUserScreen(stage, databaseHelper, selectedGroup.getId(), "remove"), 500, 400));
            } else {
                System.out.println("No group selected.");
            }
        });
        
        // Remove Special Access Group
        Button removeGroupButton = new Button("Remove Group");
        removeGroupButton.setOnAction(e -> stage.setScene(new Scene(new CreateSpecialAccessGroupScreen(stage, databaseHelper), 500, 400)));
        
        Button backButton = new Button("Back");
        backButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        backButton.setOnAction(e -> stage.setScene(new Scene(new InstructorDashboard(stage, databaseHelper), 500, 600)));

        getChildren().addAll(headerLabel, groupListView, addUserButton, updateUserButton, removeUserButton, addGroupButton, viewGroupButton, removeGroupButton, backButton);
    }
}
