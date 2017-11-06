package fr.miage.m2.controllers;

import fr.miage.m2.job.Dice;
import fr.miage.m2.job.Game;
import fr.miage.m2.job.Player;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

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
    private Button throwDices;

    @FXML
    private Button doTurn;

    private Game game = Game.getInstance();
    private Dice diceOne = game.getDices().get(0);
    private Dice diceTwo = game.getDices().get(1);


    public void setPlayerName() {
        Player currentPlayer = game.getCurrentPlayer();
        this.playerName.setText("Player : " + currentPlayer.getLastname() + " " + currentPlayer.getFirstname());
    }

    public void setScoreDiceOne() {
        this.scoreDiceOne.setText("Score dice 1: "+String.valueOf(diceOne.getValue()));
    }

    public void setScoreDiceTwo() {
        this.scoreDiceTwo.setText("Score dice 2: "+String.valueOf(diceTwo.getValue()));
    }

    public void setFinalScore() {
        this.finalScore.setText("Final score: "+String.valueOf(diceOne.getValue()+diceTwo.getValue()));
    }

    @FXML
    public void doTurn(){
        game.doTurn();
        setPlayerName();
        refreshView();
    }

    @FXML
    public void throwCurrentGameDices(){
        Player currentPlayer = game.getCurrentPlayer();
        currentPlayer.throwDice();
    }

    public void refreshView(){
        setScoreDiceOne();
        setScoreDiceTwo();
        setFinalScore();
    }

    public void openView(String view, String viewName) throws IOException {
        super.openView(view, viewName);
    }
}
