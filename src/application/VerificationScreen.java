package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VerificationScreen extends VBox {
    private DatabaseHelper databaseHelper;
    private Stage primaryStage;

    // Constructor initializes the instructor registration screen UI
    public VerificationScreen(Stage stage, DatabaseHelper databaseHelper) {
        this.primaryStage = stage; // Save stage reference
        this.databaseHelper = databaseHelper; // Save DatabaseHelper reference

        setStyle("-fx-background-color: floralwhite;"); // Set background color
        
        VBox title = new VBox(); // Container for title section
        title.setPadding(new Insets(10)); // Set padding for title section
        title.setAlignment(Pos.CENTER); // Center-align title
        title.setStyle(StartCSE360.blueBackground); // Apply blue background style

        // Create the main title label
        Label titleLabel = new Label("Verification Code"); // Application title
        titleLabel.setStyle(StartCSE360.h1); // Apply title text styling

        // Role input field
        TextField verificationCodeField = new TextField();
        verificationCodeField.setPromptText("Enter verification code");

        Button verificationButton = new Button("Verify");
        verificationButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        verificationButton.setOnAction(e -> {
            String verificationCode = verificationCodeField.getText(); // Get user input
            if ("instructor".equalsIgnoreCase(verificationCode)) { // Case-insensitive check
                primaryStage.setScene(new Scene(new InstructorRegistrationScreen(primaryStage, databaseHelper), 500, 400));
            } else if ("student".equalsIgnoreCase(verificationCode)) { // Case-insensitive check
                primaryStage.setScene(new Scene(new StudentRegistrationScreen(primaryStage, databaseHelper), 500, 400));
            } else if ("admin".equalsIgnoreCase(verificationCode)) { // Case-insensitive check
                primaryStage.setScene(new Scene(new AdminRegistrationScreen(primaryStage, databaseHelper), 500, 400));            	
            }
        });

        Button backButton = new Button("Back");
        backButton.setStyle(StartCSE360.blueBackground + StartCSE360.h3bold);
        backButton.setOnAction(e -> stage.setScene(new Scene(new LoginScreen(stage, databaseHelper), 400, 300)));

        setAlignment(Pos.CENTER); // Center-align content
        getChildren().addAll(titleLabel, verificationCodeField, verificationButton, backButton);
        setSpacing(10); // Set spacing between components
    }
}
