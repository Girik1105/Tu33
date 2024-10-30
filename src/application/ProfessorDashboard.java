package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * <p> ProfessorDashboard class for the Booked In application. </p>
 * 
 * <p> Description: This class manages the layout and functionality of the professor's 
 * dashboard, displaying user information and providing a logout option. It serves 
 * as the main screen for professors after they have successfully logged in and 
 * set up their accounts. </p>
 */
public class ProfessorDashboard {

    /** The scene object representing the layout of the professor's dashboard. */
    private Scene scene;

    /**
     * Constructor for the ProfessorDashboard class.
     * 
     * @param primaryStage  The primary stage for displaying the dashboard.
     * @param preferredName The user's preferred name.
     * @param firstName     The user's first name.
     * @param middleName    The user's middle name.
     * @param lastName      The user's last name.
     * @param email         The user's email address.
     */
    public ProfessorDashboard(Stage primaryStage, String preferredName, String firstName, String middleName, String lastName, String email) {
        // Create a VBox layout with padding.
        VBox layout = new VBox();
        layout.setPadding(new Insets(20)); // Set padding for spacing.

        // VBox for displaying user information.
        VBox userInfo = new VBox(10); // 10px spacing between user details.
        Label firstNameDashboard = new Label("First Name: " + firstName);
        Label middleNameDashboard = new Label("Middle Name: " + middleName);
        Label lastNameDashboard = new Label("Last Name: " + lastName);
        Label emailDashboard = new Label("Email: " + email);
        // Add all user information labels to the userInfo VBox.
        userInfo.getChildren().addAll(firstNameDashboard, middleNameDashboard, lastNameDashboard, emailDashboard);

        // VBox for the logout button.
        VBox logoutArea = new VBox(10); // 10px spacing for the logout button area.
        Button logoutButton = new Button("Logout");
        logoutButton.setStyle(LoginPage.buttonStyle); // Apply styling for the button.
        
        // Set the action for the logout button to return to the login page.
        logoutButton.setOnAction(e -> {
            LoginPage loginPage = new LoginPage(); // Create an instance of the LoginPage.
            loginPage.start(primaryStage); // Switch to the login scene.
        });
        logoutArea.getChildren().add(logoutButton); // Add the logout button to the VBox.

        // Text indicating successful creation of the professor account.
        Text loggedIn = new Text("Professor Account Created!");
        loggedIn.setStyle("-fx-font-size: 24px;"); // Set the font size of the text.
        
        // Add all elements (user info, account creation message, and logout) to the main layout.
        layout.getChildren().addAll(userInfo, loggedIn, logoutArea);

        // Create and set the scene for the professor dashboard with specified dimensions.
        scene = new Scene(layout, 400, 200);
    }

    /**
     * Getter method for the professor dashboard scene.
     * 
     * @return The scene of the professor's dashboard.
     */
    public Scene getScene() {
        return scene; // Return the initialized scene.
    }
}
