package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * <p> Dashboard class for the Booked In application. </p>
 * 
 * <p> Description: This class manages the layout and functionality of the admin 
 * dashboard, including user invitations, role management, user information display, 
 * and other admin-related actions like resetting passwords and deleting user accounts. 
 * The class utilizes JavaFX to create the user interface. </p>
 * 
 */
public class Dashboard {
    
    /** The scene object representing the layout of the dashboard. */
    private Scene scene;

    /**
     * Constructor for the Dashboard class.
     * 
     * @param primaryStage  The primary stage for displaying the dashboard.
     * @param preferredName The user's preferred name.
     * @param firstName     The user's first name.
     * @param middleName    The user's middle name.
     * @param lastName      The user's last name.
     * @param email         The user's email address.
     */
    public Dashboard(Stage primaryStage, String preferredName, String firstName, String middleName, String lastName, String email) {
        // Create a VBox layout with 20px spacing and padding.
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        
        // Text area for displaying information.
        Text textarea = new Text();
        
        // VBox for displaying the welcome title.
        VBox title = new VBox(10);
        title.setStyle(LoginPage.blueBackground);
        
        // Determine the display name using the preferred name if provided.
        String displayFirstName;
        if (preferredName != null && !preferredName.trim().isEmpty()) {
            displayFirstName = preferredName;
        } else {
            displayFirstName = firstName;
        }
        
        // Create a welcome label and set its style.
        Label welcomeLabel = new Label("Welcome, " + displayFirstName + "!");
        welcomeLabel.setStyle(LoginPage.h1);
        title.getChildren().add(welcomeLabel);
        
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
        
        // FlowPane for admin actions (buttons).
        FlowPane adminAction = new FlowPane();
        adminAction.setPadding(new Insets(10, 10, 10, 10));
        adminAction.setAlignment(Pos.CENTER);
        adminAction.setHgap(10);
        adminAction.setVgap(10);
        
        /*** Invite a User *****************************************************/
        // Button to invite a user.
        Button inviteButton = new Button("Invite A User");
        inviteButton.setStyle(LoginPage.buttonStyle2);
        inviteButton.setOnAction(e -> {
            // Create an alert for role selection.
            Alert roleAlert = new Alert(AlertType.INFORMATION);
            roleAlert.setTitle("Invite User");
            roleAlert.setHeaderText("Select a Role for the Invitation");

            // Radio buttons for role selection.
            RadioButton adminRole = new RadioButton("Admin");
            RadioButton studentRole = new RadioButton("Student");
            RadioButton professorRole = new RadioButton("Professor");

            // Group the radio buttons.
            ToggleGroup roleGroup = new ToggleGroup();
            adminRole.setToggleGroup(roleGroup);
            studentRole.setToggleGroup(roleGroup);
            professorRole.setToggleGroup(roleGroup);

            // Layout for role selection.
            HBox roleSelectionLayout = new HBox(10);
            roleSelectionLayout.getChildren().addAll(adminRole, studentRole, professorRole);

            // Add the role selection layout to the alert.
            roleAlert.getDialogPane().setContent(roleSelectionLayout);

            // Show the popup and handle user selection.
            roleAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Generate invitation codes for different roles.
                    String adminCode = "60458";
                    String studentCode = "stu123";
                    String professorCode = "prof123";
                    
                    // Determine the selected role and display the code.
                    String selectedRole = "";
                    if (adminRole.isSelected()) {
                        selectedRole = "Admin";
                        textarea.setText("Invitation Code: " + adminCode + "\nRole: " + selectedRole);
                    } else if (studentRole.isSelected()) {
                        selectedRole = "Student";
                        textarea.setText("Invitation Code: " + studentCode + "\nRole: " + selectedRole);
                    } else if (professorRole.isSelected()) {
                        selectedRole = "Professor";
                        textarea.setText("Invitation Code: " + professorCode + "\nRole: " + selectedRole);
                    }
                }
            });
        });
        /*************************************************************************/
        
        /*** Reset a User's Account **********************************************/
        // Button to reset a user's account.
        Button resetButton = new Button("Reset A User's Account");
        resetButton.setStyle(LoginPage.buttonStyle2);
        resetButton.setOnAction(e -> {
            // Generate a one-time password and expiration time
            String oneTimePassword = "tempPass123";
            String expirationTime = "10/30/24 13:50";
            
            // Display the one-time password and its expiration time
            textarea.setText("One-time Password: " + oneTimePassword + "\nExpiration: " + expirationTime);
        });
        /*************************************************************************/
        
        /*** Delete a User's Account *********************************************/
        // Button to delete a user's account.
        Button deleteButton = new Button("Delete a User's Account");
        deleteButton.setStyle(LoginPage.buttonStyle2);
        deleteButton.setOnAction(e -> {
            // Create an alert to confirm the deletion
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText("Are you sure?");
            alert.setContentText("Type 'Yes' to confirm.");
            
            // input field where user types 'Yes'
            TextField inputField = new TextField();
            inputField.setPromptText("Type 'Yes' to delete user");
            
            // add input field to HBox
            HBox content = new HBox(10);
            content.getChildren().add(inputField);
            
            alert.getDialogPane().setContent(content);

            // Show alert and enter text to delete user
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK && inputField.getText().equals("Yes")) {
                    textarea.setText("User deleted successfully.");
                } else {
                    textarea.setText("User was not deleted");
                }
            });
        });
        /*************************************************************************/
        
        /*** List All Users ******************************************************/
        // Button to list all users.
        Button listButton = new Button("List All Users");
        listButton.setStyle(LoginPage.buttonStyle2);
        listButton.setOnAction(e -> {
            // Placeholder user list.
            String usersList = "User1: Nghi\nUser2: Girik\nUser3: Becca";
            textarea.setText("Users:\n" + usersList);
        });
        /*************************************************************************/
        
        /*** Add or Remove a User's Role *****************************************/
        // Button to add or remove a user's role.
        Button addRole = new Button("Add a Role to User");
        addRole.setStyle(LoginPage.buttonStyle2);
        addRole.setOnAction(e -> {
            // User and action to be done
            String selectedUser = "Becca";
            String roleAction = "Added";
            
            // Display the action.
            textarea.setText(roleAction + " role to " + selectedUser);
        });
        
        Button removeRole = new Button("Remove a Role from User");
        removeRole.setStyle(LoginPage.buttonStyle2);
        removeRole.setOnAction(e -> {
        	// User and action to be done
        	String selectedUser = "Becca";
        	String roleAction = "Removed";
        	
        	// Display the action.
        	textarea.setText(roleAction + " role from " + selectedUser);
        });
        adminAction.getChildren().addAll(inviteButton, resetButton, deleteButton, listButton, addRole, removeRole);
        
        /*** Logout Functionality ************************************************/
        // VBox for logout area.
        VBox logoutArea = new VBox(10);
        Button logoutButton = new Button("Logout");
        logoutButton.setStyle(LoginPage.buttonStyle);
        
        // Set the action for the logout button.
        logoutButton.setOnAction(e -> {
            LoginPage loginPage = new LoginPage(); // Login page class.
            loginPage.start(primaryStage); // Return to the login page.
        });
        logoutArea.getChildren().add(logoutButton);
        logoutArea.setAlignment(Pos.CENTER);
        
        // Add all components to the layout.
        layout.getChildren().addAll(title, userInfo, adminAction, textarea, logoutArea);
        layout.setStyle(LoginPage.baseBackground);
        
        // Create and set the scene.
        scene = new Scene(layout, 400, 450);
    }

    /**
     * Getter method for the dashboard scene.
     * 
     * @return The scene of the dashboard.
     */
    public Scene getScene() {
        return scene;
    }
}
