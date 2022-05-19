package classes.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NewWindow {

    FXMLLoader loader = new FXMLLoader();

    public void openNewWindow(String nameFXML) {
        loader.setLocation(getClass().getResource(nameFXML));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }
}
