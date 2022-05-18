package classes.controllers;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import classes.StatisticalAnalysis;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class StatisticalChoseFilesController {

    NewWindow newWindow = new NewWindow();

    FileChooser chooser = new FileChooser();
    Path path1;
    Path path2;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button choseEncryptedFileButton;

    @FXML
    private Button choseExampleFileButton;

    @FXML
    private Button continueButton;

    @FXML
    private Button mainMenuButton;

    @FXML
    void initialize() {
        choseEncryptedFileButton.setOnAction(actionEvent -> {
            File file = chooser.showOpenDialog(new Stage());
            path1 = Paths.get(file.getPath());
        });

        choseExampleFileButton.setOnAction(actionEvent -> {
            File file = chooser.showOpenDialog(new Stage());
            path2 = Paths.get(file.getPath());
        });

        continueButton.setOnAction(actionEvent -> {
            if (path1 == null  || path2 == null) {
                continueButton.getScene().getWindow().hide();
                newWindow.openNewWindow("FileNotChose.fxml");
            }
            StatisticalAnalysis.decryptStat(path1, path2);
            continueButton.getScene().getWindow().hide();
            newWindow.openNewWindow("FileDecryptedWindow.fxml");
        });

        mainMenuButton.setOnAction(actionEvent -> {
            mainMenuButton.getScene().getWindow().hide();
            newWindow.openNewWindow("hello.fxml");
        });
    }
}
