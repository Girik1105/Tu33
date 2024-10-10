package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/****
 * The ProfessorSetupScreen class creates a setup interface for a professor.
 * 
 * This screen allows users to input their personal information, including first name, 
 * middle name, last name, preferred name, and email address. The user can submit 
 * the data to proceed to the ProfessorDashboard screen.
****/

public class ProfessorSetupScreen {
	private Scene scene;
    private String preferredName, firstName, middleName, lastName, email;
    
/****
* Constructor initializes the setup screen for professor information input.
* It sets up a grid to gather the professor's details like name and email,
* and provides a submission button to proceed to the next screen.
* 
* @param primaryStage The primary stage for displaying the scene.
* @param username The user name of the logged-in professor (not currently used).
****/
    public ProfessorSetupScreen(Stage primaryStage, String username) {
    	// Create the layout grid for the form
    	GridPane grid = new GridPane();
        grid.setStyle(LoginPage.baseBackground); // Apply base background styling
        grid.setAlignment(Pos.CENTER); // Center the grid elements
        grid.setPadding(new Insets(10, 10, 10, 10)); // Set padding
        grid.setVgap(8); // Vertical gap between grid rows
        grid.setHgap(10); // Horizontal gap between grid columns

        // First name
        Label firstNameLabel = new Label("First Name:");
        TextField firstNameInput = new TextField();
        firstNameLabel.setStyle(LoginPage.h3bold); // Apply bold styling
        grid.add(firstNameLabel, 0, 0); // Add label to grid
        grid.add(firstNameInput, 1, 0); // Add input field to grid

        // Middle name
        Label middleNameLabel = new Label("Middle Name:");
        TextField middleNameInput = new TextField();
        middleNameLabel.setStyle(LoginPage.h3bold);
        grid.add(middleNameLabel, 0, 1);
        grid.add(middleNameInput, 1, 1);

        // Last name
        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameInput = new TextField();
        lastNameLabel.setStyle(LoginPage.h3bold);
        grid.add(lastNameLabel, 0, 2);
        grid.add(lastNameInput, 1, 2);

        // Preferred name
        Label preferredNameLabel = new Label("Preferred Name:");
        TextField preferredNameInput = new TextField();
        preferredNameLabel.setStyle(LoginPage.h3bold);
        grid.add(preferredNameLabel, 0, 3);
        grid.add(preferredNameInput, 1, 3);

        // Email
        Label emailLabel = new Label("Email:");
        TextField emailInput = new TextField();
        emailLabel.setStyle(LoginPage.h3bold);
        grid.add(emailLabel, 0, 4);
        grid.add(emailInput, 1, 4);

        // Define the button action when clicked
        Button submitButton = new Button("Finish Setup");
        submitButton.setOnAction(e -> {
        	firstName = firstNameInput.getText().trim();
            middleName = middleNameInput.getText().trim();
            lastName = lastNameInput.getText().trim();
            preferredName = preferredNameInput.getText().trim();
            email = emailInput.getText().trim();
            
         // Check if required fields are filled, otherwise show an error message
            if(firstName.isEmpty() || firstName == null || lastName.isEmpty() || lastName == null || email.isEmpty() || email == null){
            	Label errorMessage = new Label("Please fill out all empty fields");
            	errorMessage.setStyle(LoginPage.errorText);
            	grid.add(errorMessage, 1, 7);
            } else {  
            // Proceed to ProfessorDashboard if validation is successful
            ProfessorDashboard professorDashboard = new ProfessorDashboard(primaryStage, preferredName, firstName, middleName, lastName, email);
            primaryStage.setScene(professorDashboard.getScene());
            primaryStage.setTitle(firstName + "'s Dashboard");
            
            }
        });
        submitButton.setStyle(LoginPage.buttonStyle);
        grid.add(submitButton, 1, 6);

        scene = new Scene(grid, 400, 300);
    }
    
    /*****
     * This method returns the current scene for the setup screen.
     * 
     * @return The Scene object containing the layout of the professor setup screen.
     ****/

    public Scene getScene() {
        return scene;
    }
}
