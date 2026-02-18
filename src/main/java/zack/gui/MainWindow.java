package zack.gui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import zack.Zack;
import zack.command.CommandResult;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Zack zack;

    private final Image userImage =
            new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image zackImage =
            new Image(this.getClass().getResourceAsStream("/images/DaZack.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        dialogContainer.getChildren().add(
                DialogBox.getZackDialog("Hello! I'm Zack\nWhat can I do for you?", zackImage)
        );
    }

    /** Injects the Zack instance */
    public void setZack(Zack z) {
        zack = z;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        CommandResult result = zack.executeCommand(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getZackDialog(result.getFeedbackToUser(), zackImage)
        );

        userInput.clear();

        if (result.shouldExit()) {
            userInput.setDisable(true);
            sendButton.setDisable(true);

            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(e -> Platform.exit());
            delay.play();
        }
    }
}
