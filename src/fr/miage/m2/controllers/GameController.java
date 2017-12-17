package fr.miage.m2.controllers;

import fr.miage.m2.job.Dice;
import fr.miage.m2.job.Game;
import fr.miage.m2.job.Player;
import fr.miage.m2.job.Points;
import fr.miage.m2.storage.persistkits.jdbc.JDBCKit;
import fr.miage.m2.storage.persistkits.json.JSONKit;
import fr.miage.m2.storage.persistkits.PersistKit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

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
    private Text previousHighScore;

    @FXML
    private Text turn;

    @FXML
    private Button throwDices;

    @FXML
    private ImageView diceOneImage;

    @FXML
    private ImageView diceTwoImage;

    private Game game = Game.getInstance();
    private Dice diceOne = game.getDices().get(0);
    private Dice diceTwo = game.getDices().get(1);

    //Saving system
    private PersistKit peristKitJDBC = new JDBCKit();
    private PersistKit persistKitJSON = new JSONKit();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Player currentPlayer = game.getCurrentPlayer();
        this.updateCurrentPlayerName();

        int previousHighScore = persistKitJSON.getUserHighScoreByUserName(currentPlayer.getLastname()+"_"+currentPlayer.getFirstname());
        this.previousHighScore.setText("Previous high score : "+String.valueOf(previousHighScore));
    }

    public void setPlayerName() {
        this.updateCurrentPlayerName();
    }

    private void updateCurrentPlayerName(){
        Player currentPlayer = game.getCurrentPlayer();
        this.playerName.setText("Player : " + currentPlayer.getLastname() + " " + currentPlayer.getFirstname());
    }

    public void setScoreDiceOne() {
        this.scoreDiceOne.setText("Score first dice : "+String.valueOf(diceOne.getValue()));
    }

    public void setScoreDiceTwo() {
        this.scoreDiceTwo.setText("Score second dice : "+String.valueOf(diceTwo.getValue()));
    }

    public void setFinalScore() {
        Player currentPlayer = game.getCurrentPlayer();
        int highScore = game.getPoint().getPoints();
        this.finalScore.setText("Final score : "+String.valueOf(highScore));
        this.saveScoreOnAllStorageSystems(currentPlayer.getLastname()+"_"+currentPlayer.getFirstname(), highScore);
    }

    private void saveScoreOnAllStorageSystems(String username, Integer highScore){
        this.peristKitJDBC.save(username, highScore);
        this.persistKitJSON.save(username, highScore);
        this.peristKitJDBC.info();
        this.persistKitJSON.info();
    }

    @FXML
    public void doTurn(){
        Player currentPlayer = game.getCurrentPlayer();
        if (game.getCurrentTurn() < game.getNUMBER_OF_TURN()) {
            game.doTurn();
            setPlayerName();
            refreshView();
        } else {
            this.throwDices.setDisable(true);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game over");
            alert.setHeaderText("Game over");
            // A changer
            int finalScore = game.getPoint().getPoints();
            alert.setContentText("Congratulations !\nYou won with a final score of " + finalScore + " points !");
            alert.showAndWait();
        }

        this.turn.setText("Tour : "+game.getCurrentTurn()+"/"+game.getNUMBER_OF_TURN());
    }

    @FXML
    public void throwCurrentGameDices() throws MalformedURLException {
        Player currentPlayer = game.getCurrentPlayer();
        int[] results;
        results=currentPlayer.throwDice();

        Points pointsCumules = new Points(game.getPoint().getPoints()+this.computeScoreCalculation());
        game.setPoint(pointsCumules);

        updateImages(results);
        refreshView();

        this.doTurn();
    }

    private int computeScoreCalculation(){
        int score = 0;
        int dicesSum = diceOne.getValue()+diceTwo.getValue();
        if(dicesSum>=7){
            score = 10;
        }else{
            score = dicesSum;
        }
        return score;
    }
    private void updateImages(int[] results) throws MalformedURLException {
        // For windaube
        URL urlDiceOne = new File(relativePath + "resources/pictures/" + results[0] +".png").toURL();
        URL urlDiceTwo = new File(relativePath + "resources/pictures/" + results[1] +".png").toURL();

        this.diceOneImage.setImage(new Image(String.valueOf(urlDiceOne)));
        this.diceTwoImage.setImage(new Image(String.valueOf(urlDiceTwo)));
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
