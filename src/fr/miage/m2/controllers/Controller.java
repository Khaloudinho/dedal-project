package fr.miage.m2.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Controller implements Initializable {

    @FXML
    private Button play;

    @FXML
    private Button rules;

    @FXML
    private Button about;

    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void openUserView() throws IOException {
        this.openView("user.fxml", "Jeu");
    }

    @FXML
    public void openRulesView() throws IOException {
        this.openView("rules.fxml", "Règles");
    }

    @FXML
    public void openAboutView() throws IOException {
        this.openView("about.fxml", "A propos");
    }

    private void openView(String view, String viewName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/"+view));
        System.out.println(fxmlLoader.getLocation());
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle(viewName);
        stage.setScene(new Scene(root1));
        stage.show();
    }
}
