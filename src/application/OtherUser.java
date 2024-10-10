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
 * <p> OtherUser class for the Booked In application. </p>
 * 
 * <p> Description: This class handles the login process for users who are logging in 
 * through an invitation. It includes input fields for username, password, and invitation 
 * code, and navigates to different account setup screens (Student or Professor) 
 * based on the provided code. </p>
 * 
 */
public class OtherUser {
    
    /** The scene object representing the layout of the login screen. */
    private Scene scene; 

    /**
     * Constructor for the OtherUser class.
     * 
     * @param primaryStage The primary stage for displaying the login screen.
     */
    public OtherUser(Stage primaryStage) {
        // VBox for application name and description.
        VBox title = new VBox(); // Create area for title and description.
        title.setPadding(new Insets(10)); // Set padding for the title area.
        title.setAlignment(Pos.CENTER); // Align items in VBox to center.
        title.setStyle(LoginPage.blueBackground); // Set background color for the VBox.

        // Create a label with the application title.
        Label label = new Label("Welcome to BookedIn");
        label.setStyle(LoginPage.h1); // Apply the style for header 1.
        
        // Create a text description for the login process.
        Text description = new Text("Log in by Invitation");
        description.setStyle(LoginPage.h2); // Apply the style for header 2.

        // Add the title and description to the VBox.
        title.getChildren().addAll(label, description); 

        // Create a GridPane for the login form.
        GridPane grid = new GridPane(); 
        grid.setAlignment(Pos.CENTER); // Align items in the grid to center.
        grid.setPadding(new Insets(20)); // Set padding around the grid.
        grid.setHgap(10); // Set horizontal gap between columns.
        grid.setVgap(10); // Set vertical gap between rows.

        // Create a label and text field for entering the username.
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameLabel.setStyle(LoginPage.h3bold); // Apply bold styling for the label.
        grid.add(usernameLabel, 0, 0); // Add the username label to the grid.
        grid.add(usernameField, 1, 0); // Add the username text field to the grid.
        
        // Create a label and text field for entering the password.
        Label passwordLabel = new Label("Password:");
        TextField passwordField = new TextField();
        passwordLabel.setStyle(LoginPage.h3bold); // Apply bold styling for the label.
        grid.add(passwordLabel, 0, 1); // Add the password label to the grid.
        grid.add(passwordField, 1, 1); // Add the password text field to the grid.

        // Create a label and text field for entering the invitation code.
        Label codeLabel = new Label("Invitation Code:");
        TextField codeField = new TextField();
        codeLabel.setStyle(LoginPage.h3bold); // Apply bold styling for the label.
        grid.add(codeLabel, 0, 2); // Add the invitation code label to the grid.
        grid.add(codeField, 1, 2); // Add the invitation code text field to the grid.
        
        // Button to submit the invitation code and proceed with login.
        Button invitationSubmit = new Button("Submit");
        invitationSubmit.setStyle(LoginPage.buttonStyle); // Apply the button style.
        invitationSubmit.setOnAction(e -> {
            // Retrieve user input from the username and invitation code fields.
            String enteredUsername = usernameField.getText().trim();
            String enteredCode = codeField.getText().trim();
            
            // Define valid invitation codes for different roles.
            String validStudentCode = "stu123";
            String validProfessorCode = "prof123";
            String validAdminCode = "60458";
            
            // Check the entered invitation code and navigate to the appropriate setup page.
            if (enteredCode.equals(validStudentCode)) {
                System.out.println("Student Login successful!"); // Log the successful student login.

                // Navigate to the student setup screen.
                StudentSetupScreen otherSetupPage = new StudentSetupScreen(primaryStage, enteredUsername);
                primaryStage.setScene(otherSetupPage.getScene()); // Set the scene for student setup.
                primaryStage.setTitle("Account Setup"); // Update the window title.
            } else if (enteredCode.equals(validProfessorCode)) {
                System.out.println("Professor Login successful!"); // Log the successful professor login.

                // Navigate to the professor setup screen.
                ProfessorSetupScreen otherSetupPage = new ProfessorSetupScreen(primaryStage, enteredUsername);
                primaryStage.setScene(otherSetupPage.getScene()); // Set the scene for professor setup.
                primaryStage.setTitle("Account Setup"); // Update the window title.
            } else {
                // Log an error message if the invitation code is invalid.
                System.out.println("Invalid username or invitation code!");    
            }
        });

        // Add the submit button to the grid.
        grid.add(invitationSubmit, 1, 3);

        /*** Organizing panels/grids *********************************************/
        // Create a VBox layout to organize the title and the grid.
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.TOP_CENTER); // Align the layout to the top center.
        layout.setStyle(LoginPage.baseBackground); // Apply the base background style.
        layout.getChildren().addAll(title, grid); // Add title and grid to the layout.

        // Scene setup: create a new scene with the specified dimensions and set it to the stage.
        this.scene = new Scene(layout, 500, 290); 
        primaryStage.setTitle("Login Screen"); // Set the title for the primary stage.
        primaryStage.setScene(this.scene); // Set the scene for the stage.
        primaryStage.show(); // Display the stage.  
    }

    /**
     * Getter method for the login screen scene.
     * 
     * @return The scene of the login screen.
     */
    public Scene getScene() {
        return this.scene; // Return the initialized scene.
    }
}
