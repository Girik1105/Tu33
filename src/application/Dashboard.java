package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Dashboard {
	
	private Scene scene;

    public Dashboard(Stage primaryStage, String preferredName, String firstName, String middleName, String lastName, String email) {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        
        Text textarea = new Text();
        
        VBox title = new VBox(10);
        String displayFirstName;
        if (preferredName != null && !preferredName.trim().isEmpty()) {
        	displayFirstName = preferredName;
        } else {
        	displayFirstName = firstName;
        }
        
        Label welcomeLabel = new Label("Welcome, " + displayFirstName + "!"); // Use displayName here
        welcomeLabel.setStyle(LoginPage.h3bold);
        title.getChildren().add(welcomeLabel);
        
        VBox userInfo = new VBox(10);
        Label firstNameDashboard = new Label("First Name: " + firstName);
        Label middleNameDashboard = new Label("Middle Name: " + middleName);
        Label lastNameDashboard = new Label("Last Name: " + lastName);
        Label emailDashboard = new Label("Email: " + email);
        userInfo.getChildren().addAll(firstNameDashboard, middleNameDashboard, lastNameDashboard, emailDashboard);
        
        FlowPane adminAction = new FlowPane();
        
        /*** Invite a User *****************************************************/
        Button inviteButton = new Button("Invite A User");
        inviteButton.setOnAction(e -> {
            Alert roleAlert = new Alert(AlertType.INFORMATION);
            roleAlert.setTitle("Invite User");
            roleAlert.setHeaderText("Select a Role for the Invitation");

            // radio button for roles
            RadioButton adminRole = new RadioButton("Admin");
            RadioButton studentRole = new RadioButton("Student");
            RadioButton professorRole = new RadioButton("Professor");

            // group radio buttons
            ToggleGroup roleGroup = new ToggleGroup();
            adminRole.setToggleGroup(roleGroup);
            studentRole.setToggleGroup(roleGroup);
            professorRole.setToggleGroup(roleGroup);

            // display role options
            HBox roleSelectionLayout = new HBox(10);
            roleSelectionLayout.getChildren().addAll(adminRole, studentRole, professorRole);

            // Add the role selection layout to the alert
            roleAlert.getDialogPane().setContent(roleSelectionLayout);

            // Show the popup
            roleAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Generate the invitation code
                    String adminCode = "60458";
                    String studentCode = "stu123";
                    String professorCode = "prof123";
                    
                    // Determine the selected role
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
        
        /*************************************************************************/
        Button resetButton = new Button("Reset A User's Account");
        resetButton.setOnAction(e -> {
            String oneTimePassword = "temporaryPassword123"; 
            String expirationTime = "2024-12-31 23:59"; // Placeholder expiration date
            
            textarea.setText("One-time Password: " + oneTimePassword + "\nExpiration: " + expirationTime);
        });
        /*************************************************************************/
        
        /*************************************************************************/
        Button deleteButton = new Button("Delete a User's Account");
        deleteButton.setOnAction(e -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText("Are you sure?");
            alert.setContentText("Type 'Yes' to confirm.");
            
            Button yesButton = new Button("Yes");
            yesButton.setOnAction(event -> {
                // Logic to delete the user
                textarea.setText("User deleted successfully.");
            });
            alert.showAndWait();
        });
        /*************************************************************************/
        
        /*************************************************************************/
        Button listButton = new Button("List All Users");
        listButton.setOnAction(e -> {
            String usersList = "User1: Nghi\nUser2: Girik\nUser3: Becca";
            textarea.setText("Users:\n" + usersList);
        });
        /*************************************************************************/
        
        Button addRemoveRole = new Button("Add or Remove a User's Role");
        addRemoveRole.setOnAction(e -> {
            // Prompt to select a user and role
            String selectedUser = "User1";
            String roleAction = "Add";
            
            textarea.setText(roleAction + " role to/from " + selectedUser);
        });
        adminAction.getChildren().addAll(inviteButton, resetButton, deleteButton, listButton, addRemoveRole);
        
        VBox logoutArea = new VBox(10);
        Button logoutButton = new Button("Logout");
        
        logoutButton.setOnAction(e -> {
        	LoginPage loginPage = new LoginPage(); // Login page class
            loginPage.start(primaryStage); // Launch LoginPage
    
        });
        logoutArea.getChildren().add(logoutButton);
        
        layout.getChildren().addAll(title, userInfo, adminAction, textarea, logoutArea);
        scene = new Scene(layout, 400, 350);
    }

    public Scene getScene() {
        return scene;
    }

}
