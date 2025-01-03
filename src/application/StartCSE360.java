package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartCSE360 extends Application {
    // UI Styling Properties for buttons and labels
    public static String blueBackground = "-fx-background-color: lightblue;";
    public static String baseBackground = "-fx-background-color: floralwhite;";
    public static String h1 = "-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #4b5d75;";
    public static String h2 = "-fx-font-size: 16px;";
    public static String h3bold = "-fx-font-size: 14px; -fx-font-weight: bold";
    public static String errorText = "-fx-text-fill: red";
    public static String buttonStyle = "-fx-background-color: lightblue; -fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #4b5d75;";

    private DatabaseHelper databaseHelper;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // VBox for the application title and description
        VBox title = new VBox(); // Container for title section
        title.setPadding(new Insets(10)); // Set padding for title section
        title.setAlignment(Pos.CENTER); // Center-align title
        title.setStyle(blueBackground); // Apply blue background style
        
        // Create the main title label
        Label label = new Label("Welcome to BookedIn"); // Application title
        label.setStyle(h1); // Apply title text styling

        databaseHelper = new DatabaseHelper();
        databaseHelper.connectToDatabase(); // Establish database connection

        // Check if admin setup is complete, otherwise show admin registration
        if (!databaseHelper.isAdminSetupComplete()) {
            primaryStage.setScene(new Scene(new AdminRegistrationScreen(primaryStage, databaseHelper), 500, 400));
            primaryStage.show();
        } else {
            showLoginScreen(primaryStage, databaseHelper); // Show login screen if admin setup is complete
        }
    }

    // Method to display the login screen
    public void showLoginScreen(Stage stage, DatabaseHelper databaseHelper) throws Exception {
        stage.setScene(new Scene(new LoginScreen(stage, databaseHelper), 400, 300));
        stage.show();
    }

    // Method to display the dashboard
    void showDashboard(Stage stage) {
        try {
            Dashboard dashboardScreen = new Dashboard(stage, databaseHelper);
            stage.setScene(new Scene(dashboardScreen, 500, 400));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace(); // Print any errors to console
        }
    }

    public static void main(String[] args) {
        launch(args); // Launch the application
    }
}
