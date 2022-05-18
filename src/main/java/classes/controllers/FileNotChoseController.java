package classes.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FileNotChoseController {

    NewWindow newWindow = new NewWindow();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button OKButton;

    @FXML
    void initialize() {
        OKButton.setOnAction(actionEvent -> {
            OKButton.getScene().getWindow().hide();
            newWindow.openNewWindow("hello.fxml");
        });
    }
}


