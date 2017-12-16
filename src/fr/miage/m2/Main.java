package fr.miage.m2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.miage.m2.job.Dice;
import fr.miage.m2.job.Game;
import fr.miage.m2.job.Player;
import fr.miage.m2.job.Points;
import fr.miage.m2.storage.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.geometry.Insets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.*;

public class Main extends Application {

    private static String relativePath = "src/fr/miage/m2/";

    @Override
    public void start(Stage primaryStage) throws Exception{

        String [] names = createTwoFielDialog();
        Player player = new Player(names[0], names[1], null);

        Dice diceOne = new Dice();
        Dice diceTwo = new Dice();

        List<Dice> dices = new ArrayList<Dice>();
        dices.add(diceOne);
        dices.add(diceTwo);

        Game game = Game.getInstance();
        game.setPlayer(player);
        game.setDices(dices);

        Points points = new Points(0);
        game.setPoint(points);

        player.setDices(dices);

        URL url = new File(relativePath + "resources/home.fxml").toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Home");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    private String [] createTwoFielDialog(){
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Personnal informations");

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField lastname = new TextField();
        lastname.setPromptText("Lastname");
        TextField firstname = new TextField();
        firstname.setPromptText("Firstname");

        gridPane.add(new Label("Lastname:"), 0, 0);
        gridPane.add(lastname, 1, 0);
        gridPane.add(new Label("Firstname:"), 0, 1);
        gridPane.add(firstname, 1, 1);

        dialog.getDialogPane().setContent(gridPane);

        // Request focus on the username field by default.
        Platform.runLater(() -> lastname.requestFocus());

        // Convert the result firstname a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(lastname.getText(), firstname.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        String [] names = new String[2];
        result.ifPresent(pair -> {
            names[0] = pair.getKey();
            names[1] = pair.getValue();
        });

        return names;
    }

    public static void main(String[] args) {
        //launch(args);
        /*ArrayList<HighScoreJSON> highScores = new ArrayList<HighScoreJSON>();

        HighScoreJSON highScoreJSON1 = new HighScoreJSON("Said", 10);
        HighScoreJSON highScoreJSON2 = new HighScoreJSON("Guillaume", 20);

        highScores.add(highScoreJSON1);
        highScores.add(highScoreJSON2);

        final Gson gson = new GsonBuilder().create();

        String json = gson.toJson(highScores);

        PrintWriter writer = null;
        try {
            writer = new PrintWriter("highscores.json", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.println(json);
        writer.close();*/

        HighScoreSr highScore = new HighScoreSr();
        System.out.println(highScore.getUserHighScoreByUserName("Said"));
    }

}
