package eus.ehu.eliminate;

import javafx.animation.PauseTransition;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class HelloController {
    @FXML
    private Label thinkNumberText;

    @FXML
    private Label welcomeText;

    @FXML
    private TextField inputField;

    @FXML
    private Button actionButton;

    @FXML
    private Button resetButton;

    @FXML
    private Pane surpriseFacesPane;

    @FXML
    private Circle faceCircle;

    @FXML
    private Circle eyeLeft;

    @FXML
    private Circle eyeRight;

    @FXML
    private Line mouthLine;

    @FXML
    public void initialize() {
        // Enlazar las formas al tamaño del Pane
        faceCircle.radiusProperty().bind(Bindings.min(surpriseFacesPane.widthProperty(), surpriseFacesPane.heightProperty()).divide(4));
        faceCircle.centerXProperty().bind(surpriseFacesPane.widthProperty().divide(2));
        faceCircle.centerYProperty().bind(surpriseFacesPane.heightProperty().divide(2));

        eyeLeft.radiusProperty().bind(faceCircle.radiusProperty().divide(5));
        eyeLeft.centerXProperty().bind(faceCircle.centerXProperty().subtract(faceCircle.radiusProperty().divide(2)));
        eyeLeft.centerYProperty().bind(faceCircle.centerYProperty().subtract(faceCircle.radiusProperty().divide(3)));

        eyeRight.radiusProperty().bind(faceCircle.radiusProperty().divide(5));
        eyeRight.centerXProperty().bind(faceCircle.centerXProperty().add(faceCircle.radiusProperty().divide(2)));
        eyeRight.centerYProperty().bind(faceCircle.centerYProperty().subtract(faceCircle.radiusProperty().divide(3)));

        mouthLine.startXProperty().bind(faceCircle.centerXProperty().subtract(faceCircle.radiusProperty().divide(2)));
        mouthLine.startYProperty().bind(faceCircle.centerYProperty().add(faceCircle.radiusProperty().divide(3)));
        mouthLine.endXProperty().bind(faceCircle.centerXProperty().add(faceCircle.radiusProperty().divide(2)));
        mouthLine.endYProperty().bind(faceCircle.centerYProperty().add(faceCircle.radiusProperty().divide(3)));

        // Configurar Enter para los botones
        actionButton.setDefaultButton(true);
        resetButton.setDefaultButton(true);

        // Configurar Enter para el TextField
        inputField.setOnAction(event -> onShowTextButtonClick());
    }

    @FXML
    protected void onShowTextButtonClick() {
        thinkNumberText.setVisible(false);
        actionButton.setVisible(false);
        inputField.setVisible(false);
        surpriseFacesPane.setVisible(true);

        String input = inputField.getText();
        if (!input.matches("\\d+")) {
            welcomeText.setText("Por favor, introduce solo números.");
            welcomeText.getStyleClass().add("visible");
            resetButton.setVisible(true);
            return;
        }

        welcomeText.setText("Leyendo tu cerebro...");
        welcomeText.getStyleClass().add("visible");
        PauseTransition delay1 = new PauseTransition(Duration.seconds(2));
        delay1.setOnFinished(event -> {
            welcomeText.setText("Procesando tus pensamientos...");
            PauseTransition delay2 = new PauseTransition(Duration.seconds(2));
            delay2.setOnFinished(event2 -> {
                welcomeText.setText("Estabas pensando en este número: " + input);
                surpriseFacesPane.setVisible(false);
                resetButton.setVisible(true);
            });
            delay2.play();
        });
        delay1.play();
    }

    @FXML
    protected void onResetButtonClick() {
        thinkNumberText.setVisible(true);
        actionButton.setVisible(true);
        inputField.setVisible(true);
        inputField.clear();
        welcomeText.setText("");
        welcomeText.getStyleClass().remove("visible");
        resetButton.setVisible(false);
        surpriseFacesPane.setVisible(false);
    }
}