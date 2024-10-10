package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        LoginPage loginPage = new LoginPage(); // Login page class
        loginPage.start(primaryStage); // Launch LoginPage
    }

    public static void main(String[] args) {
        launch(args);
    }
}