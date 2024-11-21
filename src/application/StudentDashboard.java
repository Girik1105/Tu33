package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentDashboard extends VBox {

    private Stage primaryStage;
    private DatabaseHelper databaseHelper;

    // Constructor initializes the student dashboard UI
    public StudentDashboard(Stage stage, DatabaseHelper databaseHelper) {
        this.primaryStage = stage;
        this.databaseHelper = databaseHelper;

        setStyle("-fx-background-color: floralwhite;"); // Set a background color for student dashboard

        // VBox for the dashboard title
        VBox titleBox = new VBox();
        titleBox.setPadding(new Insets(10));
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setStyle(StartCSE360.blueBackground);

        // Main title label
        Label titleLabel = new Label("Student Dashboard");
        titleLabel.setStyle(StartCSE360.h1);
        titleBox.getChildren().add(titleLabel);

        // VBox layout for dashboard features
        VBox featureBox = new VBox(20);
        featureBox.setStyle(StartCSE360.baseBackground);
        featureBox.setAlignment(Pos.CENTER);
        featureBox.setPadding(new Insets(20));

        // Feature labels and buttons
        Label welcomeLabel = new Label("Welcome, Student!");
        welcomeLabel.setStyle(StartCSE360.h2);

        Button viewArticlesButton = new Button("View Help Articles");
        viewArticlesButton.setStyle(StartCSE360.buttonStyle + StartCSE360.h3bold);
        viewArticlesButton.setOnAction(e -> {
            System.out.println("Navigating to View Help Articles...");
            // Add logic to navigate to articles viewing screen
        });

        Button submitQueryButton = new Button("Request Help");
        submitQueryButton.setStyle(StartCSE360.buttonStyle + StartCSE360.h3bold);
        submitQueryButton.setOnAction(e -> {
            System.out.println("Navigating to Submit Query...");
            // Add logic to navigate to query submission screen
        });

        Button logoutButton = new Button("Logout");
        logoutButton.setStyle(StartCSE360.buttonStyle + StartCSE360.h3bold);
        logoutButton.setOnAction(e -> {
            System.out.println("Logging out...");
            primaryStage.setScene(new Scene(new LoginScreen(primaryStage, databaseHelper), 400, 300));
            primaryStage.show();
        });

        featureBox.getChildren().addAll(welcomeLabel, viewArticlesButton, submitQueryButton, logoutButton);

        // Set alignment and add all elements to the main layout
        setAlignment(Pos.TOP_CENTER);
        getChildren().addAll(titleBox, featureBox);
    }
}

