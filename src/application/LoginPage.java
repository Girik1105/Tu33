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

public class LoginPage extends Application {
	
	public static String blueBackground = "-fx-background-color: lightblue;";
	public static String baseBackground = "-fx-background-color: floralwhite;";
	public static String h1 = "-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #4b5d75;";
	public static String h2 = "-fx-font-size: 16px;";
	public static String h3bold = "-fx-font-size: 14px; -fx-font-weight: bold";
	public static String errorText = "-fx-text-fill: red";
	public static String buttonStyle = "-fx-background-color: lightblue; -fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #4b5d75;";

    @Override
    public void start(Stage primaryStage) {
    	// VBox for application name and description
    	VBox title = new VBox(); // create area for title and description	
    	title.setPadding(new Insets(10)); // set padding to area where title and description will go
    	title.setAlignment(Pos.CENTER); // align items in VBox to center
    	title.setStyle(blueBackground); // set background color to VBox
    	
    	Label label = new Label("Welcome to BookedIn"); // create label with title
    	label.setStyle(h1);    	
    	Text description = new Text("Start by logging in!"); // create text with description
    	description.setStyle(h2);
    	
    	title.getChildren().addAll(label, description); // add application title and description to VBox
    	
    	GridPane grid = new GridPane(10, 10); // create grid area 
    	grid.setAlignment(Pos.CENTER); // align items in grid to center
    	grid.setPadding(new Insets(20)); // set padding
    	
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameLabel.setStyle(h3bold);
        grid.add(usernameLabel, 0,0);
        grid.add(usernameField, 1,0);

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordLabel.setStyle(h3bold);
        grid.add(passwordLabel, 0,1);
        grid.add(passwordField, 1,1);
        
        Button invitationSubmit = new Button("Enter Invitation Code");
        invitationSubmit.setStyle(buttonStyle);
        grid.add(invitationSubmit, 1,4);
        invitationSubmit.setOnAction(e -> {
        	OtherUser OtherUser = new OtherUser(primaryStage); 
        	primaryStage.setScene(OtherUser.getScene());
            primaryStage.setTitle("Log in with Code");
        });

        Button loginButton = new Button("Create Account");
        loginButton.setOnAction(e -> {
        	String username = usernameField.getText();
        	String password = passwordField.getText();
        	
        	if (validateCredentials(username, password)) {
        		System.out.println("Login Successful!");
        		setUpScreen setUpScreen = new setUpScreen(primaryStage, username, password);
        		primaryStage.setScene(setUpScreen.getScene());
        		primaryStage.setTitle("Account Setup");
        	} else {
        		System.out.println("Invalid credentials!");
        		 Label errorMessage = new Label("Username/Password or Code Incorrect");
        		 errorMessage.setStyle(errorText);
        		 grid.add(errorMessage, 1, 5);
        	}
        });
        loginButton.setStyle(buttonStyle);
        grid.add(loginButton, 1, 3);
        
      /*** Organizing panels/grids ***********************************/
      VBox layout = new VBox(20);
      layout.setAlignment(Pos.TOP_CENTER);
      layout.setStyle(baseBackground);
      layout.getChildren().addAll(title, grid);

      // Scene setup
      Scene scene = new Scene(layout, 500, 290);
      primaryStage.setTitle("Login Screen");
      primaryStage.setScene(scene);
      primaryStage.show();

    }
    
    private boolean validateCredentials(String username, String password) {
        return username.equals("admin") && password.equals("pass");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
