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

public class OtherUser {
	
	private Scene scene; // Store the scene as an instance variable

    public OtherUser(Stage primaryStage) {
    	// VBox for application name and description
    	VBox title = new VBox(); // create area for title and description	
    	title.setPadding(new Insets(10)); // set padding to area where title and description will go
    	title.setAlignment(Pos.CENTER); // align items in VBox to center
    	title.setStyle(LoginPage.blueBackground); // set background color to VBox
    	
    	Label label = new Label("Welcome to BookedIn"); // create label with title
    	label.setStyle(LoginPage.h1);    	
    	Text description = new Text("Log in by Invitation"); // create text with description
    	description.setStyle(LoginPage.h2);
    	
    	title.getChildren().addAll(label, description); // add application title and description to VBox
    	
    	GridPane grid = new GridPane(); // create grid area 
    	grid.setAlignment(Pos.CENTER); // align items in grid to center
    	grid.setPadding(new Insets(20)); // set padding
    	grid.setHgap(10); // horizontal gap between columns
    	grid.setVgap(10); // vertical gap between rows

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameLabel.setStyle(LoginPage.h3bold);
        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        
        Label passwordLabel = new Label("Password:");
        TextField passwordField = new TextField();
        passwordLabel.setStyle(LoginPage.h3bold);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);

        Label codeLabel = new Label("Invitation Code:");
        TextField codeField = new TextField();
        codeLabel.setStyle(LoginPage.h3bold);
        grid.add(codeLabel, 0, 2);
        grid.add(codeField, 1, 2);
        
        // Button to submit the invitation code (optional)
        Button invitationSubmit = new Button("Submit");
        invitationSubmit.setStyle(LoginPage.buttonStyle);
        invitationSubmit.setOnAction(e -> {
        	String enteredUsername = usernameField.getText().trim();
            String enteredCode = codeField.getText().trim();
            String validStudentCode = "stu123";
            String validProfessorCode = "prof123";
            String validAdminCode = "60458";
            
            if (enteredCode.equals(validStudentCode)) {
                System.out.println("Student Login successful!");

                StudentSetupScreen otherSetupPage = new StudentSetupScreen(primaryStage, enteredUsername);
                primaryStage.setScene(otherSetupPage.getScene());
                primaryStage.setTitle("Account Setup");
            } else if (enteredCode.equals(validProfessorCode)) {
            	System.out.println("Professor Login successful!");

                ProfessorSetupScreen otherSetupPage = new ProfessorSetupScreen(primaryStage, enteredUsername);
                primaryStage.setScene(otherSetupPage.getScene());
                primaryStage.setTitle("Account Setup");
            } else {
            	System.out.println("Invalid username or invitation code!");	
            }
        });
      
        grid.add(invitationSubmit, 1, 3); // Add the button to the grid

        /*** Organizing panels/grids ***********************************/
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle(LoginPage.baseBackground);
        layout.getChildren().addAll(title, grid);

        // Scene setup
        this.scene = new Scene(layout, 500, 290); // Store the scene
        primaryStage.setTitle("Login Screen");
        primaryStage.setScene(this.scene); // Set the scene for the stage
        primaryStage.show();	
    }

	public Scene getScene() {
		return this.scene; // Return the initialized scene
	}
}
