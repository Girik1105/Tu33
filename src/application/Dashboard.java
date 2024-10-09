package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Dashboard {
	
	private Scene scene;

    public Dashboard(Stage primaryStage, String preferredName, String firstName, String middleName, String lastName, String email) {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        
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
        Button inviteButton = new Button("Invite A User");
        Button resetButton = new Button("Reset A User's Account");
        Button deleteButton = new Button("Delete a User's Account");
        Button listButton = new Button("List All Users");
        Button addRemoveRole = new Button("Add or Remove a User's Role");
        adminAction.getChildren().addAll(inviteButton, resetButton, deleteButton, listButton, addRemoveRole);
        
        VBox logoutArea = new VBox(10);
        Button logoutButton = new Button("Logout");
        logoutArea.getChildren().add(logoutButton);
        
        layout.getChildren().addAll(title, userInfo, adminAction, logoutArea);
        scene = new Scene(layout, 400, 300);
    }

    public Scene getScene() {
        return scene;
    }

}
