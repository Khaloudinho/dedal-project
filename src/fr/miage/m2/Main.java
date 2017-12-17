package fr.miage.m2;

import fr.miage.m2.job.Dice;
import fr.miage.m2.job.Game;
import fr.miage.m2.job.Player;
import fr.miage.m2.job.Points;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class which run the game
 */
public class Main extends Application {

    private static String relativePath = "src/fr/miage/m2/";

    //Start UX
    @Override
    public void start(Stage primaryStage) throws Exception {

        String[] names = createTwoFielDialog();
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

    /**
     * Create a popup for Lastname ans Firstname of the player
     *
     * @return
     */
    private String[] createTwoFielDialog() {
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

        String[] names = new String[2];
        result.ifPresent(pair -> {
            names[0] = pair.getKey();
            names[1] = pair.getValue();
        });

        return names;
    }

    /**
     * Run the application
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

}
