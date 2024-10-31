package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartCSE360 extends Application {
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
        databaseHelper = new DatabaseHelper();
        databaseHelper.connectToDatabase();

        if (!databaseHelper.isAdminSetupComplete()) {
            primaryStage.setScene(new Scene(new AdminRegistrationScreen(primaryStage, databaseHelper), 400, 300));
            primaryStage.show();
        } else {
            showLoginScreen(primaryStage, databaseHelper);
        }
    }

    public void showLoginScreen(Stage stage, DatabaseHelper databaseHelper) throws Exception {
        stage.setScene(new Scene(new LoginScreen(stage, databaseHelper), 400, 300));
        stage.show();
    }

    void showDashboard(Stage stage) {
        try {
            Dashboard dashboardScreen = new Dashboard(stage, databaseHelper);
            stage.setScene(new Scene(dashboardScreen, 500, 400));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
