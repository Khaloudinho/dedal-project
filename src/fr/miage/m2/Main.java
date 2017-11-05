package fr.miage.m2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Main extends Application {

    private static String relativePath = "src/fr/miage/m2/";

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL url = new File(relativePath + "resources/home.fxml").toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Dedal Project");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
