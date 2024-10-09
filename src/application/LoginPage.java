package application;
	
import java.sql.SQLException;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class LoginPage extends Application {
    @Override
    public void start(Stage primaryStage) {
    	/*** Title Page Area *********************************************/
    	
    	// VBox for application name and description
    	VBox title = new VBox(); // create area for title and description	
    	title.setPadding(new Insets(10)); // set padding to area where title and description will go
    	title.setAlignment(Pos.CENTER); // align items in VBox to center
    	title.setStyle("-fx-background-color: lightblue;"); // set background color to VBox
	
    	Label label = new Label("Welcome to BookedIn"); // create label with title
    	label.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #4b5d75;");    	
    	Text description = new Text("Start by logging in!"); // create text with description
    	description.setStyle("-fx-font-size: 16px;");
    	
    	title.getChildren().addAll(label, description); // add application title and description to VBox
    	/****************************************************************/
        
    	/*** User Input Area ********************************************/
    	
        // GridPane layout for username, password, and login button
        GridPane grid = new GridPane(10, 10); // create grid area 
        grid.setAlignment(Pos.CENTER); // align items in grid to center
        grid.setPadding(new Insets(20)); // set padding

        // Labels and input fields
        Label usernameLabel = new Label("Username:"); // label for username
        usernameLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        grid.add(usernameLabel, 0, 0); // add username label to grid
        TextField usernameTextfield = new TextField(); // create text field for username
        grid.add(usernameTextfield, 1, 0); // place username text field on grid

        Label passwordLabel = new Label("Password:"); // label for password
        passwordLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        grid.add(passwordLabel, 0, 1); // add password label to grid
        PasswordField passwordField = new PasswordField(); // create password field for password
        grid.add(passwordField, 1, 1); // add password field to grid
        
        Label passwordLabel2 = new Label("Password Again:"); // label for password
        passwordLabel2.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        grid.add(passwordLabel2, 0, 2); // add password label to grid
        PasswordField passwordField2 = new PasswordField(); // create password field for password
        grid.add(passwordField2, 1, 2); // add password field to grid
        
        // Error message for passwords
        Label passwordsDontMatch = new Label();
        passwordsDontMatch.setStyle("-fx-text-fill: red;");
        grid.add(passwordsDontMatch, 1, 3); // Place the error message under the button
        /***************************************************************/

        /*** Log In Button *********************************************/   
        Button loginBtn = new Button("Login"); // create button to log in
        loginBtn.setStyle("-fx-background-color: lightblue; -fx-font-weight: bold;");
        grid.add(loginBtn, 1, 4); // add button to grid

        loginBtn.setOnAction(e -> {
            String password1 = passwordField.getText(); // get text from password textfield1
            String password2 = passwordField2.getText(); // get password from password textfield2
            String username = usernameTextfield.getText(); // get username

            try {
                DatabaseHelper db = new DatabaseHelper();
                db.connectToDatabase();
                
                if (db.isDatabaseEmpty()) { // First-time setup
                    if (password1.isEmpty() || password2.isEmpty()) {
                        passwordsDontMatch.setText("Password Fields Cannot be Empty!!");
                    } else if (!password1.equals(password2)) {
                        passwordsDontMatch.setText("Passwords do not match!");
                    } else {
                        db.register(username, password1);
                        setUpScreen setUpScreen = new setUpScreen(); // redirect to account setup
                        setUpScreen.start(primaryStage);
                    }
                } else {
                    // Handle regular login logic for existing users
                }
            } catch (SQLException ex) {
            	passwordsDontMatch.setText("Database error: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
        /***************************************************************/
        
        /*** Organizing panels/grids ***********************************/
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: floralwhite;");
        layout.getChildren().addAll(title, grid);
        

        // Scene setup
        Scene scene = new Scene(layout, 400, 290);
        primaryStage.setTitle("Login Screen");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    public static void main(String[] args) {
    	launch(args);
    }
}
