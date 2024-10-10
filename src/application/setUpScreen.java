package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * The setUpScreen class is responsible for displaying the user input screen
 * where users enter their first name, middle name, last name, preferred name, and email.
 * It validates the form and either proceeds to the login verification screen or displays an error message.
 */
public class setUpScreen {

    // Scene object that represents this screen
    private Scene scene;

    // Variables to store user input from the text fields
    private String firstName, middleName, lastName, email, preferredName;

    /**
     * Constructor for setUpScreen.
     * Sets up the user input fields and handles form submission.
     *
     * @param primaryStage The main window (stage) of the application
     * @param username The username passed from the login screen
     * @param password The password passed from the login screen
     */
    public setUpScreen(Stage primaryStage, String username, String password) {
    	
        // Create a GridPane layout for arranging input fields in a grid-like structure
        GridPane grid = new GridPane();
        grid.setStyle(LoginPage.baseBackground); // Set background style
        grid.setAlignment(Pos.CENTER);           // Center the grid in the window
        grid.setPadding(new Insets(10, 10, 10, 10));  // Padding around the grid
        grid.setVgap(8);   // Vertical gap between rows
        grid.setHgap(10);  // Horizontal gap between columns

        // First Name Label and Input Field
        Label firstNameLabel = new Label("First Name:");
        firstNameLabel.setStyle(LoginPage.h3bold);  // Apply custom styling for label
        TextField firstNameInput = new TextField(); // Text field for input
        grid.add(firstNameLabel, 0, 0); // Add label to grid (column 0, row 0)
        grid.add(firstNameInput, 1, 0); // Add text field to grid (column 1, row 0)

        // Middle Name Label and Input Field
        Label middleNameLabel = new Label("Middle Name:");
        middleNameLabel.setStyle(LoginPage.h3bold); // Apply custom styling for label
        TextField middleNameInput = new TextField(); // Text field for input
        grid.add(middleNameLabel, 0, 1); // Add label to grid (column 0, row 1)
        grid.add(middleNameInput, 1, 1); // Add text field to grid (column 1, row 1)

        // Last Name Label and Input Field
        Label lastNameLabel = new Label("Last Name:");
        lastNameLabel.setStyle(LoginPage.h3bold); // Apply custom styling for label
        TextField lastNameInput = new TextField(); // Text field for input
        grid.add(lastNameLabel, 0, 2); // Add label to grid (column 0, row 2)
        grid.add(lastNameInput, 1, 2); // Add text field to grid (column 1, row 2)

        // Preferred Name Label and Input Field
        Label preferredNameLabel = new Label("Preferred Name:");
        preferredNameLabel.setStyle(LoginPage.h3bold); // Apply custom styling for label
        TextField preferredNameInput = new TextField(); // Text field for input
        preferredNameInput.setPromptText("Optional");
        grid.add(preferredNameLabel, 0, 3); // Add label to grid (column 0, row 3)
        grid.add(preferredNameInput, 1, 3); // Add text field to grid (column 1, row 3)

        // Email Label and Input Field
        Label emailLabel = new Label("Email:");
        emailLabel.setStyle(LoginPage.h3bold); // Apply custom styling for label
        TextField emailInput = new TextField(); // Text field for input
        grid.add(emailLabel, 0, 4); // Add label to grid (column 0, row 4)
        grid.add(emailInput, 1, 4); // Add text field to grid (column 1, row 4)

        // Submit Button
        Button submitButton = new Button("Finish Setup"); // Button to submit the form
        submitButton.setStyle(LoginPage.buttonStyle); // Apply custom styling for button
        
        // Handle button click (submit the form)
        submitButton.setOnAction(e -> {
            firstName = firstNameInput.getText().trim(); // Get first name input
            String middleName = middleNameInput.getText().trim(); // Get middle name input
            String lastName = lastNameInput.getText().trim(); // Get last name input
            String preferredName = preferredNameInput.getText().trim(); // Get preferred name
            String email = emailInput.getText().trim(); // Get email input

            // Validate if any fields are empty, show error if true
            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
                Label errorMessage = new Label("Please fill out all empty fields"); // Error message
                errorMessage.setStyle(LoginPage.errorText); // Apply error text style
                grid.add(errorMessage, 1, 7); // Add error message to grid
            } else {
                // Proceed to the LoginVerification screen if all fields are filled
                LoginVerification loginVerification = new LoginVerification(primaryStage, username, password, preferredName, firstName, middleName, lastName, email);
                primaryStage.setScene(loginVerification.getScene());  // Set the next scene
                primaryStage.setTitle("Verify Login"); // Set the window title for verification
            }
        });
        grid.add(submitButton, 1, 6);  // Add the submit button to the grid (column 1, row 6)

        // Create the scene and set the size
        scene = new Scene(grid, 400, 300);
    }

    /**
     * Getter method to retrieve the Scene object.
     *
     * @return The Scene object for this screen
     */
    public Scene getScene() {
        return scene;
    }
}