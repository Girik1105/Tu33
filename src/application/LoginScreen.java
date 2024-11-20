package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginScreen extends VBox {

    private DatabaseHelper databaseHelper;
    private Stage primaryStage;

    // Constructor initializes the login screen UI
    public LoginScreen(Stage stage, DatabaseHelper databaseHelper) {
        this.primaryStage = stage;
        this.databaseHelper = databaseHelper;

        setStyle("-fx-background-color: floralwhite;"); // Set background color

        // VBox for the application title
        VBox titleBox = new VBox();
        titleBox.setPadding(new Insets(10));
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setStyle(StartCSE360.blueBackground); // Apply blue background style

        // Main title label
        Label titleLabel = new Label("Welcome to BookedIn");
        titleLabel.setStyle(StartCSE360.h1);
        titleBox.getChildren().add(titleLabel); // Add title to titleBox

        // VBox layout for login form
        VBox formBox = new VBox(10);
        formBox.setAlignment(Pos.CENTER);
        formBox.setPadding(new Insets(20));

        // Label for login prompt
        Label loginLabel = new Label("Login");
        loginLabel.setStyle(StartCSE360.h2); // Style for login label

        // Fields for email, password, and role input
        TextField emailField = new TextField();
        emailField.setPromptText("Enter Email / Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");

        TextField roleField = new TextField();
        roleField.setPromptText("Enter Verification Code");

        // Login button to attempt login
        Button loginButton = new Button("Login");
        loginButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        loginButton.setOnAction(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();
            String role = roleField.getText();

            try {
                // Check credentials for admin or user role
                if (databaseHelper.login(email, password, "admin")) {
                    System.out.println("Login successful. Welcome, " + role + " " + email + "!");
                    showDashboard(); // Show dashboard for admin
                } else if (databaseHelper.login(email, password, "user")) {
                    System.out.println("Login successful. Welcome, " + role + " " + email + "!");
                } else {
                    System.out.println("Invalid user credentials. Try again!");
                }
            } catch (Exception ex) {
                ex.printStackTrace(); // Print any errors to console
            }
        });

        // Register button to navigate to Instructor Registration screen
        Button registerButton = new Button("Register");
        registerButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        registerButton.setOnAction(e -> {
            primaryStage.setScene(new Scene(new InstructorRegistrationScreen(primaryStage, databaseHelper), 400, 300));
            primaryStage.show();
        });

        formBox.getChildren().addAll(loginLabel, emailField, passwordField, roleField, loginButton, registerButton);

        // Set alignment and add all elements to the main layout
        setAlignment(Pos.TOP_CENTER);
        getChildren().addAll(titleBox, formBox);
    }

    // Method to display the dashboard screen
    private void showDashboard() {
        Dashboard dashboardScreen = new Dashboard(primaryStage, databaseHelper);
        primaryStage.setScene(new Scene(dashboardScreen, 500, 400));
        primaryStage.show();
    }

	public static void start(Stage primaryStage2) {
		// TODO Auto-generated method stub
		
	}
}
