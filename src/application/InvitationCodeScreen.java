package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InvitationCodeScreen {
	private Stage stage;
    private VBox layout;

    public InvitationCodeScreen(Stage stage) {
        this.stage = stage;
        layout = new VBox(10);

        Label infoLabel = new Label("Generate invitation codes for new users.");

        Button generateCodeButton = new Button("Generate Code");

        generateCodeButton.setOnAction(e -> {
            infoLabel.setText("Code generated: ABC123");
        });

        layout.getChildren().addAll(infoLabel, generateCodeButton);
    }

    public VBox getLayout() {
        return layout;
    }
}
