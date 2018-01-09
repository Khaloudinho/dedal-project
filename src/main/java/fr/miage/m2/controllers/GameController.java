package fr.miage.m2.controllers;

import fr.miage.m2.job.Dice;
import fr.miage.m2.job.Game;
import fr.miage.m2.job.Player;
import fr.miage.m2.job.Points;
import fr.miage.m2.storage.persistkits.PersistKit;
import fr.miage.m2.storage.persistkits.jdbc.JDBCKit;
import fr.miage.m2.storage.persistkits.json.JSONKit;
import fr.miage.m2.storage.persistkits.redis.RedisKit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * JAVA FX controller which manages the user's view
 */
public class GameController extends Controller implements Initializable {

    //JAVA FX elements on user's view
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

    // Objects which have to interact with the controller
    private Game game = Game.getInstance();
    private Dice diceOne = game.getDices().get(0);
    private Dice diceTwo = game.getDices().get(1);

    //Saving systems
    private PersistKit peristKitJDBC = new JDBCKit();
    private PersistKit persistKitJSON = new JSONKit();
    private PersistKit persistKitRedis = new RedisKit();

    /**
     * Method which computes init operations before running the view associated with this controller
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Check if we are not cheating
        if (game.getCurrentTurn() >= 9)
            throwDices.setDisable(true);

        // Get the gamer (see main in order to understand)
        Player currentPlayer = game.getCurrentPlayer();

        // Display gamer data
        this.updateCurrentPlayerName();

        // Get previous high score
        int previousHighScore = persistKitJSON.getUserHighScoreByUserName(currentPlayer.getLastname() + "_" + currentPlayer.getFirstname());

        // Set gamer data (in view) about high score
        this.previousHighScore.setText("Previous high score : " + String.valueOf(previousHighScore));

        // Add the current player as dices observers
        diceOne.addObserver(currentPlayer);
        diceTwo.addObserver(currentPlayer);
    }

    /**
     * Method which updates the player name
     */
    public void setPlayerName() {
        this.updateCurrentPlayerName();
    }

    /**
     * Method which updates on the view the player name
     */
    private void updateCurrentPlayerName() {
        Player currentPlayer = game.getCurrentPlayer();
        this.playerName.setText("Player : " + currentPlayer.getLastname() + " " + currentPlayer.getFirstname());
    }

    /**
     * Method which updates on the view the dice one score
     */
    public void setScoreDiceOne() {
        this.scoreDiceOne.setText("Score first dice : " + String.valueOf(diceOne.getValue()));
    }

    /**
     * Method which updates on the view the dice two score
     */
    public void setScoreDiceTwo() {
        this.scoreDiceTwo.setText("Score second dice : " + String.valueOf(diceTwo.getValue()));
    }

    /**
     * Method which updates on the view the final score
     * And asks to save (or not) it as potential new high score
     */
    public void setFinalScore() {
        Player currentPlayer = game.getCurrentPlayer();

        // Get the points on the current turn
        int highScore = game.getPoint().getPoints();

        // Set the view with the correct final score
        this.finalScore.setText("Final score : " + String.valueOf(highScore));

        // Save it if relevant
        this.saveScoreOnAllStorageSystems(currentPlayer.getLastname() + "_" + currentPlayer.getFirstname(), highScore);
    }

    /**
     * Method which calls existing save systems
     *
     * @param username  concerned user
     * @param highScore potential high score
     */
    private void saveScoreOnAllStorageSystems(String username, Integer highScore) {

        // Ask save action
        this.peristKitJDBC.save(username, highScore);
        this.persistKitJSON.save(username, highScore);
        this.persistKitRedis.save(username, highScore);

        // Display used storage systems
        this.peristKitJDBC.info();
        this.persistKitJSON.info();
        this.persistKitRedis.info();
    }

    @FXML
    public void doTurn() {
        Player currentPlayer = game.getCurrentPlayer();

        // If player can play
        if (game.getCurrentTurn() < game.getNUMBER_OF_TURN()-1) {
            game.doTurn();
            setPlayerName();
            refreshView();

            // If player has not to play
            updateTurnStatus();
        } else {
            updateTurnStatusFinal();
            // Disable the button of throwing dices
            this.throwDices.setDisable(true);

            // Inform user (pop-up)
            // Create the alert and its contents
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game over");
            alert.setHeaderText("Game over");

            int finalScore = game.getPoint().getPoints();

            alert.setContentText("Congratulations !\nYou won with a final score of " + finalScore + " points !");
            alert.showAndWait();
        }
    }

    private void updateTurnStatus(){
        // Update turn status
        this.turn.setText("Tour : " + game.getCurrentTurn() + "/" + game.getNUMBER_OF_TURN());
    }

    private void updateTurnStatusFinal(){
        this.turn.setText("Tour : " + game.getNUMBER_OF_TURN() + "/" + game.getNUMBER_OF_TURN());
    }

    /**
     * Method which calls dice throwing
     *
     * @throws MalformedURLException
     */
    @FXML
    public void throwCurrentGameDices() throws MalformedURLException {
        Player currentPlayer = game.getCurrentPlayer();

        // Player throws dices
        int[] results;
        results = currentPlayer.throwDice();

        // Get points of his dices
        Points pointsCumules = new Points(game.getPoint().getPoints() + this.computeScoreCalculation());

        // Set the current turn score
        game.setPoint(pointsCumules);

        // Update views
        updateImages(results);
        refreshView();

        // Go to the next turn
        this.doTurn();
    }

    /**
     * Method which checks the rules, of calculation
     * and do scoring calculation
     *
     * @return
     */
    private int computeScoreCalculation() {
        int score = 0;
        int dicesSum = diceOne.getValue() + diceTwo.getValue();
        if (dicesSum == 7) {
            score = 10;
        } else {
            score = 0;
        }
        return score;
    }

    /**
     * Method which updates dices pictures
     *
     * @param results get results of the dices in the system
     * @throws MalformedURLException
     */
    private void updateImages(int[] results) throws MalformedURLException {
        // For windows
        // URI
        URL urlDiceOne = new File(relativePath + "resources/pictures/" + results[0] + ".png").toURL();
        URL urlDiceTwo = new File(relativePath + "resources/pictures/" + results[1] + ".png").toURL();

        // Set on the panel
        this.diceOneImage.setImage(new Image(String.valueOf(urlDiceOne)));
        this.diceTwoImage.setImage(new Image(String.valueOf(urlDiceTwo)));
    }

    /**
     * Method who asks all parts of the gamer's view to refresh
     */
    public void refreshView() {
        setScoreDiceOne();
        setScoreDiceTwo();
        setFinalScore();
    }

    //sub call
    public void openView(String view, String viewName) throws IOException {
        super.openView(view, viewName);
    }
}
