package tom;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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

    private Tom tom;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Tom instance */
    public void setTom(Tom t) {
        tom = t;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Tom's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        TomResponse response = tom.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                response.isError()
                        ? DialogBox.getErrorDialog(response.getMessage(), dukeImage)
                        : DialogBox.getTomDialog(response.getMessage(), dukeImage)
        );
        userInput.clear();
        if (response.isExit()) {
            closeWindow();
        }
    }

    private void closeWindow() {
        if (userInput.getScene() == null) {
            return;
        }
        userInput.getScene().getWindow().hide();
    }

    /**
     * Displays the welcome message in the dialog container.
     */
    public void showWelcomeMessage() {
        String welcomeMessage = tom.getWelcomeMessage();
        dialogContainer.getChildren().add(
                DialogBox.getTomDialog(welcomeMessage, dukeImage)
        );
    }
}
