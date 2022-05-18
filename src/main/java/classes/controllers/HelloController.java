package classes.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

public class HelloController {

    NewWindow newWindow = new NewWindow();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ceasarCipherButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button statisticButton;

    @FXML
    void initialize() {
        ceasarCipherButton.setOnAction(actionEvent -> {
            ceasarCipherButton.getScene().getWindow().hide();
            newWindow.openNewWindow("CeasarCipherWin1.fxml");
        });

        statisticButton.setOnAction(actionEvent -> {
            statisticButton.getScene().getWindow().hide();
            newWindow.openNewWindow("StatisticalChoseFiles.fxml");
        });

        exitButton.setOnAction(event -> {
            System.exit(1);
        });
    }
}
