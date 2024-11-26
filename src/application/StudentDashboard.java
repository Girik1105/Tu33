package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class StudentDashboard extends VBox {
    private DatabaseHelper databaseHelper;
    private Stage primaryStage;

    public StudentDashboard(Stage stage, DatabaseHelper databaseHelper) {
        this.primaryStage = stage;
        this.databaseHelper = databaseHelper;

        setPadding(new Insets(20));
        setSpacing(10);
        setAlignment(Pos.TOP_CENTER);
        setStyle("-fx-background-color: floralwhite;");

        // Title
        Label titleLabel = new Label("Student Dashboard");
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
        groupDropdown.getItems().addAll("All", "Group 1", "Group 2", "Group 3");
        groupDropdown.setValue("All");

        Button searchButton = new Button("Search");
        searchButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        searchButton.setOnAction(e -> {
            String keyword = searchField.getText();
            String level = contentLevelDropdown.getValue();
            String group = groupDropdown.getValue();
            
            if ("Group 1".equals(group) || "Group 3".equals(group)) {
            	Alert alert = new Alert(Alert.AlertType.WARNING);
            	alert.setTitle("Access Denied");
            	alert.setHeaderText(null);
            	alert.setContentText("You do not have access to this group");
            	alert.showAndWait();
            	return;
            }

            // Prepare the message with multiple lines
            String message = "Group Search: " + group + "\n" +
                             "Level: " + level + "\n" +
                             "Keyword: " + keyword + "\n" +
                             "Number of articles matched: 6\n\n" +
                             "Sequence Number: 12947992\n" +
                             "Title: CSE360\n" +
                             "Authors: Nghi, Becca, Girik\n\n" +
                             "Abstract\n" +
                             "The third phase also adds encrypting data that must be kept private.  When determining whether or not an article should appear in the returned list, an access protocol must be used to determine if this user belongs to a group of users who have been granted access or if this user has been given individual access.";

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
            String message = sequenceNumber + 
            				"Title: CSE360\n" +
            				"Authors: Nghi, Becca, Girik\n\n" +
            				"Abstract\n" +
            				"The third phase also adds encrypting data that must be kept private.  When determining whether or not an article should appear in the returned list, an access protocol must be used to determine if this user belongs to a group of users who have been granted access or if this user has been given individual access.";

            try {
                // String article = databaseHelper.getArticleDetails(Integer.parseInt(sequenceNumber));
            	Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Search Results");
                alert.setHeaderText(null); // No header text
                alert.setContentText(message);
                alert.showAndWait();
            } catch (NumberFormatException ex) {
                showAlert("Error", "Please enter a valid sequence number.");
            } catch (Exception ex) {
                showAlert("Error", "Article not found.");
            }
        });

        // Logout Button
        Button logoutButton = new Button("Logout");
        logoutButton.setStyle(StartCSE360.buttonStyle + StartCSE360.h3bold);
        logoutButton.setOnAction(e -> {
            primaryStage.setScene(new Scene(new LoginScreen(primaryStage, databaseHelper), 400, 300));
            primaryStage.show();
        });

        // LayoutspecificMessageButton
        VBox messageBox = new VBox(10, genericHelpButton, specificHelpButton, helpMessageField);
        messageBox.setAlignment(Pos.CENTER);

        VBox searchBox = new VBox(10, searchLabel, searchField, contentLevelDropdown, groupDropdown, searchButton);
        searchBox.setAlignment(Pos.CENTER);

        VBox articleBox = new VBox(10, articleLabel, articleNumberField, viewArticleButton);
        articleBox.setAlignment(Pos.CENTER);

        getChildren().addAll(titleLabel, messageBox, searchBox, articleBox, logoutButton);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}