package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Dashboard {
	
	private Scene scene;

    public Dashboard(Stage primaryStage, String preferredName, String firstName) {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        
        String displayFirstName;
        if (preferredName != null && !preferredName.trim().isEmpty()) {
        	displayFirstName = preferredName;
        } else {
        	displayFirstName = firstName;
        }
        
        Label welcomeLabel = new Label("Welcome, " + displayFirstName + "!"); // Use displayName here
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        
        layout.getChildren().add(welcomeLabel);

        scene = new Scene(layout, 400, 300);
    }

    public Scene getScene() {
        return scene;
    }

}
