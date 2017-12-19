package fr.miage.m2.storage.highscores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import fr.miage.m2.storage.persistkits.json.HighScoreJSONModel;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

/**
 * HighScore for JSON
 */
public class HighScoreKitJSON extends HighScore {

    private final Gson gson = new GsonBuilder().create();

    public String info(){
        return "I am JSON storage system !";
    }

    private ArrayList<HighScoreJSONModel> getHighScoreFromJSON() {
        Type collectionType = new TypeToken<Collection<HighScoreJSONModel>>() {
        }.getType();

        String path = "highscores.json";
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return gson.fromJson(bufferedReader, collectionType);
    }

    private void saveScores(ArrayList<HighScoreJSONModel> scores) {
        String json = gson.toJson(scores);

        PrintWriter writer = null;
        try {
            writer = new PrintWriter("highscores.json", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.println(json);
        writer.close();
    }

    @Override
    public Integer getUserHighScoreByUserName(String username) {
        ArrayList<HighScoreJSONModel> scores = getHighScoreFromJSON();
        return getUserHighScore(scores, username);
    }

    private Integer getUserHighScore(ArrayList<HighScoreJSONModel> scores, String username){
        for (HighScoreJSONModel highScoreJSON:
             scores) {
            if(highScoreJSON.getUsername().equals(username))
                return highScoreJSON.getScore();
        }
        return 0;
    }

    private ArrayList<HighScoreJSONModel> setUserHighScore(ArrayList<HighScoreJSONModel> scores, String username, Integer currentHighScore){
        boolean match = false;
        //Si l'utilisateur existe deja
        for (HighScoreJSONModel highScoreJSON:
             scores) {
            if(highScoreJSON.getUsername().equals(username)) {
                Integer previousHighScore = highScoreJSON.getScore();
                if(previousHighScore<currentHighScore)
                    highScoreJSON.setScore(currentHighScore);
                match = true;
            }
             if (match)
                 break;
        }

        if (!match)
            scores.add(new HighScoreJSONModel(username, currentHighScore));

        return scores;
    }

    public void saveHighScore(String username, Integer score){
        //Mapper le contenu du fichier JSON
        ArrayList<HighScoreJSONModel> scores = getHighScoreFromJSON();
        setUserHighScore(scores, username, score);

        //Renregistrer
        saveScores(scores);
    }


}
