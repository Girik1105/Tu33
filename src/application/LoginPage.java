package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * The LoginPage class handles the login screen of the application,
 * where users can log in or proceed with an invitation code.
 * The class validates user credentials and transitions to the next screen.
 */
public class LoginPage extends Application {
    
    // Styling properties for the UI elements
    public static String blueBackground = "-fx-background-color: lightblue;"; // Blue background for title section
    public static String baseBackground = "-fx-background-color: floralwhite;"; // Default background for the rest of the layout
    public static String h1 = "-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #4b5d75;";  // Large title text
    public static String h2 = "-fx-font-size: 16px;"; // Smaller subtitle text
    public static String h3bold = "-fx-font-size: 14px; -fx-font-weight: bold"; // Label text
    public static String errorText = "-fx-text-fill: red"; // Error message style (red text)
    public static String buttonStyle = "-fx-background-color: lightblue; -fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #4b5d75;"; // Button styling

    @Override
    public void start(Stage primaryStage) {
        // VBox for the application title and description
        VBox title = new VBox(); // Create a VBox to hold the title and description
        title.setPadding(new Insets(10)); // Set padding for the title section
        title.setAlignment(Pos.CENTER); // Center the title within the window
        title.setStyle(blueBackground); // Apply blue background style
        
        // Create the main title label
        Label label = new Label("Welcome to BookedIn"); // Set the application title
        label.setStyle(h1); // Apply title text styling
        
        // Create the subtitle description
        Text description = new Text("Start by logging in!"); // Subtitle text description
        description.setStyle(h2); // Apply smaller font styling
        
        // Add title and description to the VBox
        title.getChildren().addAll(label, description);
        
        // Create a GridPane for organizing the login form
        GridPane grid = new GridPane(); // Create grid layout for username/password input
        grid.setAlignment(Pos.CENTER); // Center the grid
        grid.setPadding(new Insets(20)); // Padding around the grid
        grid.setVgap(10); // Set vertical gap between rows
        grid.setHgap(10); // Set horizontal gap between columns

        // Username Label and Input Field
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField(); // Text field for entering username
        usernameLabel.setStyle(h3bold); // Apply bold styling for the username label
        grid.add(usernameLabel, 0, 0); // Add username label to the grid (column 0, row 0)
        grid.add(usernameField, 1, 0); // Add username text field (column 1, row 0)

        // Password Label and Input Field
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();  // Password field for entering password
        passwordLabel.setStyle(h3bold); // Apply bold styling for the password label
        grid.add(passwordLabel, 0, 1); // Add password label to the grid (column 0, row 1)
        grid.add(passwordField, 1, 1); // Add password field (column 1, row 1)

        // Invitation Code Button
        Button invitationSubmit = new Button("Enter Invitation Code");
        invitationSubmit.setStyle(buttonStyle); // Apply button style
        grid.add(invitationSubmit, 1, 4); // Add invitation submit button (column 1, row 4)
        // Event handler for when the invitation button is clicked
        invitationSubmit.setOnAction(e -> {
            OtherUser otherUser = new OtherUser(primaryStage); // Create OtherUser screen
            primaryStage.setScene(otherUser.getScene()); // Set the scene to OtherUser
            primaryStage.setTitle("Log in with Code"); // Update the window title
        });

        // Login Button to proceed with account creation
        Button loginButton = new Button("Create Account");
        loginButton.setStyle(buttonStyle); // Apply button styling
        grid.add(loginButton, 1, 3); // Add login button (column 1, row 3)
        // Event handler for when the login button is clicked
        loginButton.setOnAction(e -> {
            String username = usernameField.getText(); // Get the entered username
            String password = passwordField.getText(); // Get the entered password
            
            if (validateCredentials(username, password)) {
                System.out.println("Login Successful!");
                setUpScreen setUpScreen = new setUpScreen(primaryStage, username, password);  // Proceed to account setup screen
                primaryStage.setScene(setUpScreen.getScene()); // Set the scene to the setup screen
                primaryStage.setTitle("Account Setup"); // Update the window title
            } else {
                System.out.println("Invalid credentials!");
                Label errorMessage = new Label("Username/Password or Code Incorrect");  // Display error message
                errorMessage.setStyle(errorText); // Apply red styling to error message
                grid.add(errorMessage, 1, 5); // Add error message to the grid (column 1, row 5)
            }
        });

        // VBox layout to hold the title and the login form
        VBox layout = new VBox(20); // Create VBox for organizing title and grid
        layout.setAlignment(Pos.TOP_CENTER); // Align the VBox to the top center
        layout.setStyle(baseBackground); // Apply base background style to the layout
        layout.getChildren().addAll(title, grid); // Add title and grid to the VBox

        // Create the scene and set up the stage
        Scene scene = new Scene(layout, 500, 290); // Define the scene size
        primaryStage.setTitle("Login Screen"); // Set window title
        primaryStage.setScene(scene); // Set the scene for the primary stage
        primaryStage.show(); // Display the window
    }
    
    /**
     * Validates the login credentials entered by the user.
     *
     * @param username The entered username
     * @param password The entered password
     * @return true if credentials are valid, false otherwise
     */
    private boolean validateCredentials(String username, String password) {
        return username.equals("admin") && password.equals("pass");  // Simple validation for admin account
    }

    /**
     * Main method to launch the application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        launch(args);  // Launch the JavaFX application
    }
}
