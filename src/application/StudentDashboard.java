package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*****
 * The StudentDashboard class creates the dashboard interface for students after account setup.
 * 
 * It displays the student's personal information such as first name, middle name, last name, and email.
 * The screen also features a logout button to return to the LoginPage.
 ******/

public class StudentDashboard {
	private Scene scene;
	
	/*****
     * Constructor sets up the student dashboard.
     * It displays the student's basic information and provides an option to log out and return to the login page.
     * 
     * @param primaryStage The primary stage where the scene is displayed.
     * @param preferredName The student's preferred name (not currently used in the dashboard).
     * @param firstName The student's first name.
     * @param middleName The student's middle name.
     * @param lastName The student's last name.
     * @param email The student's email address.
     *****/

    public StudentDashboard(Stage primaryStage, String preferredName, String firstName, String middleName, String lastName, String email) {
    	// Create a vertical layout for the dashboard with 20px padding
    	VBox layout = new VBox();
    	layout.setPadding(new Insets(20));
    	
    	 // VBox for displaying user information.
        VBox userInfo = new VBox(10);
        Label firstNameDashboard = new Label("First Name: " + firstName);
        firstNameDashboard.setStyle(LoginPage.h2);
        Label middleNameDashboard = new Label("Middle Name: " + middleName);
        middleNameDashboard.setStyle(LoginPage.h2);
        Label lastNameDashboard = new Label("Last Name: " + lastName);
        lastNameDashboard.setStyle(LoginPage.h2);
        Label emailDashboard = new Label("Email: " + email);
        emailDashboard.setStyle(LoginPage.h2);
        userInfo.getChildren().addAll(firstNameDashboard, middleNameDashboard, lastNameDashboard, emailDashboard);
        
        // Create the logout button and action
        VBox logoutArea = new VBox(10);
        Button logoutButton = new Button("Logout");
        logoutButton.setStyle(LoginPage.buttonStyle);
        
        logoutButton.setOnAction(e -> {
        	LoginPage loginPage = new LoginPage(); // Login page class
            loginPage.start(primaryStage); // Launch LoginPage
    
         });
         logoutArea.getChildren().add(logoutButton);
         
         // Text to indicate successful login
         Text loggedIn = new Text("Student Account Created!");
         loggedIn.setStyle("-fx-font-size: 24px;");
        
         layout.getChildren().addAll(userInfo, loggedIn, logoutArea);
         layout.setStyle(LoginPage.baseBackground);
        
         scene = new Scene(layout, 400, 200);
    	}
    /*******************************************************************************************************
     * This method returns the scene that represents the student dashboard.
     * 
     * @return The Scene object containing the layout of the student dashboard.
     *******************************************************************************************************/

    public Scene getScene() {
        return scene;
    }
}
