package fr.miage.m2;

import fr.miage.m2.job.Dice;
import fr.miage.m2.job.Game;
import fr.miage.m2.job.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.*;

public class Main extends Application {

    private static String relativePath = "src/fr/miage/m2/";

    @Override
    public void start(Stage primaryStage) throws Exception{

        Player player = new Player("", "", null);

        Dice diceOne = new Dice();
        Dice diceTwo = new Dice();

        List<Dice> dices = new ArrayList<Dice>();
        dices.add(diceOne);
        dices.add(diceTwo);

        Game game = Game.getInstance();
        game.setPlayer(player);
        game.setDices(dices);
        player.setDices(dices);

        URL url = new File(relativePath + "resources/home.fxml").toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Home");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
