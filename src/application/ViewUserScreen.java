package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

public class ViewUserScreen extends VBox {

    public ViewUserScreen(Stage stage, DatabaseHelper databaseHelper) {
        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setSpacing(10);

        Label headerLabel = new Label("List of Users:");
        TextArea usersArea = new TextArea();
        usersArea.setEditable(false);

        Button backButton = new Button("Back to Dashboard");
        backButton.setOnAction(e -> stage.setScene(new Scene(new Dashboard(stage, databaseHelper), 500, 400)));

        try {
            List<User> users = databaseHelper.listUsers();
            if (users.isEmpty()) {
                usersArea.setText("No users found.");
            } else {
                StringBuilder userText = new StringBuilder();
                for (User user : users) {
                    userText.append("ID: ").append(user.getId()).append("\n")
                            .append("Email: ").append(user.getEmail()).append("\n")
                            .append("Role: ").append(user.getRole()).append("\n")
                            .append("----------------------------------------\n");
                }
                usersArea.setText(userText.toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            usersArea.setText("An error occurred while retrieving users.");
        }

        getChildren().addAll(headerLabel, usersArea, backButton);
    }
}
