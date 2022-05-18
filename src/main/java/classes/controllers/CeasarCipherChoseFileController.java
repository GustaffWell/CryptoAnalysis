package classes.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import classes.CeasarCipher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CeasarCipherChoseFileController {

    NewWindow newWindow = new NewWindow();

    FileChooser chooser = new FileChooser();
    Path path;
    int shift;

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
    private TextField shiftEnterField;

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
            String s = shiftEnterField.getText().trim();
            try {
                shift = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                continueButton.getScene().getWindow().hide();
                newWindow.openNewWindow("ShiftNotNumber.fxml");
            }
            CeasarCipher.encrypt(path, shift);
            continueButton.getScene().getWindow().hide();
            newWindow.openNewWindow("FileEncryptedWindow.fxml");
        });
    }
}

