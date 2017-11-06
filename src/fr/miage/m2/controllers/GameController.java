package fr.miage.m2.controllers;

import fr.miage.m2.job.Dice;
import fr.miage.m2.job.Game;
import fr.miage.m2.job.Player;
import fr.miage.m2.job.Points;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
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
    private Text turn;

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

        String firstname = "", lastname = "";

        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Enter your name");
        dialog.setHeaderText("Please enter your complete name below");

        //dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

        ButtonType submitButton = new ButtonType("Submit", ButtonBar.ButtonData.OK_DONE);
        // il faut ajouter un listener la pour appeler user !
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(submitButton, cancelButton);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 210, 10, 10));

        TextField askFirstname = new TextField();
        askFirstname.setPromptText("Walter");
        TextField askLastname = new TextField();
        askLastname.setPromptText("White");

        grid.add(new Label("First name :"), 0, 0);
        grid.add(askFirstname, 1, 0);
        grid.add(new Label("Last name :"), 0, 1);
        grid.add(askLastname, 1, 1);

        dialog.getDialogPane().setContent(grid);

        Optional<Pair<String, String>> result = dialog.showAndWait();

        if (result.isPresent()) {
            firstname = result.get().getKey();
            lastname = result.get().getValue();
        }

        currentPlayer.setFirstname(firstname);
        currentPlayer.setLastname(lastname);

        this.playerName.setText("Player : " + currentPlayer.getFirstname() + " " + currentPlayer.getLastname());

    }

    public void setScoreDiceOne() {
        this.scoreDiceOne.setText("Score first dice : "+String.valueOf(diceOne.getValue()));
    }

    public void setScoreDiceTwo() {
        this.scoreDiceTwo.setText("Score second dice : "+String.valueOf(diceTwo.getValue()));
    }

    public void setFinalScore() {
        this.finalScore.setText("Final score : "+String.valueOf(game.getPoint().getPoints()));
    }

    public void setThrowDiceButton(){
        Player currentPlayer = game.getCurrentPlayer();
        if (!currentPlayer.isCanPlay()){
            this.throwDices.setDisable(true);
        } else {
            this.throwDices.setDisable(false);
        }
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
            score = 7;
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
