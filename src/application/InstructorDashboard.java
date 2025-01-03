package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class InstructorDashboard extends VBox {

    public InstructorDashboard(Stage stage, DatabaseHelper databaseHelper) {
    	
        // Title
        Label titleLabel = new Label("Instructor Dashboard");
        titleLabel.setStyle(StartCSE360.h1);

        VBox buttonBox = new VBox(10); // Buttons stacked vertically with spacing
        buttonBox.setAlignment(Pos.CENTER); // Center the buttons

        
        TextField helpMessageField = new TextField();
        helpMessageField.setPromptText("Enter your help message or details here...");
        helpMessageField.setPrefWidth(300);
        
        Button genericHelpButton = new Button("Send Generic Help Message");
        genericHelpButton.setStyle(StartCSE360.buttonStyle);
		genericHelpButton.setOnAction(e -> {
            String userMessage = helpMessageField.getText(); // Get the typed message
            if (userMessage.isEmpty()) {
                userMessage = "No specific message provided."; // Default if no text is entered
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Help Message Sent");
            alert.setHeaderText(null); // No header text
            alert.setContentText("Your generic help message has been sent successfully!\nMessage: " + userMessage);
            alert.showAndWait(); // Show the alert and wait for user acknowledgment
        });

        Button specificHelpButton = new Button("Send Specific Help Message");
        specificHelpButton.setStyle(StartCSE360.buttonStyle);
        specificHelpButton.setOnAction(e -> {
            String userMessage = helpMessageField.getText(); // Get the typed message
            if (userMessage.isEmpty()) {
                userMessage = "No specific message provided."; // Default if no text is entered
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Help Message Sent");
            alert.setHeaderText(null);
            alert.setContentText("Your specific help message has been sent successfully!\nMessage: " + userMessage);
            alert.showAndWait(); // Show the alert and wait for user acknowledgment
        });

        buttonBox.getChildren().addAll(genericHelpButton, specificHelpButton, helpMessageField); // Add in order


        // Search Section
        Label searchLabel = new Label("Search Help Articles");
        searchLabel.setStyle(StartCSE360.h2);

        TextField searchField = new TextField();
        searchField.setPromptText("Enter keywords, title, or identifier");

        ComboBox<String> contentLevelDropdown = new ComboBox<>();
        contentLevelDropdown.getItems().addAll("All", "Beginner", "Intermediate", "Advanced", "Expert");
        contentLevelDropdown.setValue("All");

        ComboBox<String> groupDropdown = new ComboBox<>();
        groupDropdown.getItems().addAll("All", "Assignment 1", "Assignment 2", "Assignment 3");
        groupDropdown.setValue("All");

        Button searchButton = new Button("Search");
        searchButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        searchButton.setOnAction(e -> {
            String keyword = searchField.getText();
            String level = contentLevelDropdown.getValue();
            String group = groupDropdown.getValue();

            // Prepare the message with multiple lines
            String message = "Group Search: " + group + "\n" +
                             "Level: " + level + "\n" +
                             "Keyword: " + keyword;

            // Show a single alert with all the details
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Search Results");
            alert.setHeaderText(null); // No header text
            alert.setContentText(message);
            alert.showAndWait();
        });

        // Article Actions
        Label articleLabel = new Label("View Article by Sequence Number");
        articleLabel.setStyle(StartCSE360.h2);

        TextField articleNumberField = new TextField();
        articleNumberField.setPromptText("Enter sequence number");

        Button viewArticleButton = new Button("View Article");
        viewArticleButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        viewArticleButton.setOnAction(e -> {
            String sequenceNumber = articleNumberField.getText();
            try {
                // String article = databaseHelper.getArticleDetails(Integer.parseInt(sequenceNumber));
                showAlert("Results for:", sequenceNumber);
            } catch (NumberFormatException ex) {
                showAlert("Error", "Please enter a valid sequence number.");
            } catch (Exception ex) {
                showAlert("Error", "Article not found.");
            }
        });
        
        // LayoutspecificMessageButton
        VBox messageBox = new VBox(10, genericHelpButton, specificHelpButton, helpMessageField);
        messageBox.setAlignment(Pos.CENTER);

        VBox searchBox = new VBox(10, searchLabel, searchField, contentLevelDropdown, groupDropdown, searchButton);
        searchBox.setAlignment(Pos.CENTER);

        VBox articleBox = new VBox(10, articleLabel, articleNumberField, viewArticleButton);
        articleBox.setAlignment(Pos.CENTER);

        // Button to navigate to Create Article screen
        Button createArticleButton = new Button("Create Article");
        createArticleButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        createArticleButton.setOnAction(e -> {
            stage.setScene(new Scene(new CreateArticleScreen(stage, databaseHelper), 500, 400));
        });

        // Button to navigate to Delete Article screen
        Button deleteArticleButton = new Button("Delete Article");
        deleteArticleButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        deleteArticleButton.setOnAction(e -> {
            stage.setScene(new Scene(new DeleteArticleScreen(stage, databaseHelper), 500, 400));
        });

        /*/ Button to navigate to List Articles screen
        Button listArticlesButton = new Button("List Articles");
        listArticlesButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        listArticlesButton.setOnAction(e -> {
            stage.setScene(new Scene(new ListSpecialAccessGroups2(stage, databaseHelper), 500, 400));
        });*/
        
        // Button to list special access groups
        Button listGroupsButton = new Button("List Special Access Groups");
        listGroupsButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        listGroupsButton.setOnAction(e -> {
            stage.setScene(new Scene(new ListSpecialAccessGroups2(stage, databaseHelper), 500, 400));
        });

        // Button to back up articles
        Button backupArticlesButton = new Button("Backup Articles");
        backupArticlesButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        backupArticlesButton.setOnAction(e -> {
            stage.setScene(new Scene(new BackupArticleScreen(stage, databaseHelper), 500, 400));
        });

        // Button to restore articles
        Button restoreArticlesButton = new Button("Restore Articles");
        restoreArticlesButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        restoreArticlesButton.setOnAction(e -> {
            stage.setScene(new Scene(new RestoreArticleScreen(stage, databaseHelper), 500, 400));
        });

        // Button to view and manage users
        Button viewUsersButton = new Button("View Users");
        viewUsersButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        viewUsersButton.setOnAction(e -> {
            stage.setScene(new Scene(new ViewUserScreen(stage, databaseHelper), 500, 400));
        });

        // Logout button
        Button logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-background-color: lightcoral;" + StartCSE360.h3bold);
        logoutButton.setOnAction(e -> {
            try {
                new StartCSE360().showLoginScreen(stage, databaseHelper);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Layout settings
        setAlignment(Pos.CENTER);
        getChildren().addAll(
        		titleLabel, genericHelpButton, specificHelpButton, helpMessageField,
        		messageBox, searchBox, articleBox,
        		createArticleButton,
        		deleteArticleButton,
                listGroupsButton, 
                backupArticlesButton, 
                restoreArticlesButton, 
                viewUsersButton, 
                //listUserAccessButton, 
                logoutButton
        );
        setSpacing(10);
        setStyle("-fx-background-color: floralwhite;");
    }
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
