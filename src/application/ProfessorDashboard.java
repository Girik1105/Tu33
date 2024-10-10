package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProfessorDashboard {
	private Scene scene;

    public ProfessorDashboard(Stage primaryStage, String preferredName, String firstName, String middleName, String lastName, String email) {
    	VBox layout = new VBox();
    	layout.setPadding(new Insets(20));
    	
    	 VBox userInfo = new VBox(10);
         Label firstNameDashboard = new Label("First Name: " + firstName);
         Label middleNameDashboard = new Label("Middle Name: " + middleName);
         Label lastNameDashboard = new Label("Last Name: " + lastName);
         Label emailDashboard = new Label("Email: " + email);
         userInfo.getChildren().addAll(firstNameDashboard, middleNameDashboard, lastNameDashboard, emailDashboard);
         
         VBox logoutArea = new VBox(10);
         Button logoutButton = new Button("Logout");
         logoutButton.setStyle(LoginPage.buttonStyle);
        
         logoutButton.setOnAction(e -> {
        	LoginPage loginPage = new LoginPage(); // Login page class
            loginPage.start(primaryStage); // Launch LoginPage
    
         });
         logoutArea.getChildren().add(logoutButton);
         
         Text loggedIn = new Text("Professor Account Created!");
         loggedIn.setStyle("-fx-font-size: 24px;");
        
         layout.getChildren().addAll(userInfo, loggedIn, logoutArea);
        
         //layout.getChildren().addAll(title, userInfo, adminAction, textarea, logoutArea);
         scene = new Scene(layout, 400, 200);
    	}

    public Scene getScene() {
        return scene;
    }
}
