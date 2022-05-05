package classes.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CeasarCipherWin1Controller {

    NewWindow newWindow = new NewWindow();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button brutForceButton;

    @FXML
    private Button decrButton;

    @FXML
    private Button encrButton;

    @FXML
    private Button mainMenuButton;

    @FXML
    void initialize() {
        encrButton.setOnAction(actionEvent ->  {
            encrButton.getScene().getWindow().hide();
            newWindow.openNewWindow("CeasarCipherChoseFile.fxml");
        });

        decrButton.setOnAction(actionEvent ->  {
            decrButton.getScene().getWindow().hide();
            newWindow.openNewWindow("CeasarCipherChoseFileToDecrypt.fxml");
        });

        mainMenuButton.setOnAction(actionEvent ->  {
            mainMenuButton.getScene().getWindow().hide();
            newWindow.openNewWindow("hello.fxml");
        });

        brutForceButton.setOnAction(actionEvent ->  {
            brutForceButton.getScene().getWindow().hide();
            newWindow.openNewWindow("BrutForceChoseFile.fxml");
        });
    }
}

