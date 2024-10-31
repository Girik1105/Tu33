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

        Label loginLabel = new Label("Login");
        loginLabel.setStyle(StartCSE360.h2); // Style for the login page label

        TextField emailField = new TextField();
        emailField.setPromptText("Enter Email");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");

        TextField roleField = new TextField();
        roleField.setPromptText("Enter Role");

        Button loginButton = new Button("Login");
        loginButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        loginButton.setOnAction(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();
            String role = roleField.getText();

            try {
                if (databaseHelper.login(email, password, role)) {
                    System.out.println("Login successful. Welcome, " + email + "!");
                    showDashboard();
                } else {
                    System.out.println("Invalid user credentials. Try again!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button registerButton = new Button("Register");
        registerButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        registerButton.setOnAction(e -> {
            primaryStage.setScene(new Scene(new AdminRegistrationScreen(primaryStage, databaseHelper), 400, 300));
            primaryStage.show();
        });

        formBox.getChildren().addAll(loginLabel, emailField, passwordField, roleField, loginButton, registerButton);

        // Set alignment and add all elements to the main layout
        setAlignment(Pos.TOP_CENTER);
        getChildren().addAll(titleBox, formBox);
    }

    private void showDashboard() {
        Dashboard dashboardScreen = new Dashboard(primaryStage, databaseHelper);
        primaryStage.setScene(new Scene(dashboardScreen, 500, 400));
        primaryStage.show();
    }
}
