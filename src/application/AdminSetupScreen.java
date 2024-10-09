//package application;
//
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//public class AdminSetupScreen {
//    private Stage stage;
//    private VBox layout;
//
//    public AdminSetupScreen(Stage stage) {
//        this.stage = stage;
//        layout = new VBox(10);
//
//        Label userLabel = new Label("Admin Username:");
//        TextField usernameField = new TextField();
//
//        Label passLabel = new Label("Admin Password:");
//        PasswordField passwordField = new PasswordField();
//
//        Label confirmPassLabel = new Label("Confirm Password:");
//        PasswordField confirmPasswordField = new PasswordField();
//
//        Button setupButton = new Button("Set up Admin");
//
//        // Event handler to switch to InvitationCodeScreen after setup
//        setupButton.setOnAction(e -> {
//            InvitationCodeScreen invitationScreen = new InvitationCodeScreen(stage);
//            Scene inviteScene = new Scene(invitationScreen.getLayout(), 400, 300);
//            stage.setScene(inviteScene);
//        });
//
//        layout.getChildren().addAll(userLabel, usernameField, passLabel, passwordField, confirmPassLabel, confirmPasswordField, setupButton);
//    }
//
//    public VBox getLayout() {
//        return layout;
//    }
//}
