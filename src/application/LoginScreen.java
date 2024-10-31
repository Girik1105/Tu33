package application;

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

        Label titleLabel = new Label("Login");

        TextField emailField = new TextField();
        emailField.setPromptText("Enter Email");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");
        TextField roleField = new TextField();
        roleField.setPromptText("Enter Role");

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            String email = emailField.getText(); 
            String password = passwordField.getText();
            String role = roleField.getText();

            try {
                if (databaseHelper.login(email, password, role)) {
                    System.out.println("Login successful. Welcome, " + role + "!");
                    showDashboard();
                } else {
                    System.out.println("Invalid user credentials. Try again!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Register button to open UserRegistrationScreen
        Button registerButton = new Button("Register");
        registerButton.setOnAction(e -> {
            //primaryStage.setScene(new Scene(new UserRegistrationScreen(primaryStage, databaseHelper), 400, 300));
            primaryStage.show();
        });

        setAlignment(Pos.CENTER);
        getChildren().addAll(titleLabel, emailField, passwordField, roleField, loginButton, registerButton);
        setSpacing(10);
    }

    private void showDashboard() {
        Dashboard dashboardScreen = new Dashboard(primaryStage, databaseHelper);
        primaryStage.setScene(new Scene(dashboardScreen, 500, 400));
        primaryStage.show();
    }
}
