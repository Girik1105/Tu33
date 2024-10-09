package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class setUpScreen {

	public void start(Stage primaryStage) {
		
		VBox setUpDescription = new VBox();
    	
    	setUpDescription.setPadding(new Insets(10));
    	setUpDescription.setAlignment(Pos.CENTER);
    	setUpDescription.setStyle("-fx-background-color: lightblue;");
    	
    	Text description = new Text("Finish Setting Up Your Account");
    	description.setStyle("-fx-font-size: 16px;");
    	
    	setUpDescription.getChildren().addAll(description);
    	
		 /* GridPane layout for 
		  * email
		  * first name
		  * middle name
		  * last name
		  * preferred name
		  * */
        GridPane setupGrid = new GridPane(10, 10);
        setupGrid.setAlignment(Pos.CENTER);
        setupGrid.setPadding(new Insets(20));

        // Labels and input fields
        Label emailLabel = new Label("Email:"); // label for email
        emailLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        setupGrid.add(emailLabel, 0, 0); // add email label to grid
        TextField emailTextfield = new TextField();
        setupGrid.add(emailTextfield, 1, 0);
        
        // Labels and input fields
        Label firstNameLabel = new Label("First Name:"); // label for username
        firstNameLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        setupGrid.add(firstNameLabel, 0, 1); // add username to grid
        TextField firstNameTextfield = new TextField();
        setupGrid.add(firstNameTextfield, 1, 1);

        Label middleNameLabel = new Label("Middle Name:");
        middleNameLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        setupGrid.add(middleNameLabel, 0, 2);
        TextField middleNameTextfield = new TextField();
        setupGrid.add(middleNameTextfield, 1, 2);
        
        Label lastNameLabel = new Label("Last Name:");
        lastNameLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        setupGrid.add(lastNameLabel, 0, 3);
        TextField lastNameTextfield = new TextField();
        setupGrid.add(lastNameTextfield, 1, 3);
        
        Label prefNameLabel = new Label("Preferred First Name:");
        prefNameLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        setupGrid.add(prefNameLabel, 0, 4);
        TextField prefNameTextfield = new TextField();
        setupGrid.add(prefNameTextfield, 1, 4);
        
        
        // Create a simple home screen with a logout button
        Button nextBtn = new Button("Done");
        nextBtn.setStyle("-fx-background-color: lightblue; -fx-font-weight: bold;");

        // Set action for logout button to return to the login screen
        nextBtn.setOnAction(e -> {
            LoginPage loginPage = new LoginPage();
            loginPage.start(primaryStage);
        });
        
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: floralwhite;");
        layout.getChildren().addAll(setUpDescription, setupGrid, nextBtn);


        // Set the scene and show
        Scene homeScene = new Scene(layout, 450, 350);
        primaryStage.setTitle("Set Up Account");
        primaryStage.setScene(homeScene);
        primaryStage.show();
    }
        
}
