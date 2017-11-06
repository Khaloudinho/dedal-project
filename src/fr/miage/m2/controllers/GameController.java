package fr.miage.m2.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;

public class GameController extends Controller {

    @FXML
    private Text playerName;

    @FXML
    private Text scoreDiceOne;

    @FXML
    private Text scoreDiceTwo;

    @FXML
    private Text finalScore;

    @FXML
    public void displayPlayerName() throws IOException {
        this.openView("user.fxml", "Game");
    }

    @FXML
    public void displayScoreDiceOne() throws IOException {
        this.openView("rules.fxml", "Rules");
    }

    @FXML
    public void displayScoreDiceTwo() throws IOException {
        this.openView("about.fxml", "About");
    }

    public void openView(String view, String viewName) throws IOException {
        super.openView(view, viewName);
    }
}