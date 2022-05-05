package classes.controllers;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import classes.CeasarCipher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class BrutForceChoseFileController {

    NewWindow newWindow = new NewWindow();

    FileChooser chooser = new FileChooser();
    Path path;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button choseFileButton;

    @FXML
    private Button continueButton;

    @FXML
    private Button mainMenuButton;

    @FXML
    void initialize() {
        mainMenuButton.setOnAction(actionEvent -> {
            mainMenuButton.getScene().getWindow().hide();
            newWindow.openNewWindow("hello.fxml");
        });

        chooser.setInitialDirectory(new File("C:\\Users\\Home\\Desktop\\"));

        choseFileButton.setOnAction(actionEvent -> {
            File file = chooser.showOpenDialog(new Stage());
            path = Paths.get(file.getPath());
        });

        continueButton.setOnAction(actionEvent -> {
            if (path == null) {
                continueButton.getScene().getWindow().hide();
                newWindow.openNewWindow("FileNotChose.fxml");
            }

            CeasarCipher.brutForce(path);
            continueButton.getScene().getWindow().hide();
            newWindow.openNewWindow("FileDecryptedWindow.fxml");
        });
    }
}
