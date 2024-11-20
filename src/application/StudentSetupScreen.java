package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/*****
 * The StudentSetupScreen class creates the setup interface for students to enter their personal information.
 * 
 * The screen gathers the student's first name, middle name, last name, preferred name, and email address. 
 * Once the setup is complete, it redirects the user to their student dashboard.
 ****/

public class StudentSetupScreen {
	private Scene scene;
    private String preferredName, firstName, middleName, lastName, email;

    
    /******
     * Constructor for StudentSetupScreen.
     * 
     * It builds the user interface for student information setup and handles form submission.
     * 
     * @param primaryStage The primary stage where the scene is displayed.
     * @param username The username of the student (not currently used in the setup screen).
     ****/
    public StudentSetupScreen(Stage primaryStage, String username) {
    	GridPane grid = new GridPane();
        grid.setStyle(StartCSE360.baseBackground); // Apply base background style
        grid.setAlignment(Pos.CENTER);  // Align the grid's elements to the center
        grid.setPadding(new Insets(10, 10, 10, 10)); // Set padding for the grid
        grid.setVgap(8); // Vertical gap between grid rows
        grid.setHgap(10); // Horizontal gap between grid columns

        // First name
        Label firstNameLabel = new Label("First Name:");
        TextField firstNameInput = new TextField();
        firstNameLabel.setStyle(StartCSE360.h3bold); // Apply bold style to label
        grid.add(firstNameLabel, 0, 0); // Add label to grid
        grid.add(firstNameInput, 1, 0); // Add input field to grid

        // Middle name
        Label middleNameLabel = new Label("Middle Name:");
        TextField middleNameInput = new TextField();
        middleNameLabel.setStyle(StartCSE360.h3bold); // Apply bold style to label
        grid.add(middleNameLabel, 0, 1);
        grid.add(middleNameInput, 1, 1);

        // Last name
        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameInput = new TextField();
        lastNameLabel.setStyle(StartCSE360.h3bold);
        grid.add(lastNameLabel, 0, 2);
        grid.add(lastNameInput, 1, 2);

        // Preferred name
        Label preferredNameLabel = new Label("Preferred Name:");
        TextField preferredNameInput = new TextField();
        preferredNameLabel.setStyle(StartCSE360.h3bold);
        grid.add(preferredNameLabel, 0, 3);
        grid.add(preferredNameInput, 1, 3);

        // Email
        Label emailLabel = new Label("Email:");
        TextField emailInput = new TextField();
        emailLabel.setStyle(StartCSE360.h3bold);
        grid.add(emailLabel, 0, 4);
        grid.add(emailInput, 1, 4);

        // Submit Button
        Button submitButton = new Button("Finish Setup");
        
        /****
         * Handles form submission when the "Finish Setup" button is clicked.
         * 
         * It checks whether all the fields are filled in, and if so, it creates a new StudentDashboard.
         * Otherwise, it displays an error message asking the user to complete the missing fields.
         ****/
        
        submitButton.setOnAction(e -> {
        	// Retrieve user input from form
        	firstName = firstNameInput.getText().trim();
            middleName = middleNameInput.getText().trim();
            lastName = lastNameInput.getText().trim();
            preferredName = preferredNameInput.getText().trim();
            email = emailInput.getText().trim();
            
            // Check if required fields are filled
            if(firstName.isEmpty() || firstName == null || lastName.isEmpty() || lastName == null || email.isEmpty() || email == null){
            	Label errorMessage = new Label("Please fill out all empty fields");  // Apply error text style
            	errorMessage.setStyle(StartCSE360.errorText); // Display error message in grid
            	grid.add(errorMessage, 1, 7);
            } else {  
            	// Redirect to student dashboard if all fields are filled
            StudentDashboard studentDashboard = new StudentDashboard(primaryStage, preferredName, firstName, middleName, lastName, email);
            primaryStage.setScene(studentDashboard.getScene());
            primaryStage.setTitle(firstName + "'s Dashboard");
            
            }
        });
        submitButton.setStyle(StartCSE360.buttonStyle); // Apply button style
        grid.add(submitButton, 1, 6); // Add button to grid
        
        // Create and set up the scene
        scene = new Scene(grid, 400, 300);
    }
    
    /******
     * This method returns the scene that represents the student setup screen.
     * 
     * @return The Scene object containing the layout of the student setup screen.
     *****/
    public Scene getScene() {
        return scene;
    }
}
