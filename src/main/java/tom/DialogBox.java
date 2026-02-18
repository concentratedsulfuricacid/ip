package tom;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        dialog.setMinWidth(0);
        dialog.maxWidthProperty().bind(widthProperty().multiply(0.72));
        displayPicture.setSmooth(true);
        applyAvatarClip();
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    private void applyAvatarClip() {
        double radius = Math.min(displayPicture.getFitWidth(), displayPicture.getFitHeight()) / 2.0;
        if (radius <= 0) {
            return;
        }
        displayPicture.setClip(new Circle(radius, radius, radius));
    }

    private void applyUserStyle() {
        dialog.getStyleClass().add("user");
        displayPicture.setManaged(false);
        displayPicture.setVisible(false);
    }

    private void applyBotStyle() {
        dialog.getStyleClass().add("bot");
    }

    private void applyErrorStyle() {
        dialog.getStyleClass().add("error");
    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.applyUserStyle();
        return db;
    }

    public static DialogBox getTomDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.applyBotStyle();
        return db;
    }

    public static DialogBox getErrorDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.applyBotStyle();
        db.applyErrorStyle();
        return db;
    }
}
