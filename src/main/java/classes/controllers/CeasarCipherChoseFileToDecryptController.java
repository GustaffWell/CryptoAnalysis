package classes.controllers;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import classes.CeasarCipher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CeasarCipherChoseFileToDecryptController {

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
    void initialize(MouseEvent event) {

    }

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
            if (shift > 10 || shift < 1) {
                continueButton.getScene().getWindow().hide();
                newWindow.openNewWindow("ShiftNotNumber.fxml");
            }
            CeasarCipher.decrypt(path, shift);
            continueButton.getScene().getWindow().hide();
            newWindow.openNewWindow("FileDecryptedWindow.fxml");
        });
    }
}
