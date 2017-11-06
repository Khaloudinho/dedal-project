package fr.miage.m2.controllers;

import fr.miage.m2.job.Dice;
import fr.miage.m2.job.Game;
import fr.miage.m2.job.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController extends Controller implements Initializable {

    @FXML
    private Text playerName;

    @FXML
    private Text scoreDiceOne;

    @FXML
    private Text scoreDiceTwo;

    @FXML
    private Text finalScore;

    @FXML
    private Button throwDices;

    @FXML
    private Button doTurn;

    @FXML
    private ImageView diceOneImage;

    @FXML
    private ImageView diceTwoImage;

    private Game game = Game.getInstance();
    private Dice diceOne = game.getDices().get(0);
    private Dice diceTwo = game.getDices().get(1);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Player currentPlayer = game.getCurrentPlayer();
        this.updateCurrentPlayerName();
    }

    public void setPlayerName() {
        this.updateCurrentPlayerName();
    }

    private void updateCurrentPlayerName(){
        Player currentPlayer = game.getCurrentPlayer();
        this.playerName.setText("Player : " + currentPlayer.getLastname() + " " + currentPlayer.getFirstname());
    }

    public void setScoreDiceOne() {
        this.scoreDiceOne.setText("Score dice 1 : "+String.valueOf(diceOne.getValue()));
    }

    public void setScoreDiceTwo() {
        this.scoreDiceTwo.setText("Score dice 2 : "+String.valueOf(diceTwo.getValue()));
    }

    public void setFinalScore() {
        this.finalScore.setText("Final score : "+String.valueOf(diceOne.getValue()+diceTwo.getValue()));
    }

    public void setThrowDiceButton(){
        Player currentPlayer = game.getCurrentPlayer();
        if(!currentPlayer.isCanPlay()){
            this.throwDices.setDisable(true);
        }else {
            this.throwDices.setDisable(false);
        }
    }

    @FXML
    public void doTurn(){
        Player currentPlayer = game.getCurrentPlayer();
        if(game.getCurrentTurn()<game.getNUMBER_OF_TURN()) {
            game.doTurn();
            currentPlayer.setCanPlay(true);
            setPlayerName();
            refreshView();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("End of game");
            // A changer
            int finalScore=diceOne.getValue()+diceTwo.getValue();
            alert.setContentText("You have win with a final score of "+finalScore+" points !");

            alert.showAndWait();
        }
    }

    @FXML
    public void throwCurrentGameDices() throws MalformedURLException {
        Player currentPlayer = game.getCurrentPlayer();
        int[] results;
        results=currentPlayer.throwDice();
        updateImages(results);
        currentPlayer.setCanPlay(false);
        refreshView();
    }

    private void updateImages(int[] results) throws MalformedURLException {
        // For windaube
        URL urlDiceOne = new File(relativePath + "resources/pictures/" + results[0] +".png").toURL();
        URL urlDiceTwo = new File(relativePath + "resources/pictures/" + results[1] +".png").toURL();

        //System.out.println(urlDiceOne);
        //System.out.println(urlDiceTwo);

        this.diceOneImage.setImage(new Image(String.valueOf(urlDiceOne)));
        this.diceTwoImage.setImage(new Image(String.valueOf(urlDiceTwo)));
    }

    public void refreshView(){
        setScoreDiceOne();
        setScoreDiceTwo();
        setFinalScore();
        setThrowDiceButton();
    }

    public void openView(String view, String viewName) throws IOException {
        super.openView(view, viewName);
    }
}
